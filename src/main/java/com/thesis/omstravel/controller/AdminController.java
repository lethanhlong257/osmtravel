package com.thesis.omstravel.controller;

import com.thesis.omstravel.database.SequenceGenerator;
import com.thesis.omstravel.model.DAO.point.ExtendedPoint;
import com.thesis.omstravel.model.DAO.point.IPointRepository;
import com.thesis.omstravel.model.DAO.point.Point;
import com.thesis.omstravel.model.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        long countLocation = pointRepository.count();
        model.addAttribute("totalLocation", countLocation);
        model.addAttribute("points", points);
        return "admin/views/dashboard";
    }

    @RequestMapping(value = "/admin/place/add", method = RequestMethod.GET)
    public String addNewPlace(Model model) {
        model.addAttribute("ExtendedPoint", new ExtendedPoint());
        return "admin/views/NewPlaceMain";
    }

    @GetMapping("/admin/location/update/{id}")
    public String updateLocationGet(@PathVariable("id") long id, Model model) {
        Point point = pointRepository.findById(id);
        model.addAttribute("Point", point);
        return "admin/views/update-location";
    }

    @PostMapping("/admin/location/update/{id}")
    public String updateLocationPut(@ModelAttribute("Point") Point newPoint, @PathVariable("id") long id, Model model) {
        newPoint.setId(id);
        pointRepository.save(newPoint);
        model.addAttribute("isUpdated", true);
        return "admin/views/update-location";
    }

    @RequestMapping(value = "admin/place/add", method = RequestMethod.POST)
    public String addNewPlacePost(ExtendedPoint exPoint, Model model) {
        boolean isPlaced = false;
        String UPLOADED_FOLDER = "src/main/assert/img/";
        MultipartFile imgFile = exPoint.getImgFile();
        if (imgFile.isEmpty()) {
            int random = (int)(Math.random() * 6 + 1);
            exPoint.setImg(random+".png");
        } else {
            // Get the file and save it somewhere
            byte[] bytes = new byte[0];
            try {
                bytes = imgFile.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + imgFile.getOriginalFilename());
                Files.write(path, bytes);
                exPoint.setImg(imgFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Point p = new Point();
        p.setId(sequenceGenerator.generateSequence("id"));
        p.setDistrict(exPoint.getDistrict());
        p.setDesc(exPoint.getDesc());
        p.setCountry(exPoint.getCountry());
        p.setCity(exPoint.getCity());
        p.setZipCode(exPoint.getZipCode());
        p.setImg(exPoint.getImg());
        p.setLat(exPoint.getLat());
        p.setLon(exPoint.getLon());
        p.setName(exPoint.getName());
        p.setPhone(exPoint.getPhone());
        p.setPrice(exPoint.getPrice());
        p.setStreet(exPoint.getStreet());
        p.setTags(exPoint.getTags());
        p.setWard(exPoint.getWard());
        p.setLink(exPoint.getLink());
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
        model.addAttribute("ExtendedPoint", new ExtendedPoint());
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
