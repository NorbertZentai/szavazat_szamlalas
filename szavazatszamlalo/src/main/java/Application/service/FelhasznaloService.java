package Application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import Application.model.Felhasznalo;

@Service
public class FelhasznaloService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Példa: Felhasználó adatainak lekérdezése
    public List<Felhasznalo> getAllFelhasznalo() {
        String sql = "SELECT * FROM Felhasznalo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> 
            new Felhasznalo(
                rs.getInt("id"),
                rs.getString("felhasznalonev"),
                rs.getString("email"),
                rs.getString("jelszo"),
                rs.getTimestamp("legutobbi_belepes")
            )
        );
    }

    // Példa: Felhasználó hozzáadása
    public void addFelhasznalo(Felhasznalo felhasznalo) {
        String sql = "INSERT INTO Felhasznalo (felhasznalonev, email, jelszo, legutobbi_belepes) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            felhasznalo.getFelhasznalonev(), 
            felhasznalo.getEmail(), 
            felhasznalo.getJelszo(),
            felhasznalo.getLegutobbiBelepes()
        );
    }
}
