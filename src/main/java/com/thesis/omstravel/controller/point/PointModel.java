package com.thesis.omstravel.controller.point;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Point")
public class PointModel {

    @Transient
    public static final String SEQUENCE_NAME = "vacationDate_sequence";

    public static final String NAME = "PointModel";

    @Id
    @Indexed
    private int id;

    @Field(value = "name")
    private String name;

    @Field(value = "description")
    private String description;

    @Field(value = "street1")
    private String street1;

    @Field(value = "street2")
    private String street2;

    @Field(value = "ward")
    private String ward;

    @Field(value = "district")
    private String district;

    @Field(value = "city")
    private String city;

    @Field(value = "state")
    private String state;

    @Field(value = "country")
    private String country;

    @Field(value = "zipCode")
    private int zipCode;

    @Field(value = "active")
    private boolean active;

    @Field(value = "latitude_second")
    private float latitude_second;

    @Field(value = "longtitude_second")
    private float longtitude_second;

    @Field(value = "phone")
    private long phone;

    @Field(value = "img")
    private String img;

    public PointModel() {
    }

    public PointModel(int id, String name, String description, String street1, String street2, String ward, String district, String city, String state, String country, int zipCode, boolean active, float latitude_second, float longtitude_second, long phone, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.street1 = street1;
        this.street2 = street2;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.active = active;
        this.latitude_second = latitude_second;
        this.longtitude_second = longtitude_second;
        this.phone = phone;
        this.img = img;
    }

    @Override
    public String toString() {
        return "PointModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", ward='" + ward + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode=" + zipCode +
                ", active=" + active +
                ", latitude_second=" + latitude_second +
                ", longtitude_second=" + longtitude_second +
                ", phone=" + phone +
                ", img='" + img + '\'' +
                '}';
    }

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public static String getNAME() {
        return NAME;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getLatitude_second() {
        return latitude_second;
    }

    public void setLatitude_second(float latitude_second) {
        this.latitude_second = latitude_second;
    }

    public float getLongtitude_second() {
        return longtitude_second;
    }

    public void setLongtitude_second(float longtitude_second) {
        this.longtitude_second = longtitude_second;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
