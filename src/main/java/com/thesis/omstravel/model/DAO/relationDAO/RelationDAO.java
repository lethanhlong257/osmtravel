package com.thesis.omstravel.model.DAO.relationDAO;

import com.thesis.omstravel.model.Member;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Relation")
public class RelationDAO {
    public static final String NAME = "RelationDAO";

    @Id
    @Indexed
    private String id;

    @Field(value = "members")
    /** The relation members **/
    private String members;

    @Field(value = "tags")
    private String tags;

    @Field(value = "type")
    private String type;

    public static String getNAME() {
        return NAME;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
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

    public RelationDAO(String id, String members, String tags, String type) {
        this.id = id;
        this.members = members;
        this.tags = tags;
        this.type = type;
    }

    public RelationDAO() {
    }

    @Override
    public String toString() {
        return "RelationDAO{" +
                "id='" + id + '\'' +
                ", members='" + members + '\'' +
                ", tags='" + tags + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
