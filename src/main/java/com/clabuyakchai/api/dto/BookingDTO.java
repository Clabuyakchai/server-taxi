package com.clabuyakchai.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingDTO {
    @SerializedName("bookingID")
    @Expose
    private Long bookingID;
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

    public BookingDTO() {
    }

    public BookingDTO(Long bookingID, String from, String to, Float price, String datetime) {
        this.bookingID = bookingID;
        this.from = from;
        this.to = to;
        this.price = price;
        this.datetime = datetime;
    }

    public Long getBookingID() {
        return bookingID;
    }

    public void setBookingID(Long bookingID) {
        this.bookingID = bookingID;
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
}
