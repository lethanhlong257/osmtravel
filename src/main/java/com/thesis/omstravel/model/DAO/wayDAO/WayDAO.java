package com.thesis.omstravel.model.DAO.wayDAO;

import com.thesis.omstravel.model.DAO.nodeDAO.NodeDAO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Way")
public class WayDAO {

    public static final String NAME = "WayDAO";

    @Id
    @Indexed
    private String id;

    @Field(value = "nodes")
    private String nodes;

    @Field(value = "tags")
    private String tags;

    @Field(value = "type")
    private String type;

    public WayDAO(String id, String nodes, String tags, String type) {
        this.id = id;
        this.nodes = nodes;
        this.tags = tags;
        this.type = type;
    }

    public WayDAO() {
    }

    public static String getNAME() {
        return NAME;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WayDAO{" +
                "id='" + id + '\'' +
                ", nodes='" + nodes + '\'' +
                ", tags='" + tags + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
