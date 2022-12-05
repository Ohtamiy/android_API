package ua.cn.stu.countries_mvvm.model;

import com.annimon.stream.Stream;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import retrofit2.Response;
import ua.cn.stu.countries_mvvm.model.db.ShipDao;
import ua.cn.stu.countries_mvvm.model.db.ShipDbEntity;
import ua.cn.stu.countries_mvvm.model.network.ShipsApi;
import ua.cn.stu.countries_mvvm.model.network.ShipNetworkEntity;

public class ShipsService {

    private final ExecutorService executorService;
    private final ShipDao shipDao;
    private final ShipsApi shipsApi;

    public ShipsService(ShipsApi shipsApi,
                        ShipDao shipDao,
                        ExecutorService executorService) {
        this.shipsApi = shipsApi;
        this.shipDao = shipDao;
        this.executorService = executorService;
    }

    public Cancellable getShips(Callback<List<Ship>> callback) {
        return new FutureCancellable(executorService.submit(() -> {
            try {
                List<ShipDbEntity> entities = shipDao.getShips();
                List<Ship> ships = convertToShips(entities);
                callback.onResult(ships);

                Response<List<ShipNetworkEntity>> response = shipsApi.getShips("").execute();
                if (response.isSuccessful()) {
                    List<ShipDbEntity> newDbShips = networkToDbEntities(response.body());
                    shipDao.updateShips(newDbShips);
                    callback.onResult(convertToShips(newDbShips));
                } else {
                    if (!ships.isEmpty()) {
                        RuntimeException exception = new RuntimeException("Error");
                        callback.onError(exception);
                    }
                }
            } catch (Exception e) {
                callback.onError(e);
            }
        }));
    }

    public Cancellable getShipById(String ship_id, Callback<Ship> callback){
        return new FutureCancellable(executorService.submit(() -> {
            try {
                ShipDbEntity dbEntity = shipDao.getShipById(ship_id);
                Ship ship = new Ship(dbEntity);
                callback.onResult(ship);
            } catch (Exception e) {
                callback.onError(e);
            }
        }));
    }

    private List<Ship> convertToShips(List<ShipDbEntity> entities) {
        return Stream.of(entities)
                .map(Ship::new)
                .toList();
    }

    private List<ShipDbEntity> networkToDbEntities(List<ShipNetworkEntity> entities) {
        return Stream.of(entities)
                .map(ShipDbEntity::new)
                .toList();
    }

    static class FutureCancellable implements Cancellable {
        private final Future<?> future;

        public FutureCancellable(Future<?> future) {
            this.future = future;
        }

        @Override
        public void cancel() {
            future.cancel(true);
        }
    }
}
