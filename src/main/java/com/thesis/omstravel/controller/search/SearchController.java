package com.thesis.omstravel.controller.search;

import com.thesis.omstravel.model.DAO.wayDAO.IEnhancedWayDAORepository;
import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Relation;
import com.thesis.omstravel.model.Way;
import com.thesis.omstravel.model.search.Search;
import com.thesis.omstravel.model.search.SearchResultResponse;
import com.thesis.omstravel.services.search.ISearchService;
import com.thesis.omstravel.utils.OSMparser;
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
    IEnhancedWayDAORepository enhancedWayDAORepository;

    List<Way> listWay = OSMparser.getWay();
//    List<NodeT> listNode = OSMparser.getNode();
    List<Relation> listRelation = OSMparser.getRelation();
//
    //List<NodeT> listRoutableNode = OSMparser.getRoutableNode(listWay, listRelation);

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


}
