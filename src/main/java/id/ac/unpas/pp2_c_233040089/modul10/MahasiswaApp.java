/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul10;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // Import library JDBC

/**
 *
 * @author Aurellia
 */
public class MahasiswaApp extends JFrame {
    
    // --- KOMPONEN GUI ---
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNama, txtNIM, txtJurusan;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear;

    // Konstruktor: Menginisialisasi tampilan saat aplikasi pertama kali dijalankan
    public MahasiswaApp() {
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // A. Setup Tabel Data
        String[] colNames = {"ID", "Nama", "NIM", "Jurusan"};
        tableModel = new DefaultTableModel(colNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // B. Setup Panel Form Input
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        formPanel.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        formPanel.add(txtNama);
        
        formPanel.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        formPanel.add(txtNIM);
        
        formPanel.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        formPanel.add(txtJurusan);

        // C. Setup Panel Tombol
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnClear);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- EVENT HANDLING (Logika Tombol) ---
        
        btnSimpan.addActionListener(e -> simpanData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> kosongkanForm());
        
        // Listener Mouse: Memindahkan data dari tabel ke form input saat baris diklik
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtNama.setText(tableModel.getValueAt(row, 1).toString());
                    txtNIM.setText(tableModel.getValueAt(row, 2).toString());
                    txtJurusan.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        // Memuat data dari database ke tabel saat aplikasi terbuka
        loadData();
    }

    // Method untuk mengambil data (READ) dari database dan menampilkannya di tabel
    private void loadData() {
        tableModel.setRowCount(0); // Reset tabel GUI
        
        try {
            // Mengambil koneksi dari kelas KoneksiDB
            Connection conn = KoneksiDB.configDB();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mahasiswa");
            
            // Iterasi hasil query dan menambahkannya ke model tabel
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Memuat Data: " + e.getMessage());
        }
    }

    // Method untuk menyimpan data baru (CREATE)
    private void simpanData() {
        // Validasi Input (Latihan 2): Mencegah penyimpanan jika Nama atau NIM kosong
        if (txtNama.getText().isEmpty() || txtNIM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan NIM tidak boleh kosong!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtNIM.getText());
            pst.setString(3, txtJurusan.getText());
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
            loadData();
            kosongkanForm();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }

    // Method untuk mengubah data (UPDATE) berdasarkan ID
    private void editData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diedit!");
            return;
        }
        
        int id = (int) tableModel.getValueAt(row, 0); // Mengambil ID (Hidden)
        
        try {
            String sql = "UPDATE mahasiswa SET nama=?, nim=?, jurusan=? WHERE id=?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtNIM.getText());
            pst.setString(3, txtJurusan.getText());
            pst.setInt(4, id);
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Data Berhasil Diupdate!");
            loadData();
            kosongkanForm();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }

    // Method untuk menghapus data (DELETE) berdasarkan ID
    private void hapusData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
            return;
        }
        
        int id = (int) tableModel.getValueAt(row, 0);
        
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data ini?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM mahasiswa WHERE id = ?";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus!");
                loadData();
                kosongkanForm();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
            }
        }
    }

    // Helper untuk membersihkan form input
    private void kosongkanForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
        table.clearSelection();
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}

/*
==========PENJELASAN KODE BISI LUPA==========
Aplikasi ini menerapkan konsep CRUD (Create, Read, Update, Delete) yang terhubung ke database MySQL.

1.  **Pemisahan Logika Koneksi:**
    Berbeda dengan kode sebelumnya, logika koneksi kini dipisah ke dalam kelas `KoneksiDB`.
    Method `KoneksiDB.configDB()` dipanggil setiap kali aplikasi membutuhkan akses ke database (saat load data, simpan, edit, atau hapus). Hal ini membuat struktur kode lebih rapi dan sesuai modul.

2.  **Operasi CRUD:**
    * **Read (`loadData`):** Menggunakan `Statement` untuk mengeksekusi query SELECT dan menampilkan hasilnya ke JTable.
    * **Create (`simpanData`):** Menggunakan `PreparedStatement` untuk INSERT data. Dilengkapi validasi input (Latihan 2) untuk mencegah data kosong.
    * **Update & Delete:** Menggunakan ID unik dari baris tabel yang dipilih sebagai acuan query UPDATE atau DELETE di database.

3.  **Keamanan:**
    Penggunaan `PreparedStatement` dengan parameter (`?`) diterapkan untuk mencegah SQL Injection, di mana input user dipisahkan dari struktur query SQL.
*/