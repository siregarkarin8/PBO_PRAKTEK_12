import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // GANTI sesuai setting database kalian sendiri ya!
    private static final String URL = "jdbc:mysql://localhost:3307/db_game"; // ganti port & nama database sesuai setting kalian
    private static final String USER = "root";
    private static final String PASSWORD = ""; // isi kalau phpMyAdmin/MySQL kalian ada password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
