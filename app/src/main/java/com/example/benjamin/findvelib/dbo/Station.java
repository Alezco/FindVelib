package com.example.benjamin.findvelib.dbo;

import java.util.List;

public class Station {
    public String recordid;
    public Field fields;

    public Station(String recordid, Field fields) {
        this.recordid = recordid;
        this.fields = fields;
    }
}
