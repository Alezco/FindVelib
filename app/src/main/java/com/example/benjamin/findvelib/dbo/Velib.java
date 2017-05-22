package com.example.benjamin.findvelib.dbo;

import android.support.v7.widget.util.SortedListAdapterCallback;

import java.util.List;

public class Velib {
    public List<Station> records;

    public Velib(List<Station> records) {
        this.records = records;
    }

    public void setStations(List<Station> stations) {
        this.records = stations;
    }
}
