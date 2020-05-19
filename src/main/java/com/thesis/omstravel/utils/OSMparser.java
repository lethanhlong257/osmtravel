package com.thesis.omstravel.utils;

import com.thesis.omstravel.model.Element;
import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Relation;
import com.thesis.omstravel.model.Way;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OSMparser {
    private static String mapFilePath = "minimap.osm";

    public static List<Way> getWay() {
        List<Way> listWay = new ArrayList<Way>();
        Resource resource = new ClassPathResource(mapFilePath);

        try {
            OSMSaxParser osmSaxParser = new OSMSaxParser();
            InputStream resourceInputStream = resource.getInputStream();
            Map<String, Element> OSMSaxData = osmSaxParser.parse(resourceInputStream);
            Element currentElem = null;
            for (String id : OSMSaxData.keySet()) {
                currentElem = OSMSaxData.get(id);
                if (currentElem instanceof Way) {
                    Way currentWay = (Way) currentElem;

                    listWay.add(currentWay);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return listWay;
    }

    public static List<NodeT> getRoutableNode(List<Way> listWay,
                                             List<Relation> listRelation) {
        List<NodeT> listRoutableNode;
        GraphExport graphExport = new GraphExport();
        listRoutableNode = graphExport.buildGraph(listWay, listRelation);
        return listRoutableNode;
    }
    public static List<NodeT> getNode() {
        List<NodeT> listNode = new ArrayList<>();
        Resource resource = new ClassPathResource(mapFilePath);

        try {
            OSMSaxParser osmSaxParser = new OSMSaxParser();
            InputStream resourceInputStream = resource.getInputStream();
            Map<String, Element> result = osmSaxParser.parse(resourceInputStream);
            Element currentElem;
            for (String id : result.keySet()) {
                currentElem = result.get(id);
                if (currentElem instanceof NodeT) {
                    NodeT currentNode = (NodeT) currentElem;
                    listNode.add(currentNode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return listNode;
    }

    public static List<Relation> getRelation() {
        List<Relation> listRelation = new ArrayList<Relation>();
        Resource resource = new ClassPathResource(mapFilePath);

        try {
            OSMSaxParser osmSaxParser = new OSMSaxParser();
            InputStream resourceInputStream = resource.getInputStream();
            Map<String, Element> result = osmSaxParser.parse(resourceInputStream);
            Element currentElem;
            for (String id : result.keySet()) {
                currentElem = result.get(id);
                if (currentElem instanceof Relation) {
                    Relation currentRelation = (Relation) currentElem;
                    listRelation.add(currentRelation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return listRelation;
    }
}
