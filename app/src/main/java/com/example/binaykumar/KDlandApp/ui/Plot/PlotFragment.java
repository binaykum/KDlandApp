package com.example.binaykumar.KDlandApp.ui.Plot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.binaykumar.KDlandApp.R;

public class PlotFragment extends Fragment {

    private PlotViewModel plotViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        plotViewModel =
                ViewModelProviders.of(this).get(PlotViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plot, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        plotViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}