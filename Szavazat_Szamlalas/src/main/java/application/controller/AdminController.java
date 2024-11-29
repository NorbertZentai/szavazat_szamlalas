package application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.dao.FelhasznaloDAO;
import application.dao.JeloltDAO;
import application.dao.SzavazasDAO;
import application.dao.SzavazatDAO;
import application.model.Felhasznalo;
import application.model.Jelolt;
import application.model.Szavazas;
import application.model.Szavazat;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FelhasznaloDAO felhasznaloDAO;

    @Autowired
    private JeloltDAO jeloltDAO;

    @Autowired
    private SzavazasDAO szavazasDAO;

    @Autowired
    private SzavazatDAO szavazatDAO;

    @GetMapping
    public String showAdminPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Felhasznalo admin = felhasznaloDAO.getFelhasznaloByUsername(username);

        List<Szavazat> szavazatok = szavazatDAO.getOsszesSzavazat();
        List<String> szavazasNevek = new ArrayList<>();
        List<String> felhasznaloNevek = new ArrayList<>();
        List<String> jeloltNevek = new ArrayList<>();
        List<String> idopontok = new ArrayList<>();

        for (Szavazat szavazat : szavazatok) {
            Szavazas szavazas = szavazasDAO.getSzavazasById(szavazat.getSzavazasId());
            Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloById(szavazat.getFelhasznaloId());
            Jelolt jelolt = jeloltDAO.getJeloltById(szavazat.getJeloltId());

            szavazasNevek.add(szavazas.getMegnevezes());
            felhasznaloNevek.add(felhasznalo.getName());
            jeloltNevek.add(jelolt.getNev());
            idopontok.add(szavazat.getIdopont());
        }

        model.addAttribute("admin", admin);
        model.addAttribute("felhasznalok", felhasznaloDAO.listFelhasznalo());
        model.addAttribute("szavazasok", szavazasDAO.getOsszesSzavazas());
        model.addAttribute("jeloltek", jeloltDAO.getOsszesJelolt());
        model.addAttribute("szavazasNevek", szavazasNevek);
        model.addAttribute("felhasznaloNevek", felhasznaloNevek);
        model.addAttribute("jeloltNevek", jeloltNevek);
        model.addAttribute("idopontok", idopontok);
        return "admin";
    }


    @GetMapping("/felhasznalok")
    public String listFelhasznalok(Model model) {
        model.addAttribute("felhasznalok", felhasznaloDAO.listFelhasznalo());
        return "admin";
    }

    @PostMapping("/felhasznalo/modosit/{id}")
    public String modositFelhasznaloSzerep(@PathVariable("id") int id, @RequestParam("szerep") String szerep) {
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloById(id);
        if (felhasznalo != null) {
            felhasznalo.setRole(szerep);
            felhasznaloDAO.updateFelhasznalo(felhasznalo);
        }
        return "redirect:/admin";
    }

    @GetMapping("/felhasznalo/torol/{id}")
    public String torolFelhasznalo(@PathVariable("id") int id) {
        felhasznaloDAO.deleteFelhasznalo(id);
        return "redirect:/admin";
    }

    @GetMapping("/jelolt/torol/{id}")
    public String torolJelolt(@PathVariable("id") int id) {
        jeloltDAO.deleteJelolt(id);
        return "redirect:/admin";
    }

    @PostMapping("/szavazas/modosit/{id}")
    public String modositSzavazas(@PathVariable("id") int id,
                                  @RequestParam("szavazasNev") String nev,
                                  @RequestParam("szavazasLeiras") String leiras,
                                  @RequestParam("szavazasIndulas") String indulas,
                                  @RequestParam("szavazasZarulas") String zarulas) {
        Szavazas szavazas = szavazasDAO.getSzavazasById(id);
        if (szavazas != null) {
            szavazas.setMegnevezes(nev);
            szavazas.setLeiras(leiras);
            szavazas.setIndul(indulas);
            szavazas.setZarul(zarulas);
            szavazasDAO.updateSzavazas(szavazas);
        }
        return "redirect:/admin";
    }

    @GetMapping("/szavazas/torol/{id}")
    public String torolSzavazas(@PathVariable("id") int id) {
        szavazasDAO.deleteSzavazas(id);
        return "redirect:/admin";
    }
}