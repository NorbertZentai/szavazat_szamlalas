package Application.controller;

import Application.dao.SzavazatDAO;
import Application.model.Szavazat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/szavazatok")
public class SzavazatController {

    @Autowired
    private SzavazatDAO szavazatDao;

    @GetMapping
    public List<Szavazat> getOsszesSzavazat() {
        return szavazatDao.getOsszesSzavazat();
    }

    @PostMapping
    public Szavazat ujSzavazat(@RequestBody Szavazat szavazat) {
        return szavazatDao.mentesSzavazat(szavazat);
    }
}
