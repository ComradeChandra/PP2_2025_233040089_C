/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul05; // (Paket kamu)

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

// --- TAMBAHAN IMPORT UNTUK LATIHAN 3 ---
import java.awt.FlowLayout; // Untuk ngatur layout kiri-ke-kanan [cite: 1182]
import javax.swing.JButton; // Untuk bikin tombol [cite: 1183]

/**
 *
 * @author Aurellia // (Author kamu)
 */
public class latihan3 { 

    public static void main(String [] args){
        //menjalankan kode gui di event dispatch thread (EDT)
        //(komentar kamu tetap ada)
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run(){
                
                // 1.buat objek JFrame
                JFrame frame = new JFrame("Latihan 3 / Label dan Tombol");
                
                // 2.ATUR UKURAN JENDELA
                frame.setSize(400, 300);
                
                // 3.ATUR AKSI TOMBOL CLOSE
                frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); // 
                
                // --- INI BAGIAN MODIFIKASI UNTUK LATIHAN 3 ---

                // 4. ATUR LAYOUT MANAGER (BARU!)
                // Biar komponennya rapi dari kiri ke kanan 
                frame.setLayout(new FlowLayout());
                
                // 5. BUAT KOMPONEN JLABEL (dari kode kamu)
                JLabel label = new JLabel("Teks Awal"); // 
                
                // 6. BUAT KOMPONEN JBUTTON (BARU!)
                JButton button = new JButton("Klik Saya!"); // 
                
                // 7. TAMBAHKAN AKSI KE TOMBOL (BARU!)
                // Ini "perintah" yang dijalankan pas tombol diklik 
                button.addActionListener(e -> {
                    // Aksi ini akan mengubah teks pada label 
                    label.setText("Tombol berhasil diklik!");
                });
                
                // 8. TAMBAHKAN KEDUA KOMPONEN KE JFRAME
                // Komentar lama kamu soal BorderLayout dihapus/disesuaikan
                frame.add(label); // 
                frame.add(button); // 

                // 9. BUAT JENDELA TERLIHAT
                frame.setVisible(true);
            }
        });
    }
}