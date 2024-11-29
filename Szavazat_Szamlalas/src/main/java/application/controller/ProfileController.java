package application.controller;

import application.model.Felhasznalo;
import application.model.Szavazat;
import application.model.Jelolt;
import application.dao.FelhasznaloDAO;
import application.dao.SzavazatDAO;
import application.dao.JeloltDAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profil")
public class ProfileController {

    @Autowired
    private FelhasznaloDAO felhasznaloDAO;

    @Autowired
    private SzavazatDAO szavazatDAO;

    @Autowired
    private JeloltDAO jeloltDAO;

    @GetMapping
    public String showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByUsername(username);
        
        List<Szavazat> szavazatok = szavazatDAO.getSzavazatokByFelhasznaloId(felhasznalo.getId());
        List<String> jeloltNevek = new ArrayList<>();
        
        for (Szavazat szavazat : szavazatok) {
            Jelolt jelolt = jeloltDAO.getJeloltById(szavazat.getJeloltId());
            jeloltNevek.add(jelolt.getNev());
        }

        model.addAttribute("felhasznalo", felhasznalo);
        model.addAttribute("szavazatai", szavazatok);
        model.addAttribute("jeloltNevek", jeloltNevek);

        return "profil";
    }

    @PostMapping
    public String updateProfile(@RequestParam String username, @RequestParam String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByUsername(currentUsername);
        if (felhasznalo != null) {
            felhasznalo.setName(username);
            felhasznalo.setEmail(email);
            felhasznaloDAO.updateFelhasznalo(felhasznalo);
            
            if (!currentUsername.equals(username)) {
                Authentication newAuth = new UsernamePasswordAuthenticationToken(felhasznalo, authentication.getCredentials(), authentication.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);
            }
        }
        
        return "redirect:/profil";
    }
}
