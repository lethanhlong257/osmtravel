package com.thesis.omstravel.services.routing;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.RoutingResult;

public interface IRoutingService {
    /**
     * Find shortedPath between two nodes
     * @param startNode
     * @param endNode
     * @param algorithm
     * @return RoutingResult
     */
    public RoutingResult findShortestPath(NodeT startNode, NodeT endNode, int algorithm);
}
