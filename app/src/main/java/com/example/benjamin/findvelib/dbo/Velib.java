package com.example.benjamin.findvelib.dbo;

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
