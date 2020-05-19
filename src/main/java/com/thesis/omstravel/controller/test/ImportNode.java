package com.thesis.omstravel.controller.test;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.services.importOSMElement.ImporService;
import com.thesis.omstravel.utils.OSMparser;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class ImportNode {

    public static void main(String[] args) {
        String jsonString = "[{\"lat\":10.8993876, lon:106.7881499, id:5819479456, tags:{}},{lat:10.8993327, lon:106.7886946, id:5819479455, tags:{}}]";
        JSONParser jsonParser = new JSONParser();
        try {
            jsonParser.parse(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
