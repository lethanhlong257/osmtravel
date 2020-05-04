package com.thesis.omstravel.controller.node;

import java.util.List;

public interface IEnhancedNodeRepository {
    List<Node> findNodesByLatBetweenAndLonBetween(double maxLat, double maxLon, double minLat, double minLon);
}
