import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO {

    // Method untuk MENYIMPAN (Insert) objek Hero ke DB
    public void simpanPahlawan(Hero h) {

        // Query SQL dengan TANDA TANYA (?) sebagai pengaman (Anti-Injection)
        String sql = "INSERT INTO hero (nama, level) VALUES (?, ?)";

        // Try-With-Resources: Buka Koneksi dan Siapkan Statement
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            // Inject data dari objek 'h' ke dalam tanda tanya '?' berurutan
            pst.setString(1, h.getNama()); // Tanda tanya ke-1 diisi Nama
            pst.setInt(2, h.getLevel());   // Tanda tanya ke-2 diisi Level

            // TARIK PELATUK! Eksekusi ke database.
            pst.executeUpdate();
            System.out.println("SUKSES: Hero " + h.getNama() + " berhasil masuk DB!");

        } catch (SQLException e) {
            System.out.println("GAGAL: Terjadi Error SQL - " + e.getMessage());
        }
    }

    // Jangan lupa import java.util.ArrayList dan List (sudah ditambah di atas)
    // Method untuk MEMBACA (Select) semua isi tabel
    public List<Hero> ambilSemuaHero() {
        List<Hero> daftarHero = new ArrayList<>();
        String sql = "SELECT * FROM hero"; // Ambil semua baris

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             // EKSEKUSI BACA! Hasilnya tertampung di ResultSet (Tabel Virtual)
             ResultSet rs = pst.executeQuery()) {

            // Looping membaca tabel virtual baris per baris ke bawah
            while (rs.next()) {
                // Bikin objek Hero kosong, lalu rakit satu per satu
                Hero h = new Hero();
                h.setId(rs.getInt("id"));        // Ambil int dari kolom 'id' DB
                h.nama = rs.getString("nama");    // Ambil string dari kolom 'nama' DB
                h.level = rs.getInt("level");

                // Masukkan objek yang udah dirakit ke dalam ArrayList
                daftarHero.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Error pas narik data: " + e.getMessage());
        }
        return daftarHero; // Kembalikan isi tas ArrayList
    }

    // Method untuk MENGEDIT (Update) level Hero berdasarkan ID
    public void updateLevel(int id_target, int level_baru) {

        // Query SQL UPDATE dengan 2 TANDA TANYA (?)
        // ? ke-1 -> nilai BARU yang mau dipasang di kolom level (SET level = ?)
        // ? ke-2 -> id hero yang jadi TARGET/sasaran diedit (WHERE id = ?)
        String sql = "UPDATE hero SET level = ? WHERE id = ?";

        // Try-With-Resources: Buka Koneksi dan Siapkan Statement
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            // Inject data ke dalam tanda tanya '?' berurutan
            pst.setInt(1, level_baru);   // Tanda tanya ke-1 diisi LEVEL BARU
            pst.setInt(2, id_target);    // Tanda tanya ke-2 diisi ID TARGET yang diedit

            // TARIK PELATUK! Eksekusi ke database.
            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("SUKSES: Hero ID " + id_target + " levelnya sekarang jadi " + level_baru);
            } else {
                System.out.println("GAGAL: Hero dengan ID " + id_target + " tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println("GAGAL: Terjadi Error SQL - " + e.getMessage());
        }
    }

    // Method untuk MEMECAT (Delete) Hero dari database berdasarkan ID
    public void hapusHero(int id_target) {

        // Query SQL DELETE dengan 1 TANDA TANYA (?)
        // ? di sini -> id hero yang mau DIHAPUS (WHERE id = ?)
        String sql = "DELETE FROM hero WHERE id = ?";

        // Try-With-Resources: Buka Koneksi dan Siapkan Statement
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            // Inject data ke dalam satu-satunya tanda tanya '?'
            pst.setInt(1, id_target);   // Tanda tanya ke-1 diisi ID TARGET yang dihapus

            // TARIK PELATUK! Eksekusi ke database.
            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("SUKSES: Hero ID " + id_target + " berhasil dipecat dari database!");
            } else {
                System.out.println("GAGAL: Hero dengan ID " + id_target + " tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println("GAGAL: Terjadi Error SQL - " + e.getMessage());
        }
    }

} 