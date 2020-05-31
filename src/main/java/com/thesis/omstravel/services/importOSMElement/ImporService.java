package com.thesis.omstravel.services.importOSMElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.omstravel.model.*;
import com.thesis.omstravel.model.DAO.nodeDAO.INodeDAORepository;
import com.thesis.omstravel.model.DAO.nodeDAO.INodeRoutableDAORepository;
import com.thesis.omstravel.model.DAO.nodeDAO.NodeDAO;
import com.thesis.omstravel.model.DAO.nodeDAO.NodeRoutableDAO;
import com.thesis.omstravel.model.DAO.relationDAO.IRelationDAORepository;
import com.thesis.omstravel.model.DAO.relationDAO.RelationDAO;
import com.thesis.omstravel.model.DAO.wayDAO.IWayDAORepository;
import com.thesis.omstravel.model.DAO.wayDAO.WayDAO;
import com.thesis.omstravel.services.search.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.json.simple.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ImporService implements IImporService {

    @Autowired
    INodeDAORepository nodeDAORepository;

    @Autowired
    INodeRoutableDAORepository nodeRoutableDAORepository;

    @Autowired
    IWayDAORepository wayDAORepository;

    @Autowired
    IRelationDAORepository relationDAORepository;

    @Autowired
    ISearchService searchService;

    @Override
    public void importNode(List<NodeT> listNode) {
        NodeDAO nodeDAO = new NodeDAO();
        int i = 0;
        for (NodeT node : listNode) {
            i++;
            nodeDAO.setId(node.getId().substring(1));
            nodeDAO.setLat(String.valueOf(node.getLat()));
            nodeDAO.setLon(String.valueOf(node.getLon()));
            nodeDAO.setType("node");
            nodeDAO.setTags(buildJsonTag(node.getTags()));

            NodeDAO nodeDAO1 = nodeDAORepository.insert(nodeDAO);
            System.out.println(i + " import " + nodeDAO1.toString() + "successfully ");
        }
    }

    @Override
    public void importRoutableNode(List<NodeT> routableNodes) {
        NodeRoutableDAO nodeRoutableDAO = new NodeRoutableDAO();
        int i = 0;
        for (NodeT node : routableNodes) {
            i++;
            nodeRoutableDAO.setId(node.getId().substring(1));
            nodeRoutableDAO.setLat(String.valueOf(node.getLat()));
            nodeRoutableDAO.setLon(String.valueOf(node.getLon()));
            nodeRoutableDAO.setType("node");
            nodeRoutableDAO.setTags(buildJsonTag(node.getTags()));

            NodeRoutableDAO nodeRoutableDAO1 = nodeRoutableDAORepository.insert(nodeRoutableDAO);
            System.out.println(i + " import " + nodeRoutableDAO1.toString() + "successfully ");
        }
    }

    @Override
    public void importWay(List<Way> listWay) {
        WayDAO wayDAO = new WayDAO();
        int i=0;
        for (Way way : listWay) {
            i++;
            wayDAO.setId(way.getId().substring(1));
            wayDAO.setType("way");
            wayDAO.setTags(buildJsonTag(way.getTags()));
            wayDAO.setNodes(buildNodesListJSONString(way.getNodes()));
            WayDAO wayDAOResult = wayDAORepository.insert(wayDAO);
            System.out.println(i + " import way " + wayDAOResult.toString());
        }
    }

    public String buildNodesListJSONString(List<NodeT> nodeTList) {
        String nodesJSONString = JSONArray.toJSONString(nodeTList);
        System.out.println(nodesJSONString);
        return nodesJSONString;
    }

    @Override
    public void importRelation(List<Relation> relationList) {
        RelationDAO relationDAO = new RelationDAO();
        int i = 0;
        for (Relation relation : relationList) {
            i++;
            relationDAO.setId(relation.getId().substring(1));
            relationDAO.setTags(buildJsonTag(relation.getTags()));
            relationDAO.setType("relation");
            relationDAO.setMembers(buildMemberJSON(relation));

            RelationDAO relationDAOResult = relationDAORepository.insert(relationDAO);
            System.out.println(i + " import relation " + relationDAOResult.toString());
        }
    }

    public String buildJsonTag(Map tags) {
        String jsonTag = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonTag = objectMapper.writeValueAsString(tags);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonTag;
    }

    public String buildMemberJSON(Relation relation) {
        List<Member> memberList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        for (Element element: relation.getMembers()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ref", element.getId().substring(1));
            jsonObject.put("role", relation.getMemberRole(element));
            jsonObject.put("type", defindType(element.getId().substring(0,1)));

            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray);
        return jsonArray.toJSONString();
    }

    public String defindType(String elementID){
        if (elementID.substring(0,1).equalsIgnoreCase("N")){
            return "node";
        } else if (elementID.substring(0,1).equalsIgnoreCase("W")) {
            return "way";
        } else {
            return "relation";
        }
    }
}
