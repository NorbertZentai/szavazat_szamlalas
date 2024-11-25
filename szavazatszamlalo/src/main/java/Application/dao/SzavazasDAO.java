package Application.dao;

import Application.model.Szavazas;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SzavazasDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SzavazasDAO(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Szavazas> getOsszesSzavazas() {
        String sql = "SELECT * FROM Szavazas";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                Szavazas szavazas = new Szavazas();
                szavazas.setId(rs.getInt("id"));
                szavazas.setMegnevezes(rs.getString("megnevezes"));
                szavazas.setLeiras(rs.getString("leiras"));
                szavazas.setIndul(rs.getTimestamp("indul"));
                szavazas.setZarul(rs.getTimestamp("zarul"));
                return szavazas;
            });
        } catch (Exception e) {
            // Log the error
            throw new RuntimeException("Error fetching szavazas records", e);
        }
    }

    public Szavazas mentesSzavazas(Szavazas szavazas) {
        String sql = "INSERT INTO Szavazas(megnevezes, leiras, indul, zarul) VALUES (:megnevezes, :leiras, :indul, :zarul)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("megnevezes", szavazas.getMegnevezes())
                .addValue("leiras", szavazas.getLeiras())
                .addValue("indul", szavazas.getIndul())
                .addValue("zarul", szavazas.getZarul());
        jdbcTemplate.update(sql, params);
        return szavazas;
    }

    public Szavazas frissitSzavazas(int id, Szavazas szavazas) {
        String sql = "UPDATE Szavazas SET megnevezes = :megnevezes, leiras = :leiras, indul = :indul, zarul = :zarul WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("megnevezes", szavazas.getMegnevezes())
                .addValue("leiras", szavazas.getLeiras())
                .addValue("indul", szavazas.getIndul())
                .addValue("zarul", szavazas.getZarul())
                .addValue("id", id);
        jdbcTemplate.update(sql, params);
        return szavazas;
    }

    public void torolSzavazas(int id) {
        String sql = "DELETE FROM Szavazas WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(sql, params);
    }
}
