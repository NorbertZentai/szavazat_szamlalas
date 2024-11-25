package Application.model;

import java.sql.Date;

public class Jelolt {
    private int id;
    private String nev;
    private Date szulDatum;
    private String foglalkozas;
    private String program;

    public Jelolt(){}

    public Jelolt(String nev, Date szulDatum, String foglalkozas, String program) {
        this.nev = nev;
        this.szulDatum = szulDatum;
        this.foglalkozas = foglalkozas;
        this.program = program;
    }

    public Jelolt(int id, String nev, Date szulDatum, String foglalkozas, String program) {
        this.id = id;
        this.nev = nev;
        this.szulDatum = szulDatum;
        this.foglalkozas = foglalkozas;
        this.program = program;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNev() { return nev; }
    public void setNev(String nev) { this.nev = nev; }

    public Date getSzulDatum() { return szulDatum; }
    public void setSzulDatum(Date szulDatum) { this.szulDatum = szulDatum; }

    public String getFoglalkozas() { return foglalkozas; }
    public void setFoglalkozas(String foglalkozas) { this.foglalkozas = foglalkozas; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
}
