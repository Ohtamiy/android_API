package ua.cn.stu.countries_mvvm.screens;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ua.cn.stu.countries_mvvm.model.Callback;
import ua.cn.stu.countries_mvvm.model.Cancellable;
import ua.cn.stu.countries_mvvm.model.Ship;
import ua.cn.stu.countries_mvvm.model.ShipsService;
import ua.cn.stu.countries_mvvm.tasks.Result;

public class MainViewModel extends BaseViewModel {

    private Result<List<Ship>> shipsResult = Result.empty();

    private final MutableLiveData<Result<Ship>> shipLiveData = new MutableLiveData<>();
    {
        shipLiveData.setValue(Result.empty());
    }

    private final MutableLiveData<ViewState> stateLiveData  = new MutableLiveData<>();
    {
        updateViewState(Result.empty());
    }

    private Cancellable cancellable;

    @Override
    protected void onCleared() {
        super.onCleared();
        if (cancellable != null) cancellable.cancel();
    }

    public MainViewModel(ShipsService shipsService) {
        super(shipsService);
    }

    public LiveData<ViewState> getViewState() {
        return stateLiveData;
    }

    public void getShips(){
        updateViewState(Result.loading());
        cancellable = getShipsService().getShips(new Callback<List<Ship>>() {
            @Override
            public void onError(Throwable error) {
                if (shipsResult.getStatus() != Result.Status.SUCCESS) {
                    updateViewState(Result.error(error));
                }
            }
            @Override
            public void onResult(List<Ship> data) {
                updateViewState(Result.success(data));
            }
        });
    }

    private void updateViewState(Result<List<Ship>> shipsResult) {
        this.shipsResult = shipsResult;

        ViewState state = new ViewState();
        state.showList = shipsResult.getStatus()== Result.Status.SUCCESS;
        state.ships = shipsResult.getData();

        stateLiveData.postValue(state);
    }

    static class ViewState {

        private boolean showList;
        private List<Ship> ships;

        public boolean isShowList() {
            return showList;
        }

        public List<Ship> getShips() {
            return ships;
        }

    }

}
