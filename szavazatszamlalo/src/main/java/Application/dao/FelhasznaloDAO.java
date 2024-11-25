package Application.dao;

import Application.model.Felhasznalo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class FelhasznaloDAO {

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Felhasznalo> getOsszesFelhasznalo() {
        String sql = "SELECT * FROM Felhasznalo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Felhasznalo felhasznalo = new Felhasznalo();
            felhasznalo.setId(rs.getInt("id"));
            felhasznalo.setNev(rs.getString("felhasznalo_nev"));
            felhasznalo.setEmail(rs.getString("email"));
            felhasznalo.setJelszo(rs.getString("jelszo"));
            felhasznalo.setLegutobbiBelepes(rs.getTimestamp("legutobbi_belepes"));
            felhasznalo.setSzerep(rs.getString("szerep"));
            return felhasznalo;
        });
    }

    public Felhasznalo mentesFelhasznalo(Felhasznalo felhasznalo) {
        String sql = "INSERT INTO Felhasznalo(felhasznalo_nev, email, jelszo) VALUES (:felhasznalo_nev, :email, :jelszo)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("felhasznalo_nev", felhasznalo.getNev())
                .addValue("email", felhasznalo.getEmail())
                .addValue("jelszo", felhasznalo.getJelszo())
                .addValue("szerep", felhasznalo.getSzerep());
        jdbcTemplate.update(sql, params);
        return felhasznalo;
    }

    public Optional<Felhasznalo> keresFelhasznalo(String felhasznalonev, String jelszo) {
        String sql = "SELECT * FROM Felhasznalo WHERE felhasznalo_nev = :felhasznalo_nev AND jelszo = :jelszo";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("felhasznalo_nev", felhasznalonev)
                .addValue("jelszo", jelszo);

        List<Felhasznalo> felhasznalok = jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Felhasznalo f = new Felhasznalo();
            f.setId(rs.getInt("id"));
            f.setNev(rs.getString("felhasznalo_nev"));
            f.setEmail(rs.getString("email"));
            f.setJelszo(rs.getString("jelszo"));
            f.setLegutobbiBelepes(rs.getTimestamp("legutobbi_belepes"));
            f.setSzerep(rs.getString("szerep"));
            return f;
        });
        return felhasznalok.isEmpty() ? Optional.empty() : Optional.of(felhasznalok.get(0));
    }

    public Felhasznalo getFelhasznaloByEmail(String email) {
        String sql = "SELECT * FROM Felhasznalo WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);

        List<Felhasznalo> felhasznalok = jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Felhasznalo f = new Felhasznalo();
            f.setId(rs.getInt("id"));
            f.setNev(rs.getString("felhasznalo_nev"));
            f.setEmail(rs.getString("email"));
            f.setJelszo(rs.getString("jelszo"));
            f.setLegutobbiBelepes(rs.getTimestamp("legutobbi_belepes"));
            f.setSzerep(rs.getString("szerep"));
            return f;
        });

        return felhasznalok.isEmpty() ? null : felhasznalok.get(0);
    }
}
