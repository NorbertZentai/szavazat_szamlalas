package Application.dao;

import Application.model.Szavazas;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SzavazasDAO extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public List<Szavazas> getOsszesSzavazas() {
        String sql = "SELECT * FROM szavazasok";
        return getJdbcTemplate().query(sql, (rs, rowNum) -> {
            Szavazas szavazas = new Szavazas();
            szavazas.setId(rs.getInt("id"));
            szavazas.setMegnevezes(rs.getString("megnevezes"));
            szavazas.setLeiras(rs.getString("leiras"));
            szavazas.setIndul(rs.getTimestamp("indul"));
            szavazas.setZarul(rs.getTimestamp("zarul"));
            return szavazas;
        });
    }

    public Szavazas mentesSzavazas(Szavazas szavazas) {
        String sql = "INSERT INTO szavazasok(megnevezes, leiras, indul, zarul) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, szavazas.getMegnevezes(), szavazas.getLeiras(), szavazas.getIndul(), szavazas.getZarul());
        return szavazas;
    }

    public Szavazas frissitSzavazas(int id, Szavazas szavazas) {
        String sql = "UPDATE szavazasok SET megnevezes = ?, leiras = ?, indul = ?, zarul = ? WHERE id = ?";
        getJdbcTemplate().update(sql, szavazas.getMegnevezes(), szavazas.getLeiras(), szavazas.getIndul(), szavazas.getZarul(), id);
        return szavazas;
    }

    public void torolSzavazas(int id) {
        String sql = "DELETE FROM szavazasok WHERE id = ?";
        getJdbcTemplate().update(sql, id);
    }
}
