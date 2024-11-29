package application.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class LekerdezesDAO extends JdbcDaoSupport {

    public LekerdezesDAO(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public List<Map<String, Object>> getSzavazasAdatok() {
        String query = "SELECT " +
                "szavazas.megnevezes AS szavazas_neve, " +
                "szavazas.leiras AS szavazas_leirasa, " +
                "COUNT(DISTINCT szavazat.felhasznalo_id) AS szavazatok_szama " +
                "FROM Szavazas szavazas " +
                "JOIN Szavazat szavazat ON szavazas.id = szavazat.szavazas_id " +
                "GROUP BY szavazas.id " +
                "ORDER BY szavazas.megnevezes";
        try {
            return getJdbcTemplate().queryForList(query);
        } catch (DataAccessException e) {
            throw new RuntimeException("Hiba a szavazások lekérdezése során.", e);
        }
    }

    public List<Map<String, Object>> getSzavazatByJelolt() {
        String query = "SELECT " +
                "jelolt.nev AS jelolt_nev, " +
                "COUNT(s.id) AS szavazatok_szama " +
                "FROM Jelolt jelolt " +
                "JOIN Szavazat s ON jelolt.id = s.jelolt_id " +
                "GROUP BY jelolt.id " +
                "ORDER BY szavazatok_szama DESC";

        try {
            return getJdbcTemplate().queryForList(query);
        } catch (DataAccessException e) {
            throw new RuntimeException("Hiba a jelöltek szavazatainak lekérdezése során.", e);
        }
    }

    public List<Map<String, Object>> getSzavazatokBySzavazasokEsJeloltek() {
        String query = "SELECT " +
                "sz.megnevezes AS szavazas_nev, " +
                "j.nev AS jelolt_nev, " +
                "COUNT(s.id) AS szavazatok_szama " +
                "FROM Szavazas sz " +
                "JOIN Szavazat s ON sz.id = s.szavazas_id " +
                "JOIN Jelolt j ON s.jelolt_id = j.id " +
                "GROUP BY sz.id, j.id " +
                "ORDER BY szavazas_nev";

        try {
            return getJdbcTemplate().queryForList(query);
        } catch (DataAccessException e) {
            throw new RuntimeException("Hiba a szavazások és jelöltek szavazatainak lekérdezése során.", e);
        }
    }

    public List<Map<String, Object>> getLegtamogatottabJelolt() {
        String query = "SELECT j.nev AS jelolt_nev, COUNT(s.id) AS szavazatok_szama " +
                "FROM Jelolt j " +
                "JOIN Szavazat s ON j.id = s.jelolt_id " +
                "WHERE j.id IN (" +
                "    SELECT jelolt_id " +
                "    FROM Szavazat " +
                "    GROUP BY jelolt_id " +
                "    HAVING COUNT(id) = (" +
                "        SELECT MAX(szavazatok_szama) " +
                "        FROM (" +
                "            SELECT COUNT(id) AS szavazatok_szama " +
                "            FROM Szavazat " +
                "            GROUP BY jelolt_id" +
                "        ) AS subquery" +
                "    )" +
                ") " +
                "GROUP BY j.id";

        try {
            return getJdbcTemplate().queryForList(query);
        } catch (DataAccessException e) {
            throw new RuntimeException("Hiba a legjobban támogatott jelöltek lekérdezése során.", e);
        }
    }

    public List<Map<String, Object>> getlegtamogatottabbJeloltDatummal(String date) {
        String query = "SELECT j.nev, COUNT(s.id) AS szavazatok_szama " +
                "FROM Jelolt j " +
                "JOIN Szavazat s ON j.id = s.jelolt_id " +
                "WHERE s.idopont > ? " +
                "GROUP BY j.id " +
                "ORDER BY szavazatok_szama DESC " +
                "LIMIT 1";
        try {
            return getJdbcTemplate().queryForList(query, date);
        } catch (DataAccessException e) {
            throw new RuntimeException("Hiba a legjobban támogatott jelölt lekérdezése során egy adott időpont után.", e);
        }
    }
}
