package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class TrackingController {

    @GetMapping("track-package")
    public String getStatusPackage(){
        return "/homepages/trackPackage";
    }
}
