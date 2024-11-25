package Application.controller;

import Application.dao.SzavazasDAO;
import Application.model.Szavazas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/szavazasok")
public class SzavazasController {

    @Autowired
    private SzavazasDAO szavazasDao;

    @GetMapping
    public List<Szavazas> getOsszesSzavazas() {
        return szavazasDao.getOsszesSzavazas();
    }

    @PostMapping
    public Szavazas ujSzavazas(@RequestBody Szavazas szavazas) {
        return szavazasDao.mentesSzavazas(szavazas);
    }

    @PutMapping("/{id}")
    public Szavazas frissitSzavazas(@PathVariable int id, @RequestBody Szavazas szavazas) {
        return szavazasDao.frissitSzavazas(id, szavazas);
    }

    @DeleteMapping("/{id}")
    public String torolSzavazas(@PathVariable int id) {
        szavazasDao.torolSzavazas(id);
        return "A szavazás törölve.";
    }
}
