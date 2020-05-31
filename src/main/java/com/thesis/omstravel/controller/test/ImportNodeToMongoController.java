package com.thesis.omstravel.controller.test;


import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Relation;
import com.thesis.omstravel.model.Way;
import com.thesis.omstravel.services.importOSMElement.IImporService;
import com.thesis.omstravel.utils.OSMparser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImportNodeToMongoController {
    @Autowired
    IImporService importService;

    @RequestMapping(value = "/api/v1.0/import/node", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String importNodesToMongo() {
        List<Way> listWay = OSMparser.getWay();
        List<Relation> listRelation = OSMparser.getRelation();
        List <NodeT> listRoutableNode = OSMparser.getRoutableNode(listWay, listRelation);
        List<NodeT> listNode = OSMparser.getNode();
        importService.importNode(listNode);
        importService.importRoutableNode(listRoutableNode);

        return "import done";
    }

    @RequestMapping(value = "/api/v1.0/import/way", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String importWaysToMongo() {
        List<Way> listWays = OSMparser.getWay();
        importService.importWay(listWays);
        return "import done";
    }

    @RequestMapping(value = "/api/v1.0/import/relation", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String importRelationsToMongo() {
        List<Relation> relationList = OSMparser.getRelation();
        importService.importRelation(relationList);
        return "import relation list done";
    }
}
