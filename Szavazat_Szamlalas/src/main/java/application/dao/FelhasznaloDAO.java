package application.dao;

import application.model.Felhasznalo;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class FelhasznaloDAO extends JdbcDaoSupport {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void insertFelhasznalo(Felhasznalo felhasznalo) {
        try {
            String sql = "INSERT INTO Felhasznalo(felhasznalonev, email, jelszo, legutobbi_belepes, szerep) VALUES (?, ?, ?, ?, ?)";
            getJdbcTemplate().update(sql,
                felhasznalo.getUsername(),
                felhasznalo.getEmail(),
                felhasznalo.getPassword(),
                new Date(System.currentTimeMillis()),
                felhasznalo.getRole()
            );
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a felhasználó mentése", ex);
        }
    }

    public void updateFelhasznalo(Felhasznalo felhasznalo) {
        try {
            String sql = "UPDATE Felhasznalo SET felhasznalonev = ?, email = ?, szerep = ? WHERE id = ?";
            
            getJdbcTemplate().update(sql,
                    felhasznalo.getUsername(),
                    felhasznalo.getEmail(),
                    felhasznalo.getRole(),
                    felhasznalo.getId()
            );
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a felhasználó frissítése", ex);
        }
    }


    public Felhasznalo getFelhasznaloById(int id) {
        String sql = "SELECT * FROM Felhasznalo WHERE id=?";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, id);

        List<Felhasznalo> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Felhasznalo user = new Felhasznalo();
            user.setId((Integer) row.get("id"));
            user.setName((String) row.get("felhasznalonev"));
            user.setEmail((String) row.get("email"));
            user.setPassword((String) row.get("jelszo"));
            user.setLegutobbiBelepes((Date) row.get("legutobbi_belepes"));
            user.setRole((String) row.get("szerep"));
            result.add(user);
        }

        return result.isEmpty() ? null : result.get(0);
    }

    public Felhasznalo getFelhasznaloByUsername(String username) {
        String sql = "SELECT * FROM Felhasznalo WHERE felhasznalonev=?";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, username);

        List<Felhasznalo> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Felhasznalo user = new Felhasznalo();
            user.setId((Integer) row.get("id"));
            user.setName((String) row.get("felhasznalonev"));
            user.setEmail((String) row.get("email"));
            user.setPassword((String) row.get("jelszo"));
            user.setLegutobbiBelepes((Date) row.get("legutobbi_belepes"));
            user.setRole((String) row.get("szerep"));
            result.add(user);
        }

        return result.isEmpty() ? null : result.get(0);
    }

    public void updateLastLogin(int userId, Date lastLoginDate) {
        try {
            String sql = "UPDATE Felhasznalo SET legutobbi_belepes = ? WHERE id = ?";
            getJdbcTemplate().update(sql, lastLoginDate, userId);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Nem sikerült a felhasználó legutóbbi belépésének frissítése", ex);
        }
    }
    

    public List<Felhasznalo> listFelhasznalo() {
        String sql = "SELECT * FROM Felhasznalo";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Felhasznalo> felhasznalok = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Felhasznalo user = new Felhasznalo();
            user.setId((Integer) row.get("id"));
            user.setName((String) row.get("felhasznalonev"));
            user.setEmail((String) row.get("email"));
            user.setPassword((String) row.get("jelszo"));
            user.setLegutobbiBelepes((Date) row.get("legutobbi_belepes"));
            user.setRole((String) row.get("szerep"));
            felhasznalok.add(user);
        }
        
        return felhasznalok;
    }

    public void deleteFelhasznalo(int id) {
        String sql = "DELETE FROM Felhasznalo WHERE id = ?";
        getJdbcTemplate().update(sql, id);
    }
    
}