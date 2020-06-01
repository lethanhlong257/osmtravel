package com.thesis.omstravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String dashboard() {

        return "admin/views/dashboard";
    }

    @RequestMapping(value = "/admin/place/add", method = RequestMethod.GET)
    public String addNewPlace() {
        return "admin/views/NewPlaceMain";
    }

    @RequestMapping(value = "/admin/place/list", method = RequestMethod.GET)
    public String listPlaces() {
        return "admin/views/PlaceList";
    }
}
