package application.model;

public class Szavazas {
    private int id;
    private String megnevezes;
    private String leiras;
    private String indul;
    private String zarul;
    private int jelolt1Id;
    private int jelolt2Id;
    private int jelolt3Id;

    public Szavazas() {}

    public Szavazas(String megnevezes, String leiras, String indul, String zarul, int jelolt1Id, int jelolt2Id, int jelolt3Id) {
        this.megnevezes = megnevezes;
        this.leiras = leiras;
        this.indul = indul;
        this.zarul = zarul;
        this.jelolt1Id = jelolt1Id;
        this.jelolt2Id = jelolt2Id;
        this.jelolt3Id = jelolt3Id;
    }

    public Szavazas(int id, String megnevezes, String leiras, String indul, String zarul, int jelolt1Id, int jelolt2Id, int jelolt3Id) {
        this.id = id;
        this.megnevezes = megnevezes;
        this.leiras = leiras;
        this.indul = indul;
        this.zarul = zarul;
        this.jelolt1Id = jelolt1Id;
        this.jelolt2Id = jelolt2Id;
        this.jelolt3Id = jelolt3Id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMegnevezes() { return megnevezes; }
    public void setMegnevezes(String megnevezes) { this.megnevezes = megnevezes; }

    public String getLeiras() { return leiras; }
    public void setLeiras(String leiras) { this.leiras = leiras; }

    public String getIndul() { return indul; }
    public void setIndul(String indul) { this.indul = indul; }

    public String getZarul() { return zarul; }
    public void setZarul(String zarul) { this.zarul = zarul; }

    public int getJelolt1Id() { return jelolt1Id; }
    public void setJelolt1Id(int jelolt1Id) { this.jelolt1Id = jelolt1Id; }

    public int getJelolt2Id() { return jelolt2Id; }
    public void setJelolt2Id(int jelolt2Id) { this.jelolt2Id = jelolt2Id; }

    public int getJelolt3Id() { return jelolt3Id; }
    public void setJelolt3Id(int jelolt3Id) { this.jelolt3Id = jelolt3Id; }
}
