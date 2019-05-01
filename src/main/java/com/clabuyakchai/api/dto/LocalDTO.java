package com.clabuyakchai.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalDTO {
    @SerializedName("localID")
    @Expose
    private Long localID;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private String name;

    public LocalDTO() {
    }

    public LocalDTO(Long localID, String phone, String email, String gender, String name) {
        this.localID = localID;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.name = name;
    }

    public Long getLocalID() {
        return localID;
    }

    public void setLocalID(Long localID) {
        this.localID = localID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
