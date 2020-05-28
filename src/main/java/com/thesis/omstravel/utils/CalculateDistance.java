package com.thesis.omstravel.utils;

import com.thesis.omstravel.model.NodeT;

public class CalculateDistance {
    public double calcDistanceOfTwoNodes(NodeT node1, NodeT node2) {
        double distance = 0.0;
        double R = 6371000;
        double phi1 = Math.toRadians(node1.getLat());
        double phi2 = Math.toRadians(node2.getLat());
        double del1 = Math.toRadians(node2.getLat() - node1.getLat());
        double del2 = Math.toRadians(node2.getLon() - node1.getLon());

        double a = Math.sin(del1 / 2) * Math.sin(del1 / 2) + Math.cos(phi1)
                * Math.cos(phi2) * Math.sin(del2 / 2) * Math.sin(del2 / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        distance = R * c;

        return distance;
    }

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double distance = 0.0;
        double R = 6371000;
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double del1 = Math.toRadians(lat2 - lat1);
        double del2 = Math.toRadians(lon2 - lon1);

        double a = Math.sin(del1 / 2) * Math.sin(del1 / 2) + Math.cos(phi1)
                * Math.cos(phi2) * Math.sin(del2 / 2) * Math.sin(del2 / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        distance = R * c;

        return distance;
    }
}
