package com.upo.eps.in.covid.controller;

import com.upo.eps.in.covid.service.DataService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppController {

    private final DataService dataService;

    public AppController(@Qualifier("DataService") DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/")
    public String testApp(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboardMethod(Model model){
        return "dashboard";
    }

    @GetMapping("/map")
    public String mapMethod(Model model){
        return "map";
    }

    @GetMapping("/credits")
    public String creditsMethod(Model model){
        return "credits";
    }
}
