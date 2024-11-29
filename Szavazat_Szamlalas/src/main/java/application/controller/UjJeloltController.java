package application.controller;

import application.dao.JeloltDAO;
import application.model.Jelolt;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UjJeloltController {

    @Autowired
    private JeloltDAO jeloltDAO;

    @GetMapping("/ujjelolt")
    public String showJeloltForm() {
        return "ujjelolt";
    }

    @PostMapping("/ujjelolt")
    public String createJelolt(@RequestParam("nev") String nev, 
                               @RequestParam("szulDatum") String szulDatumStr, 
                               @RequestParam("foglalkozas") String foglalkozas, 
                               @RequestParam("program") String program, 
                               Model model) {
        if (nev.isEmpty() || szulDatumStr.isEmpty() || foglalkozas.isEmpty() || program.isEmpty()) {
            model.addAttribute("errorMessage", "Minden mezőt ki kell tölteni!");
            return "ujjelolt";
        }

        Date szulDatum = Date.valueOf(szulDatumStr);

        Jelolt ujJelolt = new Jelolt(nev, szulDatum, foglalkozas, program);
        jeloltDAO.insertJelolt(ujJelolt);

        model.addAttribute("message", "Jelölt sikeresen hozzáadva!");
        return "redirect:/szavazasok";
    }
}
