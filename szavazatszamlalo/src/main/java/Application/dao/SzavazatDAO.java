package Application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Application.model.Szavazat;
import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SzavazatDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);  // Kézzel inicializáljuk a JdbcTemplate-et
    }

    public List<Szavazat> getOsszesSzavazat() {
        String sql = "SELECT * FROM Szavazat";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Szavazat szavazat = new Szavazat();
            szavazat.setId(rs.getInt("id"));
            szavazat.setFelhasznaloId(rs.getInt("felhasznalo_id"));
            szavazat.setSzavazasId(rs.getInt("szavazas_id"));
            szavazat.setJeloltId(rs.getInt("jelolt_id"));
            szavazat.setIdopont(rs.getTimestamp("idopont"));
            return szavazat;
        });
    }

    public Szavazat mentesSzavazat(Szavazat szavazat) {
        String sql = "INSERT INTO Szavazat(felhasznalo_id, szavazas_id, jelolt_id, idopont) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, szavazat.getFelhasznaloId(), szavazat.getSzavazasId(), szavazat.getJeloltId(), szavazat.getIdopont());
        return szavazat;
    }
}
