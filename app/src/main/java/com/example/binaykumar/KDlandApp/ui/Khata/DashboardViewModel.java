package com.example.binaykumar.KDlandApp.ui.Khata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is khata fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}