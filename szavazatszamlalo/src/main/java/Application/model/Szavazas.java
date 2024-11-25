package Application.model;

import java.sql.Timestamp;

public class Szavazas {
    private int id;
    private String megnevezes;
    private String leiras;
    private Timestamp indul;
    private Timestamp zarul;


    public Szavazas() {}

    public Szavazas(String megnevezes, String leiras, Timestamp indul, Timestamp zarul) {
        this.megnevezes = megnevezes;
        this.leiras = leiras;
        this.indul = indul;
        this.zarul = zarul;
    }

    public Szavazas(int id, String megnevezes, String leiras, Timestamp indul, Timestamp zarul) {
        this.id = id;
        this.megnevezes = megnevezes;
        this.leiras = leiras;
        this.indul = indul;
        this.zarul = zarul;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMegnevezes() { return megnevezes; }
    public void setMegnevezes(String megnevezes) { this.megnevezes = megnevezes; }

    public String getLeiras() { return leiras; }
    public void setLeiras(String leiras) { this.leiras = leiras; }

    public Timestamp getIndul() { return indul; }
    public void setIndul(Timestamp indul) { this.indul = indul; }

    public Timestamp getZarul() { return zarul; }
    public void setZarul(Timestamp zarul) { this.zarul = zarul; }
}
