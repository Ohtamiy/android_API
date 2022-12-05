package ua.cn.stu.countries_mvvm;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import ua.cn.stu.countries_mvvm.model.ShipsService;
import ua.cn.stu.countries_mvvm.model.db.AppDb;
import ua.cn.stu.countries_mvvm.model.db.ShipDao;
import ua.cn.stu.countries_mvvm.model.network.ShipsApi;
import ua.cn.stu.countries_mvvm.screens.ViewModelFactory;

public class App extends Application {

    private static final String BASE_URL = "https://api.spacexdata.com";
    private ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        ShipsApi shipsApi = retrofit.create(ShipsApi.class);

        ExecutorService executorService = Executors.newCachedThreadPool();

        AppDb appDB = Room
                .databaseBuilder(this, AppDb.class, "ships.db")
                .build();

        ShipDao shipDao = appDB.getShipDao();
        ShipsService shipsService = new ShipsService(shipsApi, shipDao, executorService);
        viewModelFactory = new ViewModelFactory(shipsService);
    }

    public ViewModelProvider.Factory getViewModelFactory() {
        return viewModelFactory;
    }
}
