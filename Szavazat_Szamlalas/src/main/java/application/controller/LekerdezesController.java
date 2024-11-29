package application.controller;

import application.dao.LekerdezesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LekerdezesController {

    @Autowired
    private LekerdezesDAO lekerdezesDAO;

    @GetMapping("/lekerdezes")
    public String lekerdezes(@RequestParam(value = "datum", required = false) String datum, Model model) {
        if (datum == null || datum.isEmpty()) {
            datum = "2024-11-01";
        }

        model.addAttribute("szavazasAdatok", lekerdezesDAO.getSzavazasAdatok());
        model.addAttribute("szavazatByJelolt", lekerdezesDAO.getSzavazatByJelolt());
        model.addAttribute("szavazatokBySzavazasokEsJeloltek", lekerdezesDAO.getSzavazatokBySzavazasokEsJeloltek());
        model.addAttribute("legtamogatottabbJelolt", lekerdezesDAO.getLegtamogatottabJelolt());
        model.addAttribute("legtamogatottabbJeloltDatummal", lekerdezesDAO.getlegtamogatottabbJeloltDatummal(datum));
        return "lekerdezes";
    }
}
