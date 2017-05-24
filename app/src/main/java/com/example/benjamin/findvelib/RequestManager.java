package com.example.benjamin.findvelib;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.benjamin.findvelib.dbo.Station;
import com.example.benjamin.findvelib.dbo.Velib;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RequestManager {

    private static final RequestManager ourInstance = new RequestManager();

    public Velib velibList;

    static RequestManager getInstance() {
        return ourInstance;
    }

    private RequestManager() {
    }

    public void getData(final Velib velib, final RecyclerView.Adapter adapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VelibService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VelibService velibService = retrofit.create(VelibService.class);
        Call<Velib> velibs = velibService.getVelibs();
        velibs.enqueue(new Callback<Velib>() {
            @Override
            public void onResponse(Call<Velib> call, Response<Velib> response) {
                if (response.isSuccessful()) {
                    Velib serviceVelib = response.body();
                    velib.setStations(serviceVelib.records);
                    velibList = serviceVelib;
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Velib> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
