package application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import application.model.Szavazat;
import javax.annotation.PostConstruct;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SzavazatDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Szavazat insertSzavazat(Szavazat szavazat) {
        String sql = "INSERT INTO Szavazat(felhasznalo_id, szavazas_id, jelolt_id, idopont) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, szavazat.getFelhasznaloId(), szavazat.getSzavazasId(), szavazat.getJeloltId(), szavazat.getIdopont());
        return szavazat;
    }

    public List<Szavazat> getOsszesSzavazat() {
        String sql = "SELECT * FROM Szavazat";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Szavazat szavazat = new Szavazat();
            szavazat.setId(rs.getInt("id"));
            szavazat.setFelhasznaloId(rs.getInt("felhasznalo_id"));
            szavazat.setSzavazasId(rs.getInt("szavazas_id"));
            szavazat.setJeloltId(rs.getInt("jelolt_id"));
            szavazat.setIdopont(rs.getString("idopont"));
            return szavazat;
        });
    }

    public List<Szavazat> getSzavazatokByFelhasznaloId(int felhasznaloId) {
        String sql = "SELECT * FROM Szavazat WHERE felhasznalo_id = ?";
        return jdbcTemplate.query(sql, new Object[]{felhasznaloId}, (rs, rowNum) -> {
            Szavazat szavazat = new Szavazat();
            szavazat.setId(rs.getInt("id"));
            szavazat.setFelhasznaloId(rs.getInt("felhasznalo_id"));
            szavazat.setSzavazasId(rs.getInt("szavazas_id"));
            szavazat.setJeloltId(rs.getInt("jelolt_id"));
            szavazat.setIdopont(rs.getString("idopont"));
            return szavazat;
        });
    }

    public boolean checkIfUserVoted(int szavazasId, int felhasznaloId) {
        String sql = "SELECT COUNT(*) FROM Szavazat WHERE szavazas_id = ? AND felhasznalo_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{szavazasId, felhasznaloId}, Integer.class);
        return count > 0;
    }    
}