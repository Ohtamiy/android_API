package ua.cn.stu.countries_mvvm.screens;

import androidx.lifecycle.ViewModel;

import ua.cn.stu.countries_mvvm.model.ShipsService;

public class BaseViewModel extends ViewModel {

    private final ShipsService shipsService;

    protected final ShipsService getShipsService() {
        return shipsService;
    }

    public BaseViewModel(ShipsService shipsService) {
        this.shipsService = shipsService;
    }
}
