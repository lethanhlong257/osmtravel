package com.thesis.omstravel.services.search;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Way;
import com.thesis.omstravel.model.search.Search;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class SearchService implements ISearchService{
    @Override
    public Way searchWay(String id, List<Way> listWay) {

        for (int i = 0; i < listWay.size(); i++) {
            if (listWay.get(i).getId().equals(id)) {
                return listWay.get(i);
            }
        }

        return null;
    }

    @Override
    public NodeT searchNode(String id, List<NodeT> listNode) {

        for (int i = 0; i < listNode.size(); i++) {
            if (listNode.get(i).getId().equals(id)) {
                return listNode.get(i);
            }
        }

        return null;
    }

    @Override
    public List<Search> searchName(List<Way> listWay) {
        List<Search> result = new ArrayList<Search>();


        for (int i = 0; i < listWay.size(); i++) {
            Way wayInList = listWay.get(i);
            Search search = new Search();

            @SuppressWarnings("rawtypes")
            Set set = wayInList.getTags().entrySet();
            @SuppressWarnings("rawtypes")
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry) iterator.next();

                // Get all key name of way
                if (me.getKey().equals("name") || me.getKey().equals("name:en")
                        || me.getKey().equals("name:vi")) {
                    if(me.getValue() != null){
                        search.setLat(wayInList.getNodes().get(0).getLat());
                        search.setLon(wayInList.getNodes().get(0).getLon());
                        search.setName(me.getValue().toString());
                        result.add(search);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public List<Search> matchName(String input, List<Search> listSearchResponse) {
        List<Search> result = new ArrayList<Search>();

        for (int i = 0; i < listSearchResponse.size(); i++) {
            String test = listSearchResponse.get(i).getName();

            // If contain input in UpperCase and LowerCase
            if (test.contains(removeAccent(input))
                    || test.toLowerCase().contains(removeAccent(input))
                    || test.contains(input)
                    || removeAccent(test).toLowerCase().contains(
                    removeAccent(input)) || test.contains(input)) {
                result.add(listSearchResponse.get(i));
            }
        }
        return result;
    }

    private String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public void writeTextFile(File output, String text) throws IOException {
        Writer w = new OutputStreamWriter(new FileOutputStream(output));
        w.write(text);
        w.close();
    }
}
