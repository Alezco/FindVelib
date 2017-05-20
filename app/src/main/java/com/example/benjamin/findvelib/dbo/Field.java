package com.example.benjamin.findvelib.dbo;

public class Field {
    public String status;
    public String name;
    public int bike_stands;
    public String last_update;
    public String available_bike_stands;
    public String address;
    public float[] position;

    public Field(String status, String name, int bike_stands, String last_update, String available_bike_stands, String address, float[] position) {
        this.status = status;
        this.name = name;
        this.bike_stands = bike_stands;
        this.last_update = last_update;
        this.available_bike_stands = available_bike_stands;
        this.address = address;
        this.position = position;
    }
}
