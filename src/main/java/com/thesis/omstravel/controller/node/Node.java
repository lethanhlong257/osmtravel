package com.thesis.omstravel.controller.node;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "elements")
public class Node {

    public static final String NAME = "NodeModel";

    @Id
    @Indexed
    private String id;

    @Field(value = "lat")
    private String lat;

    @Field(value = "lon")
    private String lon;

    @Field(value = "tags")
    private String tags;

    @Field(value = "type")
    private String type;

    public Node(String id, String lat, String lon, String tags, String type) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.tags = tags;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTags() {
        return tags;
    }

    public String getType() {
        return type;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setType(String type) {
        this.type = type;
    }
}
