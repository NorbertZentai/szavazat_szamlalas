package Application.controller;

import Application.dao.FelhasznaloDAO;
import Application.model.Felhasznalo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/felhasznalok")
public class FelhasznaloController {

    @Autowired
    private FelhasznaloDAO felhasznaloDao;

    @GetMapping
    public List<Felhasznalo> getOsszesFelhasznalo() {
        return felhasznaloDao.getOsszesFelhasznalo();
    }
}
