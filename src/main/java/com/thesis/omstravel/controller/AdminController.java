package com.thesis.omstravel.controller;

import com.thesis.omstravel.database.SequenceGenerator;
import com.thesis.omstravel.model.DAO.point.IPointRepository;
import com.thesis.omstravel.model.DAO.point.Point;
import com.thesis.omstravel.model.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    IPointRepository pointRepository;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String dashboard(Model model) {

        List points = pointRepository.findAll();
        model.addAttribute("totalLocation", 10);
        model.addAttribute("points", points);
        return "admin/views/dashboard";
    }

    @RequestMapping(value = "/admin/place/add", method = RequestMethod.GET)
    public String addNewPlace(ModelMap model) {
        model.addAttribute("isPlaced", "true");
        model.addAttribute("Point", new Point());
        return "admin/views/NewPlaceMain";
    }

    @RequestMapping(value = "admin/place/add", method = RequestMethod.POST)
    public String addNewPlacePost(@ModelAttribute("Point") Point p, Model model) {
        boolean isPlaced = false;
        p.setId(sequenceGenerator.generateSequence("id"));

//        try {
//            MultipartFile multipartFile = myFile.getMultipartFile();
//            String fileName = multipartFile.getOriginalFilename();
//            File file = new File(this.getFolderUpload(), fileName);
//            multipartFile.transferTo(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("message", "Upload failed");
//        }

        if (!isDuplicateName(p) || !isDuplicateCoordinate(p.getLat(), p.getLon())) {
            Point savedPoint = pointRepository.save(p);
            if (savedPoint.getName().equals(p.getName())) {
                isPlaced = true;
            }
        } else {
            model.addAttribute("message", "Duplicate Point");
        }
        model.addAttribute("isPlaced", isPlaced);
        return "admin/views/NewPlaceMain";
    }

    private boolean isDuplicateCoordinate(double lat, double lon) {
        Point pointDB = pointRepository.findDistinctFirstByLatAndLon(lat, lon);
        if (pointDB != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDuplicateName(Point p) {
        Point pointDB = pointRepository.findDistinctFirstByName(p.getName());
        if (pointDB != null) {
            return true;
        }
        return false;
    }


    @RequestMapping(value = "/admin/place/list", method = RequestMethod.GET)
    public String listPlaces() {
        return "admin/views/PlaceList";
    }

    public File getFolderUpload() {
        File folderUpload = new File("/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

}
