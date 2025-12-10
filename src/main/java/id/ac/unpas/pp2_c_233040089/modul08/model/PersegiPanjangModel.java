/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul08.model;

/**
 *
 * @author Aurellia
 */
public class PersegiPanjangModel {
    // --- VARIABEL DATA ---
    // Menggunakan private agar data tidak bisa diubah sembarangan dari luar class
    private double panjang;
    private double lebar;
    private double luas;
    private double keliling; // Tambahan untuk fitur hitung keliling (Latihan 2)

    // --- METHOD LOGIKA (RUMUS) ---
    
    // Method untuk menghitung Luas
    // Rumus: Panjang x Lebar
    public void hitungLuas() {
        this.luas = this.panjang * this.lebar;
    }

    // Method untuk menghitung Keliling (Latihan 2)
    // Rumus: 2 x (Panjang + Lebar)
    public void hitungKeliling() {
        this.keliling = 2 * (this.panjang + this.lebar);
    }

    // --- SETTER (Untuk menyimpan nilai) ---
    // Digunakan oleh Controller untuk memasukkan data dari View ke Model
    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    // --- GETTER (Untuk mengambil hasil) ---
    // Digunakan oleh Controller untuk mengambil hasil hitungan
    public double getLuas() {
        return luas;
    }

    // Mengambil nilai keliling (Latihan 2)
    public double getKeliling() { 
        return keliling;
    }
}

/*
 ==============================================================================
 RINGKASAN LOGIKA FILE INI:
 ------------------------------------------------------------------------------
 File "Model" ini fungsinya khusus untuk menampung data dan rumus matematika.
 Dia tidak berhubungan dengan tampilan (UI). Tugasnya hanya:
 1. Menyimpan nilai panjang, lebar, luas, dan keliling.
 2. Menjalankan rumus perhitungan saat diperintah.
 Jadi kalau mau ubah rumus atau tambah variabel data, tempatnya di sini.
 ==============================================================================
*/