package com.thesis.omstravel.services.routing;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.RoutingResult;
import com.thesis.omstravel.utils.DijkstraRouting;
import com.thesis.omstravel.utils.constant.AppConst;
import org.springframework.stereotype.Component;

@Component
public class RoutingService implements IRoutingService {
    @Override
    public RoutingResult findShortestPath(NodeT startNode, NodeT endNode,
                                          int algorithm) {

        switch (algorithm) {
            case AppConst.DIJKSTRA:
                return new DijkstraRouting().findDijkstraShortestPath(startNode,
                        endNode);
            default:
                break;
        }

        return null;
    }
}
