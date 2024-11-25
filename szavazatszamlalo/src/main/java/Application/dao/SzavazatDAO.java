package Application.dao;

import Application.model.Szavazat;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SzavazatDAO extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public List<Szavazat> getOsszesSzavazat() {
        String sql = "SELECT * FROM szavazatok";
        return getJdbcTemplate().query(sql, (rs, rowNum) -> {
            Szavazat szavazat = new Szavazat();
            szavazat.setId(rs.getInt("id"));
            szavazat.setFelhasznaloId(rs.getInt("felhasznaloId"));
            szavazat.setSzavazasId(rs.getInt("szavazasId"));
            szavazat.setJeloltId(rs.getInt("jeloltId"));
            szavazat.setIdopont(rs.getTimestamp("idopont"));
            return szavazat;
        });
    }

    public Szavazat mentesSzavazat(Szavazat szavazat) {
        String sql = "INSERT INTO szavazatok(felhasznaloId, szavazasId, jeloltId, idopont) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, szavazat.getFelhasznaloId(), szavazat.getSzavazasId(), szavazat.getJeloltId(), szavazat.getIdopont());
        return szavazat;
    }
}
