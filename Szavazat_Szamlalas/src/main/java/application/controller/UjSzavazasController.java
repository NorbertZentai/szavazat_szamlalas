package application.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.dao.JeloltDAO;
import application.dao.SzavazasDAO;
import application.model.Jelolt;
import application.model.Szavazas;

@Controller
public class UjSzavazasController {

    @Autowired
    private SzavazasDAO szavazasDAO;

    @Autowired
    private JeloltDAO jeloltDAO;

    @GetMapping("/ujszavazas")
    public String showCreateForm(Model model) {
        List<Jelolt> jeloltek = jeloltDAO.getOsszesJelolt();
        model.addAttribute("jeloltek", jeloltek);
        return "ujszavazas";
    }

    @PostMapping("/ujszavazas")
    public String createSzavazas(
            @RequestParam("megnevezes") String megnevezes,
            @RequestParam("leiras") String leiras,
            @RequestParam("indul") String indul,
            @RequestParam("zarul") String zarul,
            @RequestParam("jelolt1_id") int jelolt1Id,
            @RequestParam("jelolt2_id") int jelolt2Id,
            @RequestParam("jelolt3_id") int jelolt3Id,
            Model model) {

        if (jelolt1Id == 0 || jelolt2Id == 0 || jelolt3Id == 0) {
            model.addAttribute("errorMessage", "Minden jelöltet ki kell választani!");
            return "ujszavazas";
        }

        Szavazas szavazas = new Szavazas();
        szavazas.setMegnevezes(megnevezes);
        szavazas.setLeiras(leiras);
        szavazas.setIndul(indul);
        szavazas.setZarul(zarul);
        szavazas.setJelolt1Id(jelolt1Id);
        szavazas.setJelolt2Id(jelolt2Id);
        szavazas.setJelolt3Id(jelolt3Id);

        szavazasDAO.insertSzavazas(szavazas);

        model.addAttribute("message", "Szavazás sikeresen létrehozva!");
        return "redirect:/szavazasok";
    }
}
