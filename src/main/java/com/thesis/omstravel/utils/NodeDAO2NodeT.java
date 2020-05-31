package com.thesis.omstravel.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.omstravel.model.DAO.nodeDAO.NodeDAO;
import com.thesis.omstravel.model.NodeT;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class NodeDAO2NodeT {
    public NodeT convert(NodeDAO nodeDAO) {
        NodeT nodeT = new NodeT(Long.parseLong(nodeDAO.getId()), Double.parseDouble(nodeDAO.getLat()), Double.parseDouble(nodeDAO.getLon()));

        ObjectMapper mapper = new ObjectMapper();
        String tagsString = nodeDAO.getTags();

        // convert JSON string to Map
        try {
            Map<String, String> tagsMap = mapper.readValue(tagsString, Map.class);
            tagsMap.forEach((key, value) -> nodeT.addTag(key, value));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*if (!tagsString.equals("{}")) {
            String[] tags = tagsString.split(",");
            for (String tag : tags) {
                String[] keyValue = tag.split("=");
                if (keyValue.length == 2) {
                    nodeT.addTag(keyValue[0], keyValue[1]);
                }
            }
        }*/
        return nodeT;
    }
}
