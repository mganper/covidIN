package com.upo.eps.in.covid.controller;

import com.upo.eps.in.covid.model.DataDto;
import com.upo.eps.in.covid.service.DataService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "redirect:/dashboard/1";
    }

    @GetMapping("/dashboard/{region}")
    public String dashboardMethod(Model model, @PathVariable(name = "region") int codeRegion) {
        String str;

        if(codeRegion < 1 || codeRegion > 19){
            str = "error/404";
        } else {
            List<DataDto> dataDtoList = dataService.readData();
            int casesRegion = 0, deathsRegion = 0, hospitalizedRegion = 0, dischargedRegion = 0,
                    cases = 0, deaths = 0, hospitalized = 0, discharged = 0;
            List<DataDto> regionList = dataService.getRegions(dataDtoList);

            DataDto dataDto = dataService.readDataByCodeRegion(codeRegion);

            casesRegion = dataDto.getCasesList().get(dataDto.getCasesList().size() - 1);
            deathsRegion = dataDto.getDeathsList().get(dataDto.getDeathsList().size() - 1);
            hospitalizedRegion = dataDto.getHospitalizedList().get(dataDto.getHospitalizedList().size() - 1);
            dischargedRegion = dataDto.getDischargedList().get(dataDto.getDischargedList().size() - 1);

            for (DataDto aux : dataDtoList) {

                cases += aux.getCasesList().get(aux.getCasesList().size() - 1);
                deaths += aux.getDeathsList().get(aux.getDeathsList().size() - 1);
                hospitalized += aux.getHospitalizedList().get(aux.getHospitalizedList().size() - 1);
                discharged += aux.getDischargedList().get(aux.getDischargedList().size() - 1);
            }


            DataDto listForChartRegion = dataService.getListForChartRegion(dataDtoList, codeRegion);
            DataDto listForChartCountry = dataService.getListForChartCountry(dataDtoList);
            List<DataDto> listForPieChart = dataService.getListForPieChart(dataDtoList);

            model.addAttribute("cases", cases);
            model.addAttribute("deaths", deaths);
            model.addAttribute("hospitalized", hospitalized);
            model.addAttribute("discharged", discharged);
            model.addAttribute("casesRegion", casesRegion);
            model.addAttribute("deathsRegion", deathsRegion);
            model.addAttribute("hospitalizedRegion", hospitalizedRegion);
            model.addAttribute("dischargedRegion", dischargedRegion);
            model.addAttribute("regionList", regionList);
            model.addAttribute("listForChartRegion", listForChartRegion);
            model.addAttribute("listForChartCountry", listForChartCountry);

            listForPieChart.forEach(e->{
                model.addAttribute("forPieChart" + e.getCodeRegion(), e);
            });


            str = "dashboard";
        }

        return str;
    }

    @GetMapping("/lifeTime")
    public String lifeTimeMethod(Model model) {
        List<DataDto> dataDtoList = dataService.readData();
        List<DataDto> regionList = dataService.getRegions(dataDtoList);
        model.addAttribute("regionList", regionList);

        return "lineaTemporal";
    }

    @GetMapping("/credits")
    public String creditsMethod(Model model) {
        List<DataDto> dataDtoList = dataService.readData();
        List<DataDto> regionList = dataService.getRegions(dataDtoList);
        model.addAttribute("regionList", regionList);

        return "credits";
    }
}
