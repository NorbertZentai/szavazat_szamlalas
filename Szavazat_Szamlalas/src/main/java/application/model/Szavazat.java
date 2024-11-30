package application.model;

public class Szavazat {
    private int id;
    private int felhasznaloId;
    private int szavazasId;
    private int jeloltId;
    private String idopont;

    public Szavazat(){}

    public Szavazat(int felhasznaloId, int szavazasId, int jeloltId, String idopont) {
        this.felhasznaloId = felhasznaloId;
        this.szavazasId = szavazasId;
        this.jeloltId = jeloltId;
        this.idopont = idopont;
    }

    public Szavazat(int id, int felhasznaloId, int szavazasId, int jeloltId, String idopont) {
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

    public String getIdopont() { return idopont; }
    public void setIdopont(String idopont) { this.idopont = idopont; }
}
