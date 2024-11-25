package Application.model;

import java.sql.Timestamp;

public class Felhasznalo {
    private int id;
    private String felhasznalonev;
    private String email;
    private String jelszo;
    private Timestamp legutobbiBelepes;

    public Felhasznalo(){}

    public Felhasznalo(String felhasznalonev, String email, String jelszo, Timestamp legutobbiBelepes) {
        this.felhasznalonev = felhasznalonev;
        this.email = email;
        this.jelszo = jelszo;
        this.legutobbiBelepes = legutobbiBelepes;
    }

    public Felhasznalo(int id, String felhasznalonev, String email, String jelszo, Timestamp legutobbiBelepes) {
        this.id = id;
        this.felhasznalonev = felhasznalonev;
        this.email = email;
        this.jelszo = jelszo;
        this.legutobbiBelepes = legutobbiBelepes;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFelhasznalonev() { return felhasznalonev; }
    public void setFelhasznalonev(String felhasznalonev) { this.felhasznalonev = felhasznalonev; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getJelszo() { return jelszo; }
    public void setJelszo(String jelszo) { this.jelszo = jelszo; }

    public Timestamp getLegutobbiBelepes() { return legutobbiBelepes; }
    public void setLegutobbiBelepes(Timestamp legutobbiBelepes) { this.legutobbiBelepes = legutobbiBelepes; }
}
