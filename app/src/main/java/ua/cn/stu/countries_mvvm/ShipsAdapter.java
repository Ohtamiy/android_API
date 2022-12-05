package ua.cn.stu.countries_mvvm;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import ua.cn.stu.countries_mvvm.model.Ship;

public class ShipsAdapter extends RecyclerView.Adapter<ShipsAdapter.ShipsViewHolder> implements View.OnClickListener {

    private List<Ship> ships = Collections.emptyList();
    private final ShipListener listener;

    public ShipsAdapter(ShipListener listener) {
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setShipList(List<Ship> ships){
        this.ships = ships;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_ship, parent,false);
        return new ShipsViewHolder(root,this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShipsViewHolder holder, int position) {
        Ship ship = ships.get(position);
        holder.ship_name.setText(ship.getName());
        holder.ship_type.setText(ship.getType());
        holder.itemView.setTag(ship);
    }

    @Override
    public int getItemCount() {
        return ships.size();
    }

    @Override
    public void onClick(View view) {
        Ship ship = (Ship)view.getTag();
        listener.onCountryChosen(ship);
    }

    static class ShipsViewHolder extends RecyclerView.ViewHolder {

        private final TextView ship_name;
        private final TextView ship_type;

        public ShipsViewHolder(@NonNull View itemView, View.OnClickListener listener) {
            super(itemView);
            ship_name = itemView.findViewById(R.id.ship);
            ship_type = itemView.findViewById(R.id.type);
            itemView.setOnClickListener(listener);
        }
    }

    public interface ShipListener {
        void onCountryChosen(Ship ship);
    }
}
