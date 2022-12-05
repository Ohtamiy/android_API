package ua.cn.stu.countries_mvvm.model.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ShipsApi {
    @GET("/v3/ships/{ship_id}")
    Call<List<ShipNetworkEntity>> getShips(@Path("ship_id") String ship_id);

    @GET("/v3/ships/{ship_id}")
    Call<ShipNetworkEntity> getShipById(@Path("ship_id") String ship_id);
}
