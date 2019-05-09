package com.clabuyakchai.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteDTO {
    @SerializedName("routeID")
    @Expose
    private Long routeID;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("price")
    @Expose
    private Float price;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("stations")
    @Expose
    private List<StationDTO> stations;
    @SerializedName("bus")
    @Expose
    private BusDTO bus;

    public RouteDTO() {
    }

    public RouteDTO(String from, String to, Float price) {
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public RouteDTO(String from, String to, Float price, String datetime, List<StationDTO> stations, BusDTO bus) {
        this.from = from;
        this.to = to;
        this.price = price;
        this.datetime = datetime;
        this.stations = stations;
        this.bus = bus;
    }

    public Long getRouteID() {
        return routeID;
    }

    public void setRouteID(Long routeID) {
        this.routeID = routeID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public List<StationDTO> getStations() {
        return stations;
    }

    public void setStations(List<StationDTO> stations) {
        this.stations = stations;
    }

    public BusDTO getBus() {
        return bus;
    }

    public void setBus(BusDTO bus) {
        this.bus = bus;
    }
}
