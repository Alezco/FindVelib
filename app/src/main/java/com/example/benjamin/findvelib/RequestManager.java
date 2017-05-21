package com.example.benjamin.findvelib;

import android.support.v7.widget.RecyclerView;
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

    private Velib velib;

    private static final RequestManager ourInstance = new RequestManager();

    static RequestManager getInstance() {
        return ourInstance;
    }

    private RequestManager() {
    }

    public void getData(final List<String> fields, final RecyclerView.Adapter adapter) {
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
                    for (int i = 0; i < serviceVelib.records.size(); i++) {
                        fields.add(serviceVelib.records.get(i).fields.name);
                    }
                    adapter.notifyDataSetChanged();
                    velib = serviceVelib;
                }
                else {
                }
            }

            @Override
            public void onFailure(Call<Velib> call, Throwable t) {
                Log.d("==============", "ERROR");
                t.printStackTrace();
            }
        });

    }

    public void setVelib(Velib velib) {
        this.velib = velib;
    }

    public Velib getVelib() {
        return this.velib;
    }

}
