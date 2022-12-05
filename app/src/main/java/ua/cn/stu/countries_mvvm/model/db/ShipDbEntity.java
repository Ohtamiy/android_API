package ua.cn.stu.countries_mvvm.model.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import ua.cn.stu.countries_mvvm.model.network.ShipNetworkEntity;

@Entity(tableName = "ships")
public class ShipDbEntity {

    @PrimaryKey
    @NonNull
    private String ship_id;

    @ColumnInfo
    private String ship_name;

    @ColumnInfo
    private String ship_type;

    @ColumnInfo
    private String home_port;

    public ShipDbEntity() {
    }

    public ShipDbEntity(ShipNetworkEntity entity) {
        this.ship_id = entity.getShip_id();
        this.ship_name = entity.getShip_name();
        this.ship_type = entity.getShip_type();
        this.home_port = entity.getHome_port();
    }

    @NonNull
    public String getShip_id() {
        return ship_id;
    }

    public void setShip_id(@NonNull String ship_id) {
        this.ship_id = ship_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public String getShip_type() {
        return ship_type;
    }

    public void setShip_type(String ship_type) {
        this.ship_type = ship_type;
    }

    public String getHome_port() {
        return home_port;
    }

    public void setHome_port(String home_port) {
        this.home_port = home_port;
    }
}
