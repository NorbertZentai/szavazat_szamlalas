package Application.dao;

import Application.model.Jelolt;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JeloltDAO extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public List<Jelolt> getOsszesJelolt() {
        String sql = "SELECT * FROM jeloltek";
        return getJdbcTemplate().query(sql, (rs, rowNum) -> {
            Jelolt jelolt = new Jelolt();
            jelolt.setId(rs.getInt("id"));
            jelolt.setNev(rs.getString("nev"));
            jelolt.setSzulDatum(rs.getDate("szulDatum"));
            jelolt.setFoglalkozas(rs.getString("foglalkozas"));
            jelolt.setProgram(rs.getString("program"));
            return jelolt;
        });
    }

    public Jelolt mentesJelolt(Jelolt jelolt) {
        String sql = "INSERT INTO jeloltek(nev, szulDatum, foglalkozas, program) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, jelolt.getNev(), jelolt.getSzulDatum(), jelolt.getFoglalkozas(), jelolt.getProgram());
        return jelolt;
    }

    public Jelolt frissitJelolt(int id, Jelolt jelolt) {
        String sql = "UPDATE jeloltek SET nev = ?, szulDatum = ?, foglalkozas = ?, program = ? WHERE id = ?";
        getJdbcTemplate().update(sql, jelolt.getNev(), jelolt.getSzulDatum(), jelolt.getFoglalkozas(), jelolt.getProgram(), id);
        return jelolt;
    }

    public void torolJelolt(int id) {
        String sql = "DELETE FROM jeloltek WHERE id = ?";
        getJdbcTemplate().update(sql, id);
    }
}
