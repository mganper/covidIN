package com.upo.eps.in.covid.controller;

import com.upo.eps.in.covid.model.DataDto;
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
        List<DataDto> dataDtoList = dataService.readTemperatures();
        int cases = 0, deaths = 0, hospitalized = 0, discharged = 0;

        for (DataDto dataDto : dataDtoList) {
            cases += dataDto.getCasesList().get(dataDto.getCasesList().size()-1);
            deaths += dataDto.getDeathsList().get(dataDto.getDeathsList().size()-1);
            hospitalized += dataDto.getHospitalizListed().get(dataDto.getHospitalizListed().size()-1);
            discharged += dataDto.getDischargedList().get(dataDto.getDischargedList().size()-1);
        }

        DataDto dataDto = dataService.getListForChart(dataDtoList, "Andalucía");

        model.addAttribute("cases", cases);
        model.addAttribute("deaths", deaths);
        model.addAttribute("hospitalized", hospitalized);
        model.addAttribute("discharged", discharged);
        model.addAttribute("dateList", dataDto.getDateList().toArray());
        model.addAttribute("tempList", dataDto.getTempList().toArray());
        model.addAttribute("rainList", dataDto.getRainList().toArray());
        model.addAttribute("casesList", dataDto.getCasesList().toArray());

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
