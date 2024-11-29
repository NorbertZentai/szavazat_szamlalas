package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import application.dao.SzavazasDAO;
import application.model.Szavazas;

@Controller
@RequestMapping("/szavazasok")
public class SzavazasokController {

    @Autowired
    private SzavazasDAO szavazasDao;

    @GetMapping
    public String showSzavazasok(Model model) {
        List<Szavazas> szavazasok = szavazasDao.getOsszesSzavazas();
        model.addAttribute("szavazasok", szavazasok);
        return "szavazasok";
    }
}
