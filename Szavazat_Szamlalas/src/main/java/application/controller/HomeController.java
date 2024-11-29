package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import application.dao.SzavazasDAO;
import application.model.Szavazas;

import java.util.List;

@Controller
public class HomeController {

  @Autowired
  private SzavazasDAO szavazasDao;

  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  @GetMapping("/")
  public String homePage(Model model) {
    List<Szavazas> kihirdetettSzavazasok = szavazasDao.getOsszesSzavazas();
    model.addAttribute("kihirdetettSzavazasok", kihirdetettSzavazasok);
    return "index";
  }
}
