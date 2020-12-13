package com.example.letovi.controller;

import com.example.letovi.service.LetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;


@Controller
public class HomeController {

    @Autowired
    LetServiceImp letService;

    @GetMapping("/")
    public String getHome(Model model){
        return "home";
    }

    @RequestMapping("/find")
    public String homeSearch(Model model, @RequestParam(value = "polazniAerodrom")String polazniAerodrom,@RequestParam(value = "odredisniAerodrom")String odredisniAerodrom,@RequestParam(value = "datumPolaska") String datumPolaska,@RequestParam(value = "datumPovratka") String datumPovratka,@RequestParam(value = "brojPutnika") Integer brojPutnika,@RequestParam(value = "valuta") String valuta) {
        try {
            model.addAttribute("letovi", letService.checkExisting(polazniAerodrom, odredisniAerodrom,LocalDate.parse(datumPolaska),LocalDate.parse(datumPovratka),brojPutnika,valuta));
            return "home";
        }
        catch (Exception e){
            return "home";
        }
    }

}
