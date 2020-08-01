package com.example.binaykumar.KDlandApp.ui.Priority;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;


import com.example.binaykumar.KDlandApp.ApiInterface;
import com.example.binaykumar.KDlandApp.R;
import com.example.binaykumar.KDlandApp.RTodos;
import com.example.binaykumar.KDlandApp.RetrofitApi;
import com.example.binaykumar.KDlandApp.RetrofitComplaints;
import com.example.binaykumar.KDlandApp.RetrofitService;
import com.example.binaykumar.KDlandApp.RetrofitVillages;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriorityFragment extends Fragment {

    private PriorityViewModel priorityViewModel;

   // RetrofitApi retrofitApi;
    ApiInterface apiInterface;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        priorityViewModel =
                ViewModelProviders.of(this).get(PriorityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_priority, container, false);
        final TextView textView = root.findViewById(R.id.tv11);

        priorityViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final TextView tvv = (TextView) root.findViewById(R.id.tvv);
        tvv.setText("23");

        Button button7 = (Button) root.findViewById(R.id.btn5);
        apiInterface= RetrofitService.getClient().create(ApiInterface.class);
        String user="kdcmp";
        String password="kdcmp";
        String base = user +":"+ password;

        String authHeader ="Basic "+ Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.i("         from just click", "rvepllllllllllllllllllllll");

                Call<List<RetrofitVillages>> call =apiInterface.getVillages(
                        authHeader
                );
                Call<RetrofitVillages> call1 =apiInterface.getVillages1(
                        authHeader
                );
System.out.println(authHeader);
                /*
                try {
                    call.enqueue((new Callback<List<RetrofitVillages>>() {
                        @Override


                        public void onResponse(Call<List<RetrofitVillages>> call, Response<List<RetrofitVillages>> response) {
                            Log.e("from on response of call ", "t  " + response.body());
                        }

                        @Override
                        public void onFailure(Call<List<RetrofitVillages>> call, Throwable t) {
                            Log.e("   from failure ", "on failure blcok " + t.getMessage());
                        }
                    }));


                } finally {
                    System.out.println("from finally");
                }



                */


                call1.enqueue((new Callback<RetrofitVillages>() {

                    @Override
                    public void onResponse(Call<RetrofitVillages> call, Response<RetrofitVillages> response) {
                        if (response.isSuccessful()) { System.out.println(" response is successful");}
                        RetrofitVillages resss=  response.body();
                        System.out.println(  "what " );

                        Log.e(" from test dessage", "t "  +resss.getVillagename() );
                        System.out.println(call);

                    }

                    @Override
                    public void onFailure(Call<RetrofitVillages> call, Throwable t) {
                            Log.e("from failure  message", t.getMessage());

                        }

                }));




/*

                call.enqueue((new Callback<List<RetrofitVillages>>() {

                    @Override
                    public void onResponse(Call<List<RetrofitVillages>> call, Response<List<RetrofitVillages>> response) {
                        if (response.isSuccessful()) { System.out.println(" response is successful");}
                        String resss=  response.body().toString();
                        System.out.println(  "what " +response.body().size());

                        Log.e(" from test dessage", "t"  +resss);
                        System.out.println(call);
                    }

                    @Override
                    public void onFailure(Call<List<RetrofitVillages>> call, Throwable t) {
                        Log.e("from failure  message", t.getMessage());
                    }

                }));

*/
            }
        });


        return root;
    }


}