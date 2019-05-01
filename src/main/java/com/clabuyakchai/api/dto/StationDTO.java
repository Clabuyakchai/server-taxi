package com.clabuyakchai.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationDTO {
    @SerializedName("stationID")
    @Expose
    private Long stationID;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("location")
    @Expose
    private String location;

    public StationDTO() {
    }

    public StationDTO(Long stationID, String name, String city, String location) {
        this.stationID = stationID;
        this.name = name;
        this.city = city;
        this.location = location;
    }

    public Long getStationID() {
        return stationID;
    }

    public void setStationID(Long stationID) {
        this.stationID = stationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
