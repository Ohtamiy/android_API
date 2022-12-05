package ua.cn.stu.countries_mvvm.screens;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ua.cn.stu.countries_mvvm.model.Callback;
import ua.cn.stu.countries_mvvm.model.Cancellable;
import ua.cn.stu.countries_mvvm.model.ShipsService;
import ua.cn.stu.countries_mvvm.model.Ship;
import ua.cn.stu.countries_mvvm.tasks.Result;


public class DetailsViewModel extends BaseViewModel {

    private final MutableLiveData<Result<Ship>> countryLiveData = new MutableLiveData<>();
    {
        countryLiveData.setValue(Result.empty());
    }

    private Cancellable cancellable;

    public DetailsViewModel(ShipsService shipsService) {
        super(shipsService);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (cancellable != null) {
            cancellable.cancel();
        }
    }

    public void loadShipById(String country_id) {
        countryLiveData.setValue(Result.loading());
        cancellable = getShipsService().getShipById(country_id, new Callback<Ship>() {
            @Override
            public void onError(Throwable error) {
                countryLiveData.postValue(Result.error(error));
            }

            @Override
            public void onResult(Ship data) {
                countryLiveData.postValue(Result.success(data));
            }
        });
    }

    public LiveData<Result<Ship>> getResults() {
        return countryLiveData;
    }
}
