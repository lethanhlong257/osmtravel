package com.thesis.omstravel.model.search;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class SearchResultResponse {
    private JSONObject response = new JSONObject();

    public SearchResultResponse(List<Search> listSearch) {

        JSONArray list = new JSONArray();

        for (int i = 0; i < listSearch.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("type", "Point");
            JSONArray coord = new JSONArray("[" + listSearch.get(i).getLon()
                    + "," + listSearch.get(i).getLat() + "]");
            object.put("coordinates", coord);
            object.put("name", listSearch.get(i).getName());
            list.put(object);
        }

        response.put("SearchList", list);
    }

    @Override
    public String toString(){
        return response.toString();
    }
}
