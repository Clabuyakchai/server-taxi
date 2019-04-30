package com.clabuyakchai.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteDTO {
//    @SerializedName("id")
//    @Expose
//    private Long id;
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
    @SerializedName("stationsID")
    @Expose
    private List<Long> stationsID;

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

    public List<Long> getStationsID() {
        return stationsID;
    }

    public void setStationsID(List<Long> stationsID) {
        this.stationsID = stationsID;
    }
}
