public class Hero {
    private int id;
    String nama;
    int level;

    // Constructor kosong (dipakai pas merakit data dari Database di ambilSemuaHero)
    public Hero() {
    }

    // Constructor dengan parameter (dipakai pas bikin Hero baru di MainApp)
    public Hero(String nama, int level) {
        this.nama = nama;
        this.level = level;
    }

    // Getter & Setter untuk id (private, wajib lewat method)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter untuk nama dan level
    public String getNama() {
        return nama;
    }

    public int getLevel() {
        return level;
    }
}