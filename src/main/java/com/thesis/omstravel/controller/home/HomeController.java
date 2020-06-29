package com.thesis.omstravel.controller.home;

import com.thesis.omstravel.model.DAO.point.IPointRepository;
import com.thesis.omstravel.model.DAO.point.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    IPointRepository pointRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(@ModelAttribute("point") Point point, Model model) {
        List<Point> pointList = pointRepository.findAll();

        model.addAttribute("points", pointList);
        return "home";
    }
}
