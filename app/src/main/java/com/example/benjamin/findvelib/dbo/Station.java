package com.example.benjamin.findvelib.dbo;

public class Station {
    public String recordid;
    public Field fields;

    public Station(String recordid, Field fields) {
        this.recordid = recordid;
        this.fields = fields;
    }
}
