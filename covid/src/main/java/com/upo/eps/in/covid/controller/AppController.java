package com.upo.eps.in.covid.controller;

import com.upo.eps.in.covid.model.Temperature;
import com.upo.eps.in.covid.service.DataService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {

    private final DataService dataService;

    public AppController(@Qualifier("DataService") DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/")
    public String indexMethod() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboardMethod(Model model) {
        List<Temperature> temperatureList = dataService.readTemperatures();
        int cases = 0, deaths = 0, hospitalized = 0, discharged = 0;

        for (Temperature temperature : temperatureList){
            for (int c : temperature.getCasesList()){
                cases += c;
            }

            for (int c : temperature.getDeathsList()){
                deaths += c;
            }

            for (int c : temperature.getHospitalizListed()){
                hospitalized += c;
            }

            for (int c : temperature.getDischargedList()){
                discharged += c;
            }
        }

        model.addAttribute("cases", cases);
        model.addAttribute("deaths", deaths);
        model.addAttribute("hospitalized", hospitalized);
        model.addAttribute("discharged", discharged);

        return "dashboard";
    }

    @GetMapping("/map")
    public String mapMethod(Model model) {
        return "map";
    }

    @GetMapping("/credits")
    public String creditsMethod(Model model) {
        return "credits";
    }
}
