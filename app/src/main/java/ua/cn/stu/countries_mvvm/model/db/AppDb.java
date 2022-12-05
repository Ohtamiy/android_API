package ua.cn.stu.countries_mvvm.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {ShipDbEntity.class})
public abstract class AppDb extends RoomDatabase {
    public abstract ShipDao getShipDao();
}
