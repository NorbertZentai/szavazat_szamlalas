package application.controller;

import application.dao.JeloltDAO;
import application.dao.SzavazasDAO;
import application.dao.SzavazatDAO;
import application.model.Jelolt;
import application.model.Szavazas;
import application.model.Szavazat;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/szavazas")
public class SzavazasController {

    @Autowired
    private SzavazasDAO szavazasDao;
    @Autowired
    private SzavazatDAO szavazatDao;
    @Autowired
    private JeloltDAO jeloltDao;

    @GetMapping("/{id}")
    public String getSzavazas(@PathVariable int id, @RequestParam("felhasznaloId") int felhasznaloId, Model model) {
        Szavazas szavazas = szavazasDao.getSzavazasById(id);
        
        Jelolt jelolt1 = jeloltDao.getJeloltById(szavazas.getJelolt1Id());
        Jelolt jelolt2 = jeloltDao.getJeloltById(szavazas.getJelolt2Id());
        Jelolt jelolt3 = jeloltDao.getJeloltById(szavazas.getJelolt3Id());
        
        
        boolean marSzavazott = szavazatDao.checkIfUserVoted(id, felhasznaloId);
        
        model.addAttribute("szavazas", szavazas);
        model.addAttribute("jelolt1", jelolt1);
        model.addAttribute("jelolt2", jelolt2);
        model.addAttribute("jelolt3", jelolt3);
        model.addAttribute("marSzavazott", marSzavazott);
    
        return "szavazas";
    }

    @PostMapping("/{id}")
    public String szavazas(@PathVariable int id, @RequestParam("jelolt") int jeloltId, @RequestParam("felhasznaloId") int felhasznaloId, Model model) {

        String idopont = new Timestamp(System.currentTimeMillis()).toString();

        Szavazat szavazat = new Szavazat();
        szavazat.setFelhasznaloId(felhasznaloId);
        szavazat.setSzavazasId(id);
        szavazat.setJeloltId(jeloltId);
        szavazat.setIdopont(idopont);

        szavazatDao.insertSzavazat(szavazat);

        return "redirect:/szavazasok";
    }
}
