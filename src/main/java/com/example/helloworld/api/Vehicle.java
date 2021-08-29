package com.example.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle {
    private Integer branchId;
    @JsonProperty
    public Integer getBranchId() {
        return branchId;
    }
    @JsonProperty
    public Integer getPrice() {
        return price;
    }
    @JsonProperty
    public String getVehicleType() {
        return vehicleType;
    }

    private Integer price;
    private String  vehicleType;

    public Vehicle(){

    }

    public Vehicle(String branchId, String price, String vehicleType){
        this.branchId = Integer.parseInt(branchId);
        this.price = Integer.parseInt(price);
        this.vehicleType = vehicleType;
    }
}
