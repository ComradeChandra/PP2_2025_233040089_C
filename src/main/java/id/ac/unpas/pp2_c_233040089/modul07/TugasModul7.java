/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul07;

// Import library GUI (Swing & AWT)
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener; 

/**
 * * @author Aurellia 
 */
public class TugasModul7 extends JFrame {
    // --- 1. KOMPONEN GLOBAL ---
    // Deklarasi variabel di sini biar bisa diakses sama semua method di kelas ini
    private JTextField txtNama; 
    private JTextField txtNilai; 
    private JComboBox<String> cmbMatkul; 
    private JTable tableData; 
    private DefaultTableModel tableModel; 
    private JTabbedPane tabbedPane; 

    // === KONSTRUKTOR ===
    // Ini yang pertama kali jalan pas program di-run
    public TugasModul7() {
        setTitle("Aplikasi Manajemen Nilai Siswa (Tugas Modul 7)");
        setSize(500, 450); // Ukuran jendela (lebar x tinggi)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Biar program mati pas di-close
        setLocationRelativeTo(null); // Biar popup muncul pas di tengah layar laptop

        // Siapin wadah buat Tab-nya
        tabbedPane = new JTabbedPane();

        // Panggil method buat bikin tampilan Tab 1 (Input)
        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        // Panggil method buat bikin tampilan Tab 2 (Tabel)
        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel); 

        // Masukin TabbedPane yang udah jadi ke dalam Frame utama
        add(tabbedPane);
    }
    
    // ---------------------------TUGAS-4-----------------------------
    // Method buat ngerakit tampilan Tab INPUT DATA
    // TUGAS 4: Disini nambahin tombol Reset
    private JPanel createInputPanel() {
        // Pake GridLayout 5 baris biar rapi ke bawah (Nama, Matkul, Nilai, Tombol)
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        // Kasih jarak di pinggir biar gak mepet banget sama garis jendela
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        // 1. Baris Nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // 2. Baris Mata Pelajaran (Pake ComboBox biar user milih aja)
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
                           "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // 3. Baris Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // 4. Baris Tombol (Simpan & Reset)
        panel.add(new JLabel("")); // Ini cuma spacer kosong biar tombol ada di kanan 
        
        // Bikin panel kecil lagi buat nyatuin dua tombol (Simpan & Reset)
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5)); 
        JButton btnSimpan = new JButton("Simpan Data");
        JButton btnReset = new JButton("Reset Input"); // TUGAS 4: Ini tombol Reset-nya

        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnReset);
        panel.add(buttonPanel);

        // Aksi kalo tombol Simpan diklik -> Lari ke method prosesSimpan()
        btnSimpan.addActionListener(e -> prosesSimpan());

        // TUGAS 4: Logika tombol Reset
        // Kalo diklik, kosongin semua textfield dan balikin combo box ke awal
        btnReset.addActionListener(e -> {
            txtNama.setText("");
            txtNilai.setText("");
            cmbMatkul.setSelectedIndex(0);
        });

        return panel;
    }

    // ---------------------------TUGAS-2-----------------------------
    // Method buat ngerakit tampilan Tab DAFTAR NILAI (Tabel)
    // TUGAS 2: Nambahin tombol Hapus Data disini
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Bikin header kolom tabelnya
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        // DefaultTableModel biar datanya bisa diedit (tambah/hapus)
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);
        
        // Tabel harus dibungkus ScrollPane biar bisa discroll kalo datanya banyak
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // TUGAS 2: Tombol Hapus (ditaruh di panel bawah)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnHapus = new JButton("Hapus Data");
        
        // TUGAS 2: Logika Hapus Data
        btnHapus.addActionListener(e -> {
            // Cek baris mana yang lagi diklik user
            int indeks = tableData.getSelectedRow(); 
            
            if (indeks > -1) { // Kalo hasilnya > -1 berarti ada baris yang dipilih
                tableModel.removeRow(indeks); // Hapus dari model tabelnya
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Kalo user iseng klik Hapus tapi belom milih baris
                JOptionPane.showMessageDialog(this, "Pilih baris tabel dulu kalo mau ngehapus!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnPanel.add(btnHapus);
        panel.add(btnPanel, BorderLayout.SOUTH); // Tempel panel tombol di bagian bawah (SOUTH)
        
        return panel;
    }

    // ---------------------------TUGAS-1-dan-TUGAS-3-----------------------------
    // Method Otak Utama: Validasi Input, Hitung Grade, dan Masukin ke Tabel
    // TUGAS 1: Grade pake SWITCH CASE
    // TUGAS 3: Validasi Nama min. 3 huruf
    private void prosesSimpan() {
        // 1. Ambil semua data dari form input
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // 2. MULAI VALIDASI
        
        // Cek kalo nama kosong melompong
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return; // Stop, jangan lanjut
        }
        
        // TUGAS 3: Validasi Nama (Minimal 3 karakter)
        // Pake .trim() biar spasi doang gak diitung
        if (nama.trim().length() < 3) { 
            JOptionPane.showMessageDialog(this, "Nama siswa kependekan! Minimal 3 huruf lah.", "Error Validasi", JOptionPane.WARNING_MESSAGE);
            return; // Stop proses
        }

        // Validasi Nilai: Harus Angka & Range 0-100
        int nilai;
        try {
            // Coba ubah teks jadi angka (integer)
            nilai = Integer.parseInt(strNilai);
            
            // Cek range 0-100
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai kejauhan! Harus 0-100.", "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            // Kalo user masukin huruf di kolom nilai, bakal lari kesini (Error handling)
            JOptionPane.showMessageDialog(this, "Kolom nilai harus diisi angka ya!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // TUGAS 1: Logika Bisnis (Menentukan Grade) - Wajib pake SWITCH CASE
        String grade;
        // Trik: Nilai dibagi 10 biar dapet angka depannya doang buat Case
        // Contoh: 85 / 10 = 8 (Masuk case 8 -> AB)
        switch (nilai / 10) {
            case 10: 
            case 9:
                grade = "A"; // Range 90-100
                break;
            case 8:
                grade = "AB"; // Range 80-89
                break;
            case 7:
                grade = "B"; // Range 70-79
                break;
            case 6:
                grade = "BC"; // Range 60-69
                break;
            case 5:
                grade = "C"; // Range 50-59
                break;
            case 4:
                grade = "D"; // Range 40-49
                break;
            default:
                grade = "E"; // Range 0-39 (Sisanya/Gagal)
                break;
        }

        // 3. Masukkan data yang udah bersih ke Tabel
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        // 4. Bersih-bersih Form & Pindah Tab otomatis
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
        JOptionPane.showMessageDialog(this, "Sip! Data Berhasil Disimpan.");
        tabbedPane.setSelectedIndex(1); // Otomatis geser ke tab "Daftar Nilai" biar user liat hasilnya
    }
    
    // === MAIN METHOD ===
    // Pintu masuk program java
    public static void main(String[] args) {
        // SwingUtilities.invokeLater itu best practice biar GUI-nya thread-safe (gak nge-lag/crash)
        SwingUtilities.invokeLater(() -> {
            new TugasModul7().setVisible(true); // Munculin jendelanya
        });
    }
}