package com.example.benjamin.findvelib;

import android.util.Log;

import com.example.benjamin.findvelib.dbo.Field;
import com.example.benjamin.findvelib.dbo.Station;
import com.example.benjamin.findvelib.dbo.Velib;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RequestManager {
    public Velib velib;

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
        Call<Velib> velibs = velibService.getVelibs();
        velibs.enqueue(new Callback<Velib>() {
            @Override
            public void onResponse(Call<Velib> call, Response<Velib> response) {
                if (response.isSuccessful()) {
                    Velib serviceVelib = response.body();
                    Log.d("==================", serviceVelib.toString());
                }
                else {
                    Log.d("====else===", "====================");
                }
            }

            @Override
            public void onFailure(Call<Velib> call, Throwable t) {
                Log.d("==============", "ERROR");
                t.printStackTrace();
            }
        });
        /*try {
            this.velib = velibs.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //List<Field> fields = stations.
    }
}
