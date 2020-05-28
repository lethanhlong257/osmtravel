package com.thesis.omstravel.controller.search;

import com.thesis.omstravel.model.*;
import com.thesis.omstravel.model.DAO.wayDAO.IEnhancedWayDAORepository;
import com.thesis.omstravel.model.search.Search;
import com.thesis.omstravel.model.search.SearchResultResponse;
import com.thesis.omstravel.services.routing.INearestNodeService;
import com.thesis.omstravel.services.routing.IRoutingService;
import com.thesis.omstravel.services.search.ISearchService;
import com.thesis.omstravel.utils.OSMparser;
import com.thesis.omstravel.utils.constant.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.StringTokenizer;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SearchController {

    @Autowired
    ISearchService searchService;

    @Autowired
    IRoutingService routingService;

    @Autowired
    INearestNodeService nodeFindingService;


    @Autowired
    IEnhancedWayDAORepository enhancedWayDAORepository;

    List<Way> listWay = OSMparser.getWay();
//    List<NodeT> listNode = OSMparser.getNode();
    List<Relation> listRelation = OSMparser.getRelation();
//
    List<NodeT> listRoutableNode = OSMparser.getRoutableNode(listWay, listRelation);

    @RequestMapping(value = "/api/v1.0/search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String search(@RequestParam(value = "keyword") String name) {
        //List<Way> listWay = OSMparser.getWay();
        //List<Way> listWay = enhancedWayDAORepository.findAllWays();
        List<Search> searchResult;
        String decodedName = null;
        try {
            decodedName = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Search> listString = searchService.searchName(listWay);
        searchResult = searchService.matchName(decodedName, listString);
        SearchResultResponse searchResultResponse = new SearchResultResponse(searchResult);
        System.out.println("Search keywork: " + decodedName);
        return searchResultResponse.toString();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public String searchByName(@RequestParam(value = "keyword") String name) {
        List<Search> searchResult;
        String decodedName = null;
        try {
            decodedName = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Search> listString = searchService.searchName(listWay);
        searchResult = searchService.matchName(decodedName, listString);
        SearchResultResponse searchResultResponse = new SearchResultResponse(searchResult);
        System.out.println("Search keywork: " + decodedName);
        return searchResultResponse.toString();
    }

    @RequestMapping(value = "/api/v1.0/get/all/way", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getWays() {
        List<Way> ways = enhancedWayDAORepository.findAllWays();
        //List<Way> ways = OSMparser.getWay();
        return ways.toString();
    }

    /**
     * Example
     * Benh vien mat - Cho ben thanh
     * http://localhost:8080/routing?route=10.780698,106.6807187;10.7652623,106.6827139
     *
     **/
    @RequestMapping(value = "/routing", method = RequestMethod.GET, headers = "Accept=application/json;charset=UTF-8")
    public String routing(@RequestParam(value = "route") String route) {

        String decodeRoute = null;
        NodeT startNode = null, endNode = null;

        try {
            decodeRoute = URLDecoder.decode(route, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringTokenizer st = new StringTokenizer(decodeRoute, ";");
        String startCoord = st.nextToken();
        String endCoord = st.nextToken();
        if (startNode == null && endNode == null) {
            startNode = nodeFindingService.findNearestNode(listRoutableNode,
                    startCoord);
            endNode = nodeFindingService.findNearestNode(listRoutableNode,
                    endCoord);
        }

        System.out.println("Start node: " +startNode.getId());
        System.out.println("End node: " +endNode.getId());
        RoutingResult routingResult = routingService.findShortestPath(
                startNode, endNode, AppConst.DIJKSTRA);

        RoutingResponse response = new RoutingResponse(routingResult);

        return response.toString();
    }

}
