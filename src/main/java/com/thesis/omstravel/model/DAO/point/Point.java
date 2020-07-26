package com.thesis.omstravel.model.DAO.point;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Point")
public class Point {

    @Indexed
    @Id
    private long id;

    @Field(value = "name")
    private String name;

    @Field(value = "lat")
    private double lat;

    @Field(value = "lon")
    private double lon;

    @Field(value = "street")
    private String street;

    @Field(value = "country")
    private String country;

    @Field(value = "ward")
    private String ward;

    @Field(value = "city")
    private String city;

    @Field(value = "district")
    private String district;

    @Field(value = "zipcode")
    private String zipCode;

    @Field(value = "img")
    private String img;

    @Field(value = "phone")
    private String phone;

    @Field(value = "desc")
    private String desc;

    @Field(value = "tags")
    private String tags;

    @Field(value = "price")
    private double price;

    @Field(value = "link")
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Point() {
    }

    public Point(long id, String name, double lat, double lon, String street, String country, String ward, String city, String district, String zipCode, String img, String phone, String desc, String tags, double price, String link) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.street = street;
        this.country = country;
        this.ward = ward;
        this.city = city;
        this.district = district;
        this.zipCode = zipCode;
        this.img = img;
        this.phone = phone;
        this.desc = desc;
        this.tags = tags;
        this.price = price;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
