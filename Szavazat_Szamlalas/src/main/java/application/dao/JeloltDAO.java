package application.dao;

import application.model.Jelolt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JeloltDAO extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public List<Jelolt> getOsszesJelolt() {
        String sql = "SELECT * FROM Jelolt";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Jelolt> jeloltek = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Jelolt jelolt = new Jelolt();
            jelolt.setId((Integer) row.get("id"));
            jelolt.setNev((String) row.get("nev"));
            jelolt.setSzulDatum((Date) row.get("szuletesi_datum"));
            jelolt.setFoglalkozas((String) row.get("foglalkozas"));
            jelolt.setProgram((String) row.get("program"));
            jeloltek.add(jelolt);
        }

        return jeloltek;
    }

    public void insertJelolt(Jelolt jelolt) {
        try {
            String sql = "INSERT INTO Jelolt(nev, szuletesi_datum, foglalkozas, program) VALUES (?, ?, ?, ?)";
            getJdbcTemplate().update(sql,
                jelolt.getNev(),
                jelolt.getSzulDatum(),
                jelolt.getFoglalkozas(),
                jelolt.getProgram()
            );
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a jelölt mentése", ex);
        }
    }

    public void updateJelolt(int id, Jelolt jelolt) {
        try {
            String sql = "UPDATE Jelolt SET nev = ?, szuletesi_datum = ?, foglalkozas = ?, program = ? WHERE id = ?";
            getJdbcTemplate().update(sql,
                jelolt.getNev(),
                jelolt.getSzulDatum(),
                jelolt.getFoglalkozas(),
                jelolt.getProgram(),
                id
            );
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a jelölt frissítése", ex);
        }
    }

    public void deleteJelolt(int id) {
        try {
            String sql = "DELETE FROM Jelolt WHERE id = ?";
            getJdbcTemplate().update(sql, id);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a jelölt törlése", ex);
        }
    }

    public Jelolt getJeloltById(int id) {
        String sql = "SELECT * FROM Jelolt WHERE id = ?";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, id);

        List<Jelolt> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Jelolt jelolt = new Jelolt();
            jelolt.setId((Integer) row.get("id"));
            jelolt.setNev((String) row.get("nev"));
            jelolt.setSzulDatum((Date) row.get("szuletesi_datum"));
            jelolt.setFoglalkozas((String) row.get("foglalkozas"));
            jelolt.setProgram((String) row.get("program"));
            result.add(jelolt);
        }

        return result.isEmpty() ? null : result.get(0);
    }
}