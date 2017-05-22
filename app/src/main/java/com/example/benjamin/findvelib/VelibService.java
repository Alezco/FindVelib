package com.example.benjamin.findvelib;

import com.example.benjamin.findvelib.dbo.Velib;

import retrofit2.Call;
import retrofit2.http.GET;

interface VelibService {
    String ENDPOINT = "https://opendata.paris.fr";

    @GET("/api/records/1.0/search/?dataset=stations-velib-disponibilites-en-temps-reel&rows=100&facet=banking&facet=bonus&facet=status&facet=contract_name")
    Call<Velib> getVelibs();
}
