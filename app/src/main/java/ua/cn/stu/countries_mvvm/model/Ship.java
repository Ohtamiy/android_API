package ua.cn.stu.countries_mvvm.model;

import ua.cn.stu.countries_mvvm.model.db.ShipDbEntity;

public class Ship {
    private final String id;
    private final String name;
    private final String type;
    private final String port;

    public Ship(String id,
                String name,
                String type,
                String port) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.port = port;
    }

    public Ship(ShipDbEntity entity){
        this(entity.getShip_id(),
                entity.getShip_name(),
                entity.getShip_type(),
                entity.getHome_port());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPort() {
        return port;
    }
}
