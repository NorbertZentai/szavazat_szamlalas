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
                "COUNT(szavazat.id) AS szavazatok_szama " +  // Ellenőrizd, hogy szavazat.id létezik-e az adatbázisban
                "FROM Jelolt jelolt " +
                "JOIN Szavazat szavazat ON jelolt.id = szavazat.jelolt_id " +
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
                "szavazas.megnevezes AS szavazas_nev, " +
                "jelolt.nev AS jelolt_nev, " +
                "COUNT(szavazat.id) AS szavazatok_szama " + // Ellenőrizd, hogy szavazat.id létezik-e az adatbázisban
                "FROM Szavazas szavazas " +
                "JOIN Szavazat szavazat ON szavazas.id = szavazat.szavazas_id " +
                "JOIN Jelolt jelolt ON szavazat.jelolt_id = jelolt.id " +
                "GROUP BY szavazas.id, jelolt.id " +
                "ORDER BY szavazas_nev";

        try {
            return getJdbcTemplate().queryForList(query);
        } catch (DataAccessException e) {
            throw new RuntimeException("Hiba a szavazások és jelöltek szavazatainak lekérdezése során.", e);
        }
    }

    public List<Map<String, Object>> getLegtamogatottabJelolt() {
        String query = "SELECT jelolt.nev AS jelolt_nev, COUNT(szavazat.id) AS szavazatok_szama " +  // Ellenőrizd, hogy szavazat.id létezik-e az adatbázisban
                "FROM Jelolt jelolt " +
                "JOIN Szavazat szavazat ON jelolt.id = szavazat.jelolt_id " +
                "WHERE jelolt.id IN (" +
                "    SELECT jelolt_id " +
                "    FROM Szavazat " +
                "    GROUP BY jelolt_id " +
                "    HAVING COUNT(id) = (" +
                "        SELECT MAX(szavazatok_szama) " +
                "        FROM (" +
                "            SELECT COUNT(id) AS szavazatok_szama " +
                "            FROM Szavazat " +
                "            GROUP BY jelolt_id" +
                "        ) AS max_szavazatok" +
                "    )" +
                ") " +
                "GROUP BY jelolt.id";

        try {
            return getJdbcTemplate().queryForList(query);
        } catch (DataAccessException e) {
            throw new RuntimeException("Hiba a legjobban támogatott jelöltek lekérdezése során.", e);
        }
    }

    public List<Map<String, Object>> getlegtamogatottabbJeloltDatummal(String date) {
        String query = "SELECT jelolt.nev, COUNT(szavazat.id) AS szavazatok_szama " +
                "FROM Jelolt jelolt " +
                "JOIN Szavazat szavazat ON jelolt.id = szavazat.jelolt_id " +
                "WHERE szavazat.idopont > ? " +
                "GROUP BY jelolt.id " +
                "ORDER BY szavazatok_szama DESC " +
                "LIMIT 1";
        try {
            return getJdbcTemplate().queryForList(query, date);
        } catch (DataAccessException e) {
            throw new RuntimeException("Hiba a legjobban támogatott jelölt lekérdezése során egy adott időpont után.", e);
        }
    }
}
