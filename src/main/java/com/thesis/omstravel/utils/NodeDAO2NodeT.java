package com.thesis.omstravel.utils;

import com.thesis.omstravel.model.DAO.nodeDAO.NodeDAO;
import com.thesis.omstravel.model.NodeT;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class NodeDAO2NodeT {
    public NodeT convert(NodeDAO nodeDAO) {
        NodeT nodeT = new NodeT(Long.parseLong(nodeDAO.getId()), Double.parseDouble(nodeDAO.getLat()), Double.parseDouble(nodeDAO.getLon()));

        String tagsString = nodeDAO.getTags();
        if (!tagsString.equals("{}")) {
            String[] tags = tagsString.split(",");
            for (String tag : tags) {
                String[] keyValue = tag.split("=");
                if (keyValue.length == 2) {
                    nodeT.addTag(keyValue[0], keyValue[1]);
                }
            }
        }
        return nodeT;
    }
}
