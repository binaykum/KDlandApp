package com.example.binaykumar.KDlandApp.ui.Priority;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class PriorityViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PriorityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Work in process. Under development");
    }

    public LiveData<String> getText() {
        return mText;
    }
}