package com.example.binaykumar.KDlandApp.ui.Plot;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Plot fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}