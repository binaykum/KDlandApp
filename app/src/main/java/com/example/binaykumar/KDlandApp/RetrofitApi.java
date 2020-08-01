package com.example.binaykumar.KDlandApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitApi {

    @GET("villages")
    public Call<List<RetrofitComplaints>> getAllComplaints();

    @GET("/villages/{snno}")
    public Call<RetrofitComplaints> getComplaint(@Path("snno") int snno);
/*
    @POST("complaints/")
    public Call<Void> addcomplaint(@Body RetrofitComplaints complaint);

    @PUT("complaints/{id}")
    public Call<Void> updatecomplaint(@Body RetrofitComplaints complaint, @Path("id") int id);

    @DELETE("complaints/{id}")
    public Call<Void> deletecomplaint(@Path("id") int id);

*/
}
