package com.thesis.omstravel.controller.node;

import com.thesis.omstravel.utils.CheckNumberic;
import com.thesis.omstravel.utils.constant.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class NodeController {
    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    INodeService nodeService;

    @Autowired
    CheckNumberic checkNumberic;

    @GetMapping(path = APIConstant.API_FIND_ALL_NODES, produces = "application/json")
    @ResponseBody
    public List<Node> getUserVacationDates() {
        return nodeRepository.findAll();
    }

    @GetMapping(path = "/api/v1/get/nodes/distance/{dist}/{lat}/{lon}}", produces = "application/json")
    @ResponseBody
    public List<Node> listNodeInDistance(@PathVariable String dist, @PathVariable String lon, @PathVariable String lat) {
        List<Node> listNodeInDistance = null;
        if (checkNumberic.isNumeric(dist) && checkNumberic.isNumeric(lat) && checkNumberic.isNumeric(lon)) {
            // lat: 10.748902, 106.687569
            listNodeInDistance = nodeService.listNodeInDistance(
                    Double.parseDouble(lat),
                    Double.parseDouble(lon),
                    Double.parseDouble(dist));
            return listNodeInDistance;
        } else {
            return listNodeInDistance;
        }
    }

    @GetMapping(path = "/api/v1/get/node/bylat", produces = "application/json")
    @ResponseBody
    public List<Node> listNodeInDistanceByLat() {
        return nodeService.listNodeInDistanceByLat(10.748902, 1);
    }
}
