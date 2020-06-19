/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vandy
 */
public class koneksi {
    private Connection koneksi;
    
    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Terkoneksi jdbc");
        }catch(ClassNotFoundException ex){
            System.out.println("Koneksi Tidak di Berhasil "+ex);
        }
        
        String url = "jdbc:mysql://localhost:3306/inventori_barang";
        try {
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Konekksi database Berhasil di Lakukan ");
        } catch (SQLException e) {
            System.out.println("Koneksi Database Gagal");
        }
        return koneksi;
    }
}
