/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aurellia
 */
public class KoneksiDB {
    
    // Variabel statis untuk menyimpan sesi koneksi agar bisa dipanggil dari kelas lain
    private static Connection mysqlconfig;
    
    // Method statis untuk mengkonfigurasi dan mengembalikan koneksi database
    public static Connection configDB() throws SQLException {
        try {
            // URL Database: Menentukan alamat server dan nama database 'kampus_db'
            String url = "jdbc:mysql://localhost:3306/kampus_db";
            String user = "root"; // User default XAMPP/Laragon
            String pass = "";     // Password default biasanya kosong
            
            // Registrasi Driver MySQL
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            // Membuka koneksi ke database menggunakan parameter di atas
            mysqlconfig = DriverManager.getConnection(url, user, pass);
            
        } catch (SQLException e) {
            // Menampilkan pesan error jika koneksi gagal (misal: MySQL belum distart)
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal: " + e.getMessage());
        }
        
        return mysqlconfig;
    }
}

/*
==========PENJELASAN KODE==========
Kelas KoneksiDB berfungsi sebagai jembatan khusus untuk menghubungkan aplikasi Java dengan Database MySQL.
Pemisahan kelas ini dilakukan agar logika koneksi terpusat di satu tempat, sehingga jika ada perubahan konfigurasi (misal ganti nama database atau password), cukup diubah di file ini saja tanpa mengganggu file utama.
Method configDB() menggunakan DriverManager untuk membangun koneksi dan mengembalikan objek Connection yang nantinya dipakai oleh MahasiswaApp untuk mengirim query.
*/