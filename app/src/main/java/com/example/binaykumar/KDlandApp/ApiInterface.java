package com.example.binaykumar.KDlandApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;


public interface ApiInterface {

    @GET("/villages/")
    Call<RetrofitVillages> getVillages1(
            @Header("Authorization") String authHeader
    );
    @GET("/villages/")
    Call<List<RetrofitVillages>> getVillages(
           @Header("Authorization") String authHeader
    );
    @GET("/villages/{snno}")
    public Call<RetrofitComplaints> getComplaint(@Header("Authorization") String authHeader,@Path("snno") int snno);

}
