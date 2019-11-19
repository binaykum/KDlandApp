package com.example.binaykumar.KDlandApp.ui.Priority;

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

public class PriorityFragment extends Fragment {

    private PriorityViewModel priorityViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        priorityViewModel =
                ViewModelProviders.of(this).get(PriorityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_priority, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        priorityViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}