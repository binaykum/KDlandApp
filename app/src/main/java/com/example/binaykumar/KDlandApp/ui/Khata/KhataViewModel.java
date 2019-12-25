package com.example.binaykumar.KDlandApp.ui.Khata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class KhataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KhataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is khata fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}