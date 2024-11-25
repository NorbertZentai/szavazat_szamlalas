package Application.model;

import java.sql.Timestamp;

public class Felhasznalo {
    private int id;
    private String nev;
    private String email;
    private String jelszo;
    private String szerep;
    private Timestamp legutobbiBelepes;

    public Felhasznalo(){}

    public Felhasznalo(String felhasznalonev, String email, String jelszo, Timestamp legutobbiBelepes) {
        this.nev = felhasznalonev;
        this.email = email;
        this.jelszo = jelszo;
        this.legutobbiBelepes = legutobbiBelepes;
        this.szerep = "ROLE_USER";
    }

    public Felhasznalo(int id, String felhasznalonev, String email, String jelszo, Timestamp legutobbiBelepes) {
        this.id = id;
        this.nev = felhasznalonev;
        this.email = email;
        this.jelszo = jelszo;
        this.legutobbiBelepes = legutobbiBelepes;
        this.szerep = "ROLE_USER";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNev() { return nev; }
    public void setNev(String felhasznalonev) { this.nev = felhasznalonev; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getJelszo() { return jelszo; }
    public void setJelszo(String jelszo) { this.jelszo = jelszo; }

    public Timestamp getLegutobbiBelepes() { return legutobbiBelepes; }
    public void setLegutobbiBelepes(Timestamp legutobbiBelepes) { this.legutobbiBelepes = legutobbiBelepes; }

    public String getSzerep() { return szerep; }
    public void setSzerep(String szerep) { this.szerep = szerep; }
}
