package com.example.benjamin.findvelib;

public class Velib {
    private String id;
    private String name;
    private boolean opened;
    private int bikeStands;
    private int availableBikeStands;
    private String address;
    private String date;
    private float latitude;
    private float longitude;

    public Velib(String id, String name, boolean opened, int bikeStands, int availableBikeStands, String address, String date, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.opened = opened;
        this.bikeStands = bikeStands;
        this.availableBikeStands = availableBikeStands;
        this.address = address;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
