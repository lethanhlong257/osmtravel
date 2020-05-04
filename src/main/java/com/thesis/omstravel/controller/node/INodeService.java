package com.thesis.omstravel.controller.node;

import java.util.List;

public interface INodeService {
    List<Node> listNodeInDistance(double lat, double lon, double dist);

    List<Node> listNodeInDistanceByLat(double lat, double distance);
}
