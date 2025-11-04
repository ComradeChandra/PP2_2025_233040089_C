/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Aurellia
 */
public class Tugas1 { 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout - Tugas 1");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                // 2. Buat komponen
                
                final JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton buttonSOUTH = new JButton("Tombol ada di Bawah (SOUTH)");
                
                // Buat 3 tombol baru
                JButton buttonWEST = new JButton("WEST");
                JButton buttonEAST = new JButton("EAST");
                JButton buttonCENTER = new JButton("CENTER");

                // 3. Tambahkan Aksi (ActionListener) ke tombol
                
                // Aksi untuk tombol SOUTH (dari Latihan 4)
                buttonSOUTH.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });

                // TAMBAHAN UNTUK TUGAS 1
                
                // Aksi untuk tombol WEST
                buttonWEST.addActionListener(e -> {
                    label.setText("Tombol WEST diklik!");
                });
                
                // Aksi untuk tombol EAST
                buttonEAST.addActionListener(e -> {
                    label.setText("Tombol EAST diklik!");
                });
                
                // Aksi untuk tombol CENTER
                buttonCENTER.addActionListener(e -> {
                    label.setText("Tombol CENTER diklik!");
                });
                // --- SELESAI TUGAS 1 ---

                // 4. Tambahkan komponen ke frame DENGAN POSISI
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSOUTH, BorderLayout.SOUTH);
                frame.add(buttonWEST, BorderLayout.WEST);
                frame.add(buttonEAST, BorderLayout.EAST);
                frame.add(buttonCENTER, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}