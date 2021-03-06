package com.example.binaykumar.KDlandApp.ui.Result;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.binaykumar.KDlandApp.R;

public class ResultFragment extends Fragment {
private TextView result;
String message="Welcome to Keredari";
    private ResultViewModel mViewModel;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View root= inflater.inflate(R.layout.fragment_result, container, false);
         result= (TextView)root.findViewById(R.id.textView5);
         result.setMovementMethod(new ScrollingMovementMethod());
         Bundle bundle= getArguments();
          message= bundle.getString("message");
             result.setText(message);

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        // TODO: Use the ViewModel
    }

}
