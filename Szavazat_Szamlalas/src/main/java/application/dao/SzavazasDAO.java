package application.dao;

import application.model.Jelolt;
import application.model.Szavazas;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SzavazasDAO {

    private final JdbcTemplate jdbcTemplate;

    public SzavazasDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Szavazas> getOsszesSzavazas() {
        String sql = "SELECT * FROM Szavazas";
        return jdbcTemplate.query(sql, new RowMapper<Szavazas>() {
            @Override
            public Szavazas mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Szavazas szavazas = new Szavazas();
                szavazas.setId(rs.getInt("id"));
                szavazas.setMegnevezes(rs.getString("megnevezes"));
                szavazas.setLeiras(rs.getString("leiras"));
                szavazas.setIndul(rs.getString("indul"));
                szavazas.setZarul(rs.getString("zarul"));
                szavazas.setJelolt1Id(rs.getInt("jelolt1_id"));
                szavazas.setJelolt2Id(rs.getInt("jelolt2_id"));
                szavazas.setJelolt3Id(rs.getInt("jelolt3_id"));
                return szavazas;
            }
        });
    }

    public Szavazas getSzavazasById(int id) {
        String sql = "SELECT * FROM Szavazas WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Szavazas szavazas = new Szavazas();
            szavazas.setId(rs.getInt("id"));
            szavazas.setMegnevezes(rs.getString("megnevezes"));
            szavazas.setLeiras(rs.getString("leiras"));
            szavazas.setIndul(rs.getString("indul"));
            szavazas.setZarul(rs.getString("zarul"));
            szavazas.setJelolt1Id(rs.getInt("jelolt1_id"));
            szavazas.setJelolt2Id(rs.getInt("jelolt2_id"));
            szavazas.setJelolt3Id(rs.getInt("jelolt3_id"));
            return szavazas;
        });
    }

    public List<Jelolt> getJeloltekForSzavazas(int szavazasId) {
        String sql = "SELECT * FROM Jelolt WHERE id IN (" +
                     "SELECT jelolt1_id FROM Szavazas WHERE id = ? " +
                     "UNION " +
                     "SELECT jelolt2_id FROM Szavazas WHERE id = ? " +
                     "UNION " +
                     "SELECT jelolt3_id FROM Szavazas WHERE id = ?)";
        try {
            return jdbcTemplate.query(sql, new Object[]{szavazasId, szavazasId, szavazasId}, (rs, rowNum) -> {
                Jelolt jelolt = new Jelolt();
                jelolt.setId(rs.getInt("id"));
                jelolt.setNev(rs.getString("nev"));
                return jelolt;
            });
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a jelöltek lekérése", ex);
        }
    }

    public void regisztralSzavazat(int szavazasId, int jeloltId) {
        String sql = "INSERT INTO Szavazat (szavazas_id, jelolt_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, szavazasId, jeloltId);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a szavazat regisztrálása", ex);
        }
    }

    public Szavazas insertSzavazas(Szavazas szavazas) {
        String sql = "INSERT INTO Szavazas(megnevezes, leiras, indul, zarul, jelolt1_id, jelolt2_id, jelolt3_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,
                szavazas.getMegnevezes(),
                szavazas.getLeiras(),
                szavazas.getIndul(),
                szavazas.getZarul(),
                szavazas.getJelolt1Id(),
                szavazas.getJelolt2Id(),
                szavazas.getJelolt3Id()
            );
            return szavazas;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a szavazás mentése", ex);
        }
    }

    public Szavazas updateSzavazas(Szavazas szavazas) {
        String sql = "UPDATE Szavazas SET megnevezes = ?, leiras = ?, indul = ?, zarul = ?, " +
                     "jelolt1_id = ?, jelolt2_id = ?, jelolt3_id = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql,
                szavazas.getMegnevezes(),
                szavazas.getLeiras(),
                szavazas.getIndul(),
                szavazas.getZarul(),
                szavazas.getJelolt1Id(),
                szavazas.getJelolt2Id(),
                szavazas.getJelolt3Id(),
                szavazas.getId()
            );
            return szavazas;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a szavazás frissítése", ex);
        }
    }

    public void deleteSzavazas(int id) {
        String sql = "DELETE FROM Szavazas WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a szavazás törlése", ex);
        }
    }
}
