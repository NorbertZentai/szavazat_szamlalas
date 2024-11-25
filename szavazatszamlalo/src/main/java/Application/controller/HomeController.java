package Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Application.dao.FelhasznaloDAO;
import Application.dao.SzavazasDAO;
import Application.model.Felhasznalo;
import Application.model.Szavazas;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SzavazasDAO szavazasDao;

    @Autowired
    private FelhasznaloDAO felhasznaloDao;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Szavazas> kihirdetettSzavazasok = szavazasDao.getOsszesSzavazas();
        model.addAttribute("kihirdetettSzavazasok", kihirdetettSzavazasok);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("felhasznalo", new Felhasznalo());
        return "register";
    }

    @PostMapping("/registerAdd")
    public String register(@ModelAttribute("felhasznalo") Felhasznalo felhasznalo, Model model) {
        try {
            felhasznalo.setSzerep("ROLE_USER");
            felhasznaloDao.mentesFelhasznalo(felhasznalo);
            model.addAttribute("message", "Sikeres regisztráció!");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Hiba történt a regisztráció során. Kérjük, próbáld újra.");
            return "register";
        }
    }
}
