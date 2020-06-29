package com.thesis.omstravel.controller.search;

import com.thesis.omstravel.model.DAO.point.IPointRepository;
import com.thesis.omstravel.model.DAO.point.Point;
import com.thesis.omstravel.model.Way;
import com.thesis.omstravel.model.search.Search;
import com.thesis.omstravel.model.search.SearchResultResponse;
import com.thesis.omstravel.services.search.ISearchService;
import com.thesis.omstravel.utils.OSMparser;
import org.eclipse.jdt.internal.compiler.env.IModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class RoutingController {
    List<Way> listWay = OSMparser.getWay();

    @Autowired
    ISearchService searchService;

    @Autowired
    IPointRepository pointRepository;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchByName(@RequestParam(value = "keyword") String name, Model model) {
        List<Search> searchResult;
        String decodedName = null;
        try {
            decodedName = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Search> listString = searchService.searchName(listWay);
        searchResult = searchService.matchName(decodedName, listString);
        model.addAttribute("listPoints", searchResult);
        return "home";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(@RequestParam(value = "keyword") String name, Model model) {
        List<Point> pointList = pointRepository.findPointsByNameContains(name);
        model.addAttribute("keyword", name);
        model.addAttribute("isSearchResult", true);
        model.addAttribute("points", pointList);

        return "search-result";
    }

    @RequestMapping(value = "/search/advanced", method = RequestMethod.GET)
        public String advancedSearch(@RequestParam(value = "keyword") String keyword) {

            return "home";
    }

    @RequestMapping(value = "/routing", method = RequestMethod.GET)
    public String routing(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to) {
        return "home";
    }
}
