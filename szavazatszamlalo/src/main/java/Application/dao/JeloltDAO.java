package Application.dao;

import Application.model.Jelolt;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JeloltDAO {

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Jelolt> getOsszesJelolt() {
        String sql = "SELECT * FROM Jelolt";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Jelolt jelolt = new Jelolt();
            jelolt.setId(rs.getInt("id"));
            jelolt.setNev(rs.getString("nev"));
            jelolt.setSzulDatum(rs.getDate("szuletesi_datum"));
            jelolt.setFoglalkozas(rs.getString("foglalkozas"));
            jelolt.setProgram(rs.getString("program"));
            return jelolt;
        });
    }

    public Jelolt mentesJelolt(Jelolt jelolt) {
        String sql = "INSERT INTO Jelolt(nev, szuletesi_datum, foglalkozas, program) VALUES (:nev, :szuletesi_datum, :foglalkozas, :program)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("nev", jelolt.getNev())
                .addValue("szuletesi_datum", jelolt.getSzulDatum())
                .addValue("foglalkozas", jelolt.getFoglalkozas())
                .addValue("program", jelolt.getProgram());
        jdbcTemplate.update(sql, params);
        return jelolt;
    }

    public Jelolt frissitJelolt(int id, Jelolt jelolt) {
        String sql = "UPDATE Jelolt SET nev = :nev, szuletesi_datum = :szuletesi_datum, foglalkozas = :foglalkozas, program = :program WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("nev", jelolt.getNev())
                .addValue("szuletesi_datum", jelolt.getSzulDatum())
                .addValue("foglalkozas", jelolt.getFoglalkozas())
                .addValue("program", jelolt.getProgram())
                .addValue("id", id);
        jdbcTemplate.update(sql, params);
        return jelolt;
    }

    public void torolJelolt(int id) {
        String sql = "DELETE FROM Jelolt WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(sql, params);
    }
}
