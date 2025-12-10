/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul08.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Aurellia
 */
public class PersegiPanjangView extends JFrame {
    // --- KOMPONEN UI ---
    // Deklarasi komponen input (TextField), output (Label), dan tombol (Button)
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblLuas = new JLabel("-"); // Nilai awal strip (-)
    private JLabel lblKeliling = new JLabel("-"); // Label output Keliling (Latihan 2)
    private JButton btnHitung = new JButton("Hitung");
    private JButton btnReset = new JButton("Reset"); // Tombol Reset (Latihan 3)

    // Konstruktor: Mengatur tata letak tampilan saat program dijalankan
    public PersegiPanjangView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Agar program berhenti total saat ditutup
        setSize(300, 300); // Ukuran jendela
        setTitle("MVC Persegi Panjang");
        setLayout(new GridLayout(6, 2, 10, 10)); // Menggunakan Grid 6 baris agar rapi

        // Menambahkan komponen ke layar urut dari atas ke bawah
        // Baris 1: Label dan Input Panjang
        add(new JLabel("Panjang:"));
        add(txtPanjang);
        
        // Baris 2: Label dan Input Lebar
        add(new JLabel("Lebar:"));
        add(txtLebar);
        
        // Baris 3: Label dan Output Luas
        add(new JLabel("Hasil Luas:"));
        add(lblLuas);
        
        // Baris 4: Label dan Output Keliling (Latihan 2)
        add(new JLabel("Hasil Keliling:"));
        add(lblKeliling);
        
        // Baris 5 & 6: Tombol Aksi
        add(btnHitung);
        add(btnReset); // Tambahan Latihan 3
    }

    // --- METHOD UNTUK MENGAMBIL INPUT USER ---
    // Mengubah teks dari TextField menjadi tipe double agar bisa dihitung
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // --- METHOD UNTUK MENAMPILKAN HASIL ---
    // Mengubah angka hasil hitungan menjadi String untuk ditampilkan di Label
    public void setLuas(double luas) {
        lblLuas.setText(String.valueOf(luas));
    }

    public void setKeliling(double keliling) { // Latihan 2
        lblKeliling.setText(String.valueOf(keliling));
    }

    // --- METHOD RESET TAMPILAN (Latihan 3) ---
    // Membersihkan form input dan mengembalikan label ke kondisi awal
    public void resetView() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblLuas.setText("-");
        lblKeliling.setText("-");
        txtPanjang.requestFocus(); // Mengembalikan kursor ke kolom panjang
    }

    // --- REGISTRASI LISTENER ---
    // Menghubungkan tombol di View dengan logic di Controller
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) { // Latihan 3
        btnReset.addActionListener(listener);
    }

    // Method bantuan untuk menampilkan pesan error (validasi)
    public void tampilkanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }
}

/*
 ==============================================================================
 RINGKASAN LOGIKA FILE INI:
 ------------------------------------------------------------------------------
 File "View" ini fungsinya hanya sebagai antarmuka (tampilan visual).
 Tugasnya:
 1. Menampilkan form, label, dan tombol kepada user.
 2. Menyediakan jalan (method) untuk mengambil nilai inputan user.
 3. Menyediakan jalan (method) untuk menampilkan hasil ke layar.
 File ini tidak boleh melakukan perhitungan matematika sama sekali.
 ==============================================================================
*/