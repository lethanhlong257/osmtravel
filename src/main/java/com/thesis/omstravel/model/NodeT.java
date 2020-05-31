package com.thesis.omstravel.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NodeT extends Element {
    //ATTRIBUTES
    /** The latitude **/
    private double lat;
    /** The longitude **/
    private double lon;

    private List<NodeT> adjNode = new ArrayList<NodeT>();

    private double distance = 0;

    private boolean checked = false;

    public void clearData(){
        this.distance = 0;
        this.checked = false;
        this.shortestPath.clear();
    }

    private LinkedList<NodeT> shortestPath = new LinkedList<NodeT>();

    public LinkedList<NodeT> getShortestPath() {
        return shortestPath;
    }

    public void addNodeShortestPath(NodeT node) {
        this.shortestPath.add(node);
    }

    public void setShortestPath(LinkedList<NodeT> path) {
        this.shortestPath.clear();

        for(int i = 0; i < path.size(); i++){
            shortestPath.add(path.get(i));
        }

    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<NodeT> getAdjNode() {
        return adjNode;
    }

    public void setAdjNode(List<NodeT> adjNode) {
        this.adjNode = adjNode;
    }

//CONSTRUCTOR
    /**
     * Default constructor
     * @param id The object ID
     * @param lat The latitude
     * @param lon The longitude
     */
    public NodeT(long id, double lat, double lon) {
        super(id);
        this.lat = lat;
        this.lon = lon;
    }

    //ACCESSORS
    @Override
    public String getId() {
        return "N"+id;
    }

    /**
     * @return the latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * @return the longitude
     */
    public double getLon() {
        return lon;
    }

//MODIFIERS
    /**
     * @param lat the new latitude
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @param lon the new longitude
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "{" +
                "\"lat\":" + "\"" + lat + "\"" +
                ", \"lon\":" + "\"" + lon + "\"" +
                ", \"id\":" + "\"" + id + "\"" +
                ", \"tag\":" + "\"" + tags + "\"" +
                '}';
    }

}
