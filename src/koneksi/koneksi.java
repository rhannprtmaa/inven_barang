package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private Connection koneksi;
    
    public Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Berhasil Terkoneksi jdbc");
        } catch (ClassNotFoundException ex) {
            System.out.println("Koneksi Tidak di Berhasil " + ex);
        }
        
        String url = "jdbc:mysql://localhost:3306/distribusi_barang?useSSL=false&serverTimezone=UTC";
        try {
            koneksi = DriverManager.getConnection(url, "root", "");
            System.out.println("Konekksi database Berhasil di Lakukan");
        } catch (SQLException e) {
            System.out.println("Koneksi Database Gagal");
            e.printStackTrace();  // Supaya tahu error detailnya
        }
        
        return koneksi;
    }
}
