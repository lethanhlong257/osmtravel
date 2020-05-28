package com.thesis.omstravel.services.routing;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Way;

import java.util.List;

public interface INearestNodeService {
    public NodeT find (List<Way> listWay, String coordinate);

    public NodeT findNearestNode(List<NodeT> listNode, String coordinate);
}
