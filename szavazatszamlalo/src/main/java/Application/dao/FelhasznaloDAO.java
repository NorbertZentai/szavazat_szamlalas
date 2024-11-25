package Application.dao;

import Application.model.Felhasznalo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class FelhasznaloDAO extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    private boolean isJdbcTemplateValid() {
        return getJdbcTemplate() != null;
    }

    public List<Felhasznalo> getOsszesFelhasznalo() {
        if (!isJdbcTemplateValid()) {
            throw new IllegalStateException("JdbcTemplate is not properly initialized.");
        }
        String sql = "SELECT * FROM felhasznalok";
        return getJdbcTemplate().query(sql, (rs, rowNum) -> {
            Felhasznalo felhasznalo = new Felhasznalo();
            felhasznalo.setId(rs.getInt("id"));
            felhasznalo.setFelhasznalonev(rs.getString("felhasznalonev"));
            felhasznalo.setEmail(rs.getString("email"));
            felhasznalo.setJelszo(rs.getString("jelszo"));
            felhasznalo.setLegutobbiBelepes(rs.getTimestamp("legutobbiBelepes"));
            return felhasznalo;
        });
    }

    public Felhasznalo mentesFelhasznalo(Felhasznalo felhasznalo) {
        if (!isJdbcTemplateValid()) {
            throw new IllegalStateException("JdbcTemplate is not properly initialized.");
        }
        String sql = "INSERT INTO felhasznalok(felhasznalonev, email, jelszo) VALUES (?, ?, ?)";
        getJdbcTemplate().update(sql, felhasznalo.getFelhasznalonev(), felhasznalo.getEmail(), felhasznalo.getJelszo());
        return felhasznalo;
    }

    public Optional<Felhasznalo> keresFelhasznalo(String felhasznalonev, String jelszo) {
        if (!isJdbcTemplateValid()) {
            throw new IllegalStateException("JdbcTemplate is not properly initialized.");
        }
        String sql = "SELECT * FROM felhasznalok WHERE felhasznalonev = ? AND jelszo = ?";
        List<Felhasznalo> felhasznalok = getJdbcTemplate().query(sql, new Object[]{felhasznalonev, jelszo},
                (rs, rowNum) -> {
                    Felhasznalo f = new Felhasznalo();
                    f.setId(rs.getInt("id"));
                    f.setFelhasznalonev(rs.getString("felhasznalonev"));
                    f.setEmail(rs.getString("email"));
                    f.setJelszo(rs.getString("jelszo"));
                    f.setLegutobbiBelepes(rs.getTimestamp("legutobbiBelepes"));
                    return f;
                });
        return felhasznalok.isEmpty() ? Optional.empty() : Optional.of(felhasznalok.get(0));
    }

    public Felhasznalo getFelhasznaloByEmail(String email) {
        String sql = "SELECT * FROM felhasznalok WHERE email = ?";
        List<Felhasznalo> felhasznalok = getJdbcTemplate().query(sql, new Object[]{email}, (rs, rowNum) -> {
            Felhasznalo f = new Felhasznalo();
            f.setId(rs.getInt("id"));
            f.setFelhasznalonev(rs.getString("felhasznalonev"));
            f.setEmail(rs.getString("email"));
            f.setJelszo(rs.getString("jelszo"));
            f.setLegutobbiBelepes(rs.getTimestamp("legutobbiBelepes"));
            return f;
        });

        return felhasznalok.isEmpty() ? null : felhasznalok.get(0);  // Ha nincs találat, null-t adunk vissza
    }
}
