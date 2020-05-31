package com.thesis.omstravel.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class RoutingResponse {
    private JSONObject response = new JSONObject();

    public RoutingResponse(RoutingResult routingResult) {
        LinkedList<NodeT> shortestPath = routingResult.getShortestPath();
        double distance = routingResult.getDistance();
        response.put("type", "Features");

        JSONObject geometry = new JSONObject();
        geometry.put("type", "lineString");
        JSONArray coordinates = new JSONArray();
        for (int i = 0; i < shortestPath.size(); i++) {
            // In GeoJSON format, in coordinate, long is before lat
            JSONArray coord = new JSONArray("[" + shortestPath.get(i).getLat()
                    + "," + shortestPath.get(i).getLon() + "]");
            coordinates.put(coord);
        }
        geometry.put("coordinates", coordinates);
        response.put("geometry", geometry);
        response.put("distance", round(distance));
        System.out.println("Distance = "+round(distance));
    }

    private String round(double number){
        String finalDistance;
        DecimalFormat df = new DecimalFormat("#.##");
        if ( number >= 1000){
            double temp = number / 1000;
            finalDistance = df.format(temp) + " km";
        } else {
            finalDistance = df.format(number) + " m";
        }
        return finalDistance;
    }
    @Override
    public String toString(){
        return response.toString();
    }
}
