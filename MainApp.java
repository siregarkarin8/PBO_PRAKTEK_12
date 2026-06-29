import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        // 1. Instansiasi Class Penghubung (DAO)
        HeroDAO databaseKu = new HeroDAO();

        // ===================================
        // SKENARIO 1: Menambahkan 2 Hero ke Database
        // ===================================
        System.out.println("--- PROSES INSERT DATA ---");
        Hero pahlawanSatu = new Hero("Gatotkaca", 99);
        Hero pahlawanDua = new Hero("Kadita", 45);

        // Perhatikan, kodenya elegan cuma butuh 1 baris!
        databaseKu.simpanPahlawan(pahlawanSatu);
        databaseKu.simpanPahlawan(pahlawanDua);

        // ===================================
        // SKENARIO 2: Menarik Data dari Database ke Layar
        // ===================================
        System.out.println("\n--- ISI DATABASE SAAT INI ---");
        List<Hero> semuaData = databaseKu.ambilSemuaHero();

        for (Hero heroDB : semuaData) {
            System.out.println("[*] ID: " + heroDB.getId() +
                               " | Nama: " + heroDB.getNama() +
                               " | Level: " + heroDB.getLevel());
        }

        // ===================================
        // SKENARIO 3: Mengedit Level Hero (Update)
        // ===================================
        System.out.println("\n--- PROSES UPDATE DATA ---");
        // ID 1 = Gatotkaca -> levelnya dinaikkan jadi 100
        databaseKu.updateLevel(1, 100);

        // ===================================
        // SKENARIO 4: Memecat Hero dari Database (Delete)
        // ===================================
        System.out.println("\n--- PROSES DELETE DATA ---");
        // ID 2 = Kadita -> dihapus dari database
        databaseKu.hapusHero(2);

        // ===================================
        // SKENARIO 5: Cek Ulang Isi Database (Pembuktian Update & Delete)
        // ===================================
        System.out.println("\n--- ISI DATABASE TERBARU (Setelah Update & Delete) ---");
        List<Hero> dataTerbaru = databaseKu.ambilSemuaHero();

        for (Hero heroDB : dataTerbaru) {
            System.out.println("[*] ID: " + heroDB.getId() +
                               " | Nama: " + heroDB.getNama() +
                               " | Level: " + heroDB.getLevel());
        }
    }
}