package Application.model;

import java.sql.Timestamp;

public class Szavazat {
    private int id;
    private int felhasznaloId;
    private int szavazasId;
    private int jeloltId;
    private Timestamp idopont;

    public Szavazat(){}

    public Szavazat(int felhasznaloId, int szavazasId, int jeloltId, Timestamp idopont) {
        this.felhasznaloId = felhasznaloId;
        this.szavazasId = szavazasId;
        this.jeloltId = jeloltId;
        this.idopont = idopont;
    }

    public Szavazat(int id, int felhasznaloId, int szavazasId, int jeloltId, Timestamp idopont) {
        this.id = id;
        this.felhasznaloId = felhasznaloId;
        this.szavazasId = szavazasId;
        this.jeloltId = jeloltId;
        this.idopont = idopont;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFelhasznaloId() { return felhasznaloId; }
    public void setFelhasznaloId(int felhasznaloId) { this.felhasznaloId = felhasznaloId; }

    public int getSzavazasId() { return szavazasId; }
    public void setSzavazasId(int szavazasId) { this.szavazasId = szavazasId; }

    public int getJeloltId() { return jeloltId; }
    public void setJeloltId(int jeloltId) { this.jeloltId = jeloltId; }

    public Timestamp getIdopont() { return idopont; }
    public void setIdopont(Timestamp idopont) { this.idopont = idopont; }
}
