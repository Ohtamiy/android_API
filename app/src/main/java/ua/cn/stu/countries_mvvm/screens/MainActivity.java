package ua.cn.stu.countries_mvvm.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ua.cn.stu.countries_mvvm.App;
import ua.cn.stu.countries_mvvm.ShipsAdapter;
import ua.cn.stu.countries_mvvm.R;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_NAME";

    private ShipsAdapter adapter;
    private RecyclerView shipsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shipsList = findViewById(R.id.shipsList);

        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, app.getViewModelFactory());
        MainViewModel viewModel = viewModelProvider.get(MainViewModel.class);

        viewModel.getViewState().observe(this, state -> {
            shipsList.setVisibility(toVisibility(state.isShowList()));
            adapter.setShipList(state.getShips());
        });

        initShipsList();
        viewModel.getShips();
    }

    private void initShipsList(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        shipsList.setLayoutManager(layoutManager);

        adapter = new ShipsAdapter(ship -> {
            Intent intent = new Intent (this, DetailsActivity.class);
            intent.putExtra(EXTRA_ID, ship.getId());
            startActivity(intent);
        });
        shipsList.setAdapter(adapter);
    }

    static int toVisibility (boolean show){
        return show ? View.VISIBLE : View.GONE;
    }
}