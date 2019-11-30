package com.example.binaykumar.KDlandApp.ui.Raiyat;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.binaykumar.KDlandApp.R;

public class RaiyatFragment extends Fragment {

    private RaiyatViewModel mViewModel;

    public static RaiyatFragment newInstance() {
        return new RaiyatFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.raiyat_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RaiyatViewModel.class);
        // TODO: Use the ViewModel
    }

}
