package ua.cn.stu.countries_mvvm.screens;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.annimon.stream.Objects;

import ua.cn.stu.countries_mvvm.App;
import ua.cn.stu.countries_mvvm.R;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_NAME";

    private TextView shipTextView;
    private TextView shipTypeTextView;
    private TextView homePortTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        shipTextView = findViewById(R.id.shipTextView);
        shipTypeTextView = findViewById(R.id.shipTypeTextView);
        homePortTextView = findViewById(R.id.homePortTextView);

        String country_id = getIntent().getStringExtra(EXTRA_ID);

        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, app.getViewModelFactory());
        DetailsViewModel viewModel = viewModelProvider.get(DetailsViewModel.class);

        viewModel.loadShipById(country_id);
        viewModel.getResults().observe(this, result -> {
            switch (result.getStatus()){
                case SUCCESS:{
                    shipTextView.setText(result.getData().getName());
                    shipTypeTextView.setText(Objects.equals(result.getData().getType(), "") ?
                                    "None" : result.getData().getType());
                    homePortTextView.setText(Objects.equals(result.getData().getPort(), "") ?
                            "None" : result.getData().getPort());
                    break;
                }
                case EMPTY:{
                    shipTextView.setText("Nothing found");
                    shipTypeTextView.setText("");
                    homePortTextView.setText("");
                    break;
                }
                case LOADING:{
                    shipTextView.setText("");
                    shipTypeTextView.setText("");
                    homePortTextView.setText("");
                    break;
                }
            }
        });

    }
}