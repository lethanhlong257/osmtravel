package com.thesis.omstravel.utils;

import com.thesis.omstravel.model.DAO.nodeDAO.INodeDAORepository;
import com.thesis.omstravel.model.DAO.nodeDAO.NodeDAO;
import com.thesis.omstravel.model.DAO.wayDAO.IWayDAORepository;
import com.thesis.omstravel.model.DAO.wayDAO.WayDAO;
import com.thesis.omstravel.model.Element;
import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Way;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WayDAO2Way {
    @Autowired
    INodeDAORepository nodeDAORepository;

    @Autowired
    NodeDAO2NodeT nodeDAO2NodeT;

    private Element element;

    public Way convert (WayDAO wayDAO) {
        JSONParser jsonParser = new JSONParser();
        Way way = new Way(Long.parseLong(wayDAO.getId()));
        List<NodeT> nodeTList = null;
        try {
            JSONArray nodesJsonArray = (JSONArray) jsonParser.parse(wayDAO.getNodes());
            String tagsString = wayDAO.getTags();
            if (!tagsString.equals("{}")) {
                String[] tags = tagsString.split(",");
                for (String tag : tags) {
                    String[] keyValue = tag.split("=");
                    if (keyValue.length == 2) {
                        way.addTag(keyValue[0], keyValue[1]);
                    }
                }
            }

            for (Object node : nodesJsonArray) {
                JSONObject nodeJSON = (JSONObject) node;
                NodeT nodeT = new NodeT(Long.parseLong(nodeJSON.get("id").toString()),
                        Double.parseDouble(nodeJSON.get("lat").toString()),
                        Double.parseDouble(nodeJSON.get("lon").toString())
                        );
                String tagNodeString = nodeJSON.get("tag").toString();

                if (!tagNodeString.equals("{}")) {
                    String[] tags = tagsString.split(",");
                    for (String tag : tags) {
                        String[] keyValue = tag.split("=");
                        if (keyValue.length == 2) {
                            nodeT.addTag(keyValue[0], keyValue[1]);
                        }
                    }
                }

                way.addNode(nodeT);
            }

//            JSONObject tagJSON = new JSONObject(tagsString);
//            String [] keysTag = JSONObject.getNames(tagJSON);
//            for (String key : keysTag) {
//                way.addTag(key, (String) tagJSON.get(key));
//           }

//            for (Object nodeID : nodesJsonArray) {
//                System.out.println("Find NODE byNodeID: " + nodeID.toString());
//                NodeDAO nodeDAO = nodeDAORepository.findById(nodeID.toString());
//                NodeT nodeT = nodeDAO2NodeT.convert(nodeDAO);
//                way.addNode(nodeT);
//            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        return way;
    }
}
