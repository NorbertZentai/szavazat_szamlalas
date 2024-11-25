package Application.controller;

import Application.dao.JeloltDAO;
import Application.model.Jelolt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jeloltek")
public class JeloltController {

    @Autowired
    private JeloltDAO jeloltDao;

    @GetMapping
    public List<Jelolt> getOsszesJelolt() {
        return jeloltDao.getOsszesJelolt();
    }

    @PostMapping
    public Jelolt ujJelolt(@RequestBody Jelolt jelolt) {
        return jeloltDao.mentesJelolt(jelolt);
    }

    @PutMapping("/{id}")
    public Jelolt frissitJelolt(@PathVariable int id, @RequestBody Jelolt jelolt) {
        return jeloltDao.frissitJelolt(id, jelolt);
    }

    @DeleteMapping("/{id}")
    public String torolJelolt(@PathVariable int id) {
        jeloltDao.torolJelolt(id);
        return "A jelölt törölve.";
    }
}
