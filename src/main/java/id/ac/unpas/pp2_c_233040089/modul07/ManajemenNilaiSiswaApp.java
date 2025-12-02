/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul07;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener; 

/**
 * 
 * @author Aurellia 
 */
public class ManajemenNilaiSiswaApp extends JFrame {
    // --- 1. DEKLARASI KOMPONEN GLOBAL ---
    private JTextField txtNama; 
    private JTextField txtNilai; 
    private JComboBox<String> cmbMatkul; 
    private JTable tableData; 
    private DefaultTableModel tableModel; 
    private JTabbedPane tabbedPane; 

    // === KONSTRUKTOR ===
    public ManajemenNilaiSiswaApp() {
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); 

        tabbedPane = new JTabbedPane();

        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel); 

        add(tabbedPane);
    }
    
    // === 2. DESAIN TAB INPUT (Latihan 1: Base Components Only) ===
    private JPanel createInputPanel() {
        // Layout 4 baris, 2 kolom untuk form (Hanya Tombol Simpan)
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        // Komponen Input Nama Siswa
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // Komponen Mata Pelajaran (ComboBox)
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
                           "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Tombol Simpan (Tanpa Tombol Reset - TUGAS 4)
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(new JLabel("")); // Placeholder kosong 
        panel.add(btnSimpan);

        // Logika Tombol Simpan
        btnSimpan.addActionListener(e -> prosesSimpan());

        return panel;
    }

    // === 3. DESAIN TAB TABEL (Latihan 1: Base Table Only) ===
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Setup Model Tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);
        
        // Membungkus tabel dengan ScrollPane
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // --- TIDAK ADA TOMBOL HAPUS (TUGAS 2) ---
        
        return panel;
    }

    // === 4. LOGIKA BISNIS: PROSES SIMPAN (LATIHAN 1: IF-ELSE GRADE LOGIC) ===
    private void prosesSimpan() {
        // 1. Ambil data
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // 2. VALIDASI INPUT (Hanya Validasi Dasar: Kosong, Angka, dan Rentang 0-100)
        
        // Validasi 1: Cek Nama kosong
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validasi Nilai: Pastikan angka dan rentang 0-100
        int nilai;
        try {
            // Tangkap error jika input bukan angka
            nilai = Integer.parseInt(strNilai);
            
            // Validasi Rentang (0-100)
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0-100!", "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            // Tangkap error jika input bukan angka
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Logika Grade menggunakan IF-ELSE (Logic Dasar)
        String grade;
        if (nilai >= 80) grade = "A";
        else if (nilai >= 70) grade = "AB";
        else if (nilai >= 60) grade = "B";
        else if (nilai >= 50) grade = "BC";
        else if (nilai >= 40) grade = "C";
        else if (nilai >= 30) grade = "D";
        else grade = "E";


        // 4. Masukkan ke Tabel (Update Model)
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        // 5. Reset Form dan Pindah Tab
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1); // Pindah ke tab Daftar Nilai
    }
    
    // === 5. MAIN METHOD (Pintu Masuk Program) ===
    public static void main(String[] args) {
        // Wajib menjalankan GUI di Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}