package com.thesis.omstravel.model;

public class Member {
    private String role;
    private String ref;
    private String type;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Member(String role, String ref, String type) {
        this.role = role;
        this.ref = ref;
        this.type = type;
    }

    public Member() {
    }
}
