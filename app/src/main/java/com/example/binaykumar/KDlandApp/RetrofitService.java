package com.example.binaykumar.KDlandApp;

import android.util.Log;

import retrofit2.Retrofit;
// import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
   // private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
  // private static final String BASE_URL = "https://jsonplaceholder.typicode.com";//json
   private static final String BASE_URL = "http://binaykum.pythonanywhere.com"; //binaykum
    //private static final String BASE_URL = "https://shrouded-beyond-67487.herokuapp.com/";
 // private static final String BASE_URL = "http://binaykum.pythonanywhere.com";
 // private static final String BASE_URL = "http://10.0.2.2:8000";
  private  static Retrofit retrofit=null;
  public static Retrofit getClient(){
      if (retrofit==null){
          retrofit= new Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();
      }
return retrofit;

  }



  /*
    private static RetrofitService mInstance;

    private Retrofit mRetrofit;

    private RetrofitService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitService getInstance() {
        if (mInstance == null) {

            mInstance = new RetrofitService();
            Log.i("form retrofitservice", "received from exception");

              }
        return mInstance;
    }
    public RetrofitApi getJSONApi() {
        return mRetrofit.create(RetrofitApi.class);
    }
*/
}
