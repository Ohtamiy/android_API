package ua.cn.stu.countries_mvvm.screens;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Constructor;

import ua.cn.stu.countries_mvvm.model.ShipsService;

public class ViewModelFactory implements ViewModelProvider.Factory {

    public static final String TAG = ViewModelFactory.class.getSimpleName();
    private final ShipsService shipsService;

    public ViewModelFactory(ShipsService shipsService) {
        this.shipsService = shipsService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            Constructor<T> constructor = modelClass.getConstructor(ShipsService.class);
            return constructor.newInstance(shipsService);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
