package com.thesis.omstravel.controller.node;

import java.util.ArrayList;
import java.util.List;

public class EnhancedNodeRepository implements IEnhancedNodeRepository {
    @Override
    public List<Node> findNodesByLatBetweenAndLonBetween(double maxLat, double maxLon, double minLat, double minLon) {
        List<Node> NodesByLatBetweenAndLonBetween = new ArrayList<>();

        return NodesByLatBetweenAndLonBetween;
    }
}
