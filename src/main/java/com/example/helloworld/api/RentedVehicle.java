package com.example.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentedVehicle {
    private Vehicle vehicle;
    private String slot;

    public RentedVehicle(){

    }
    @JsonProperty
    public Vehicle getVehicle() {
        return vehicle;
    }

    @JsonProperty
    public String getSlot() {
        return slot;
    }
    private String[] hrs = {"00:00 hrs","01:00 hrs","02:00 hrs","03:00 hrs","04:00 hrs","05:00 hrs",
            "06:00 hrs","07:00 hrs","08:00 hrs","09:00 hrs","10:00 hrs","11:00 hrs","12:00 hrs",
            "13:00 hrs","14:00 hrs","15:00 hrs","16:00 hrs","17:00 hrs","18:00 hrs",
            "19:00 hrs","20:00 hrs","21:00 hrs","22:00 hrs","23:00 hrs"};

    public RentedVehicle(Vehicle vehicle, Integer slot){
        this.vehicle = vehicle;
        this.slot = hrs[slot];
    }
}
