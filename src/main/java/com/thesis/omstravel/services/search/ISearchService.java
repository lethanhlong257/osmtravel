package com.thesis.omstravel.services.search;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Way;
import com.thesis.omstravel.model.search.Search;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ISearchService {

    public Way searchWay(String id, List<Way> listWay);

    public NodeT searchNode(String id, List<NodeT> listNode);

    public List<Search> searchName(List<Way> listWay);

    public List<Search> matchName(String input, List<Search> listSearchResponse);
}
