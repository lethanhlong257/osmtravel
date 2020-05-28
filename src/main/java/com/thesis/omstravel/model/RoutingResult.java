package com.thesis.omstravel.model;

import java.util.LinkedList;

public class RoutingResult {
    private LinkedList<NodeT> shortestPath;
    private double distance = 0.0;

    public LinkedList<NodeT> getShortestPath() {
        return shortestPath;
    }
    public void setShortestPath(LinkedList<NodeT> result) {
        this.shortestPath = result;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
}
