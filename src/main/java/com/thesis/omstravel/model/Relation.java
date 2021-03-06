package com.thesis.omstravel.model;

import java.util.ArrayList;
import java.util.List;

public class Relation extends Element{
    // ATTRIBUTES
    /** The relation members **/
    private List<Member> members;

    // CONSTRUCTOR
    public Relation(long id) {
        super(id);
        members = new ArrayList<Member>();
    }

    // ACCESSORS
    @Override
    public String getId() {
        return "R" + id;
    }

    /**
     * @param e
     *            The element
     * @return The role of this element, or null if no role
     */
    public String getMemberRole(Element e) {
        String result = null;

        boolean found = false;
        int index = 0;
        while (!found && index < members.size()) {
            if (members.get(index).elem == e) {
                found = true;
                result = members.get(index).role;
            }
            index++;
        }

        if (!found) {
            throw new RuntimeException("Element " + e.getId() + " not found");
        }

        return result;
    }

    /**
     * @return The list of member elements
     */
    public List<Element> getMembers() {
        List<Element> elems = new ArrayList<Element>(members.size());
        for (Member m : members) {
            elems.add(m.elem);
        }
        return elems;
    }

    // MODIFIERS
    /**
     * Adds a new member
     *
     * @param role
     *            The role of the member
     * @param e
     *            The element
     */
    public void addMember(String role, Element e) {
        if (e == null) {
            throw new NullPointerException("Element can't be null");
        }

        members.add(new Member(role, e));
    }

    /**
     * Removes a member
     *
     * @param e
     *            The element to remove
     */
    public void removeMember(Element e) {
        boolean found = false;
        int index = 0;
        while (!found && index < members.size()) {
            if (members.get(index).elem == e) {
                found = true;
                members.remove(index);
            }
            index++;
        }
    }

    // INNER CLASS Member
    private class Member {
        // ATTRIBUTES
        /** The member role **/
        private String role;
        /** The member object **/
        private Element elem;

        // CONSTRUCTOR
        /**
         * Default constructor
         *
         * @param role
         *            The member role
         * @param elem
         *            The member object
         */
        private Member(String role, Element elem) {
            this.role = role;
            this.elem = elem;
        }

        @Override
        public String toString() {
            return "{" +
                    "role:'" + role +
                    "ref:" + elem.getId().substring(1) +
                    "type:" + defindType(elem.getId()) +
                    '}';
        }

        public String defindType(String elementID){
            if (elementID.substring(0,1).equalsIgnoreCase("N")){
                return "node";
            } else if (elementID.substring(0,1).equalsIgnoreCase("W")) {
                return "way";
            } else {
                return "relation";
            }
        }
    }
}
