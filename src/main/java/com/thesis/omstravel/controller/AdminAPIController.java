package com.thesis.omstravel.controller;

import com.thesis.omstravel.model.DAO.point.IPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AdminAPIController {

    @Autowired
    IPointRepository pointRepository;

    @RequestMapping(value = "/api/v1/admin/location/remove/{id}", method = RequestMethod.GET)
    public void removeLocation(@PathVariable(value = "id") String id) {
        try {
            pointRepository.deleteById(Long.parseLong(id));
            System.out.println(id);
        } catch (Error error) {
            System.out.println(error);
        }
    }

    @DeleteMapping("/api/admin/location/remove/{id}")
    public void deleteLocations(@PathVariable(value = "id") String id) {
        try {
            pointRepository.deleteById(Long.parseLong(id));
            System.out.println(id);
        } catch (Error error) {
            System.out.println(error);
        }
    }
}
