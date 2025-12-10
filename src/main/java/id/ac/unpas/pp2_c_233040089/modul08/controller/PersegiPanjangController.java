/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul08.controller;

import id.ac.unpas.pp2_c_233040089.modul08.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040089.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Aurellia
 */
public class PersegiPanjangController {
    // Controller membutuhkan akses ke Model dan View
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        // Mendaftarkan ActionListener ke tombol-tombol yang ada di View
        // Agar saat tombol diklik, kode di Controller ini yang berjalan
        this.view.addHitungListener(new HitungListener());
        this.view.addResetListener(new ResetListener()); // Latihan 3
    }

    // --- LOGIKA TOMBOL HITUNG ---
    // Inner Class untuk menangani event klik tombol Hitung
    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Ambil data input dari View
                double p = view.getPanjang();
                double l = view.getLebar();

                // 2. Masukkan data ke Model
                model.setPanjang(p);
                model.setLebar(l);

                // 3. Perintahkan Model untuk menghitung (Luas & Keliling)
                model.hitungLuas();
                model.hitungKeliling(); // Latihan 2

                // 4. Ambil hasil dari Model dan tampilkan kembali di View
                view.setLuas(model.getLuas());
                view.setKeliling(model.getKeliling()); // Latihan 2

            } catch (NumberFormatException ex) {
                // Error handling jika user memasukkan huruf bukan angka
                view.tampilkanError("Input harus berupa angka!");
            }
        }
    }

    // --- LOGIKA TOMBOL RESET (Latihan 3) ---
    // Inner Class untuk menangani event klik tombol Reset
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Memerintahkan View untuk membersihkan tampilan
            view.resetView();
        }
    }
}

/*
 ==============================================================================
 RINGKASAN LOGIKA FILE INI:
 ------------------------------------------------------------------------------
 File "Controller" ini berfungsi sebagai penghubung antara Model dan View.
 Alur kerjanya:
 1. Menunggu user menekan tombol (Listener).
 2. Saat tombol diklik, Controller mengambil data dari View.
 3. Data dikirim ke Model untuk diolah/dihitung.
 4. Hasil olahan diambil lagi dari Model, lalu dikirim balik ke View untuk ditampilkan.
 Tanpa Controller, View dan Model tidak bisa saling komunikasi.
 ==============================================================================
*/