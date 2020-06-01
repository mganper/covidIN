package com.upo.eps.in.covid.controller;

import com.upo.eps.in.covid.model.DataDto;
import com.upo.eps.in.covid.service.DataService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
        return "redirect:/dashboard/0";
    }

    @GetMapping("/dashboard/{region}")
    public String dashboardMethod(Model model, @PathVariable(name = "region") int codeRegion) {
        List<DataDto> dataDtoList = dataService.readData();
        int cases = 0, deaths = 0, hospitalized = 0, discharged = 0;
        List<DataDto> regionlist = new ArrayList<>();

        if(codeRegion != 0){
            DataDto dataDto = dataService.readDataByCodeRegion(codeRegion);

            cases += dataDto.getCasesList().get(dataDto.getCasesList().size() - 1);
            deaths += dataDto.getDeathsList().get(dataDto.getDeathsList().size() - 1);
            hospitalized += dataDto.getHospitalizedList().get(dataDto.getHospitalizedList().size() - 1);
            discharged += dataDto.getDischargedList().get(dataDto.getDischargedList().size() - 1);
        }

        for (DataDto aux : dataDtoList) {

            if(codeRegion == 0) {
                cases += aux.getCasesList().get(aux.getCasesList().size() - 1);
                deaths += aux.getDeathsList().get(aux.getDeathsList().size() - 1);
                hospitalized += aux.getHospitalizedList().get(aux.getHospitalizedList().size() - 1);
                discharged += aux.getDischargedList().get(aux.getDischargedList().size() - 1);
            }

            regionlist.add(new DataDto(aux.getNameRegion(), aux.getCodeRegion()));
        }

        DataDto listForChart = dataService.getListForChart(dataDtoList, codeRegion);

        model.addAttribute("cases", cases);
        model.addAttribute("deaths", deaths);
        model.addAttribute("hospitalized", hospitalized);
        model.addAttribute("discharged", discharged);
        model.addAttribute("regionList", regionlist);
        model.addAttribute("dataDto", listForChart);

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
