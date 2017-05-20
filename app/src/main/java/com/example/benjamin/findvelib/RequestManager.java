package com.example.benjamin.findvelib;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RequestManager {
    public List<Velib> velibList;

    private static final RequestManager ourInstance = new RequestManager();

    static RequestManager getInstance() {
        return ourInstance;
    }

    private RequestManager() {
    }

    public String test = "lolilol";

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VelibService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VelibService velibService = retrofit.create(VelibService.class);
        Call<List<Velib>> velibList = velibService.listVelibs();
        velibList.enqueue(new Callback<List<Velib>>() {
            @Override
            public void onResponse(Call<List<Velib>> call, Response<List<Velib>> response) {
                if (response.isSuccessful()) {
                    List<Velib> serviceVelibList = response.body();
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<List<Velib>> call, Throwable t) {

            }
        });
        try {
            this.velibList = velibList.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < this.velibList.size(); i++) {
            Log.d("===============", this.velibList.get(i).toString());
        }
    }
}
