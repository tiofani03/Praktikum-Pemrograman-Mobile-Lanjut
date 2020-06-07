package com.tiooooo.mynoteapps.ui;

import android.app.Application;

import com.tiooooo.mynoteapps.ui.insert.NoteAddUpdateViewModel;
import com.tiooooo.mynoteapps.ui.main.MainViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private ViewModelFactory(Application application){
        mApplication = application;
    }

    public static ViewModelFactory getInstance(Application application){

        if(INSTANCE == null){
            synchronized (ViewModelFactory.class){
                if(INSTANCE == null){
                    INSTANCE = new ViewModelFactory(application);
                }
            }
        }

        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(mApplication);
        }else if(modelClass.isAssignableFrom(NoteAddUpdateViewModel.class)){
            return (T) new NoteAddUpdateViewModel(mApplication);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
