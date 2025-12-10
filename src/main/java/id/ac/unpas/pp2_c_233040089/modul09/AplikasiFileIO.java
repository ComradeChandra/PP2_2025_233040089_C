/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*; // Import semua library IO (Input/Output) biar gak ribet

/**
 *
 * @author Aurellia
 */
public class AplikasiFileIO extends JFrame {
    
    // Komponen UI global biar bisa diakses di semua method
    private JTextArea textArea;
    private JButton btnBuka, btnSimpan, btnAppend; // Tombol File Teks
    private JButton btnSimpanObjek, btnBukaObjek; // Tombol File Objek (Latihan 3)
    private JFileChooser fileChooser; // Jendela buat milih file

    public AplikasiFileIO() {
        // Settingan dasar jendela aplikasi
        super("Aplikasi Catatan & Config (Modul 9)");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Biar muncul di tengah layar
        setLayout(new BorderLayout());

        // Area teks buat ngetik catatan
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        // Kasih ScrollPane biar kalo teksnya panjang bisa discroll
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Panel buat naruh tombol-tombol di bawah
        JPanel panelTombol = new JPanel(new GridLayout(2, 3, 5, 5)); // 2 Baris, 3 Kolom

        // --- BAGIAN LATIHAN 1 & 4 (File Teks) ---
        btnBuka = new JButton("Buka File Teks");
        btnSimpan = new JButton("Simpan (Overwrite)"); // Latihan 1
        btnAppend = new JButton("Simpan (Append)"); // Latihan 4

        // --- BAGIAN LATIHAN 3 (File Objek) ---
        btnSimpanObjek = new JButton("Simpan Objek User");
        btnBukaObjek = new JButton("Buka Objek User");

        // Masukin tombol ke panel
        panelTombol.add(btnBuka);
        panelTombol.add(btnSimpan);
        panelTombol.add(btnAppend);
        panelTombol.add(btnSimpanObjek);
        panelTombol.add(btnBukaObjek);

        add(panelTombol, BorderLayout.SOUTH);

        // Siapin FileChooser biar user bisa milih file di komputer
        fileChooser = new JFileChooser();

        // --- ACTION LISTENER (Logika pas tombol diklik) ---
        
        // 1. Buka File Teks
        btnBuka.addActionListener(e -> bukaFileTeks());

        // 2. Simpan File (Overwrite/Timpa) - Latihan 1
        btnSimpan.addActionListener(e -> simpanFileTeks(false));

        // 3. Simpan File (Append/Nambah di bawah) - Latihan 4
        btnAppend.addActionListener(e -> simpanFileTeks(true));

        // 4. Simpan Objek (Latihan 3)
        btnSimpanObjek.addActionListener(e -> simpanFileObjek());

        // 5. Buka Objek (Latihan 3)
        btnBukaObjek.addActionListener(e -> bukaFileObjek());
        
        // --- LATIHAN 2: AUTO LOAD FILE ---
        // Coba baca file "last_notes.txt" pas aplikasi baru dibuka
        muatCatatanTerakhir();
    }

    // --- METHOD LATIHAN 2: Muat Catatan Terakhir ---
    private void muatCatatanTerakhir() {
        File file = new File("last_notes.txt");
        // Cek dulu filenya ada apa nggak
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                textArea.setText(""); // Bersihin area dulu
                // Baca baris demi baris sampe abis (null)
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                // Gak perlu popup message biar gak ganggu pas startup
            } catch (IOException e) {
                // Kalo error, diem aja (sesuai instruksi modul), atau print ke console buat debug
                System.out.println("Gagal memuat catatan terakhir: " + e.getMessage());
            }
        }
    }

    // --- METHOD BUKA FILE TEKS (Latihan 1) ---
    private void bukaFileTeks() {
        // Nampilin dialog "Open File"
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) { // Kalo user jadi milih file (klik Open)
            File file = fileChooser.getSelectedFile();
            
            // Pake try-with-resources biar otomatis close stream-nya (Modern Java)
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                textArea.setText(""); // Kosongin dulu text area
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                JOptionPane.showMessageDialog(this, "File berhasil dibuka!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saat membaca file: " + e.getMessage());
            }
        }
    }

    // --- METHOD SIMPAN FILE TEKS (Latihan 1 & 4) ---
    // Parameter 'append' nentuin mau nimpa (false) atau nambah (true)
    private void simpanFileTeks(boolean append) {
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // FileWriter parameternya (file, append). Kalo append true dia nambah, kalo false dia nimpa.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
                writer.write(textArea.getText());
                // Simpan juga ke "last_notes.txt" buat Latihan 2
                simpanCatatanTerakhir(); 
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saat menyimpan file: " + e.getMessage());
            }
        }
    }
    
    // Helper buat Latihan 2 (Nyimpen backup otomatis)
    private void simpanCatatanTerakhir() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("last_notes.txt"))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            System.out.println("Gagal update last_notes: " + e.getMessage());
        }
    }

    // --- METHOD SIMPAN OBJEK (Latihan 3) ---
    private void simpanFileObjek() {
        // Kita bikin objek UserConfig dummy buat disimpen
        String nama = JOptionPane.showInputDialog(this, "Masukkan Username:");
        int fontSize = textArea.getFont().getSize();
        
        UserConfig config = new UserConfig(nama, fontSize);
        
        // Simpan ke file config.obj
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("config.obj"))) {
            out.writeObject(config); // Ini proses serialisasi
            JOptionPane.showMessageDialog(this, "Objek berhasil disimpan: " + config.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal simpan objek: " + e.getMessage());
        }
    }

    // --- METHOD BUKA OBJEK (Latihan 3) ---
    private void bukaFileObjek() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("config.obj"))) {
            // Baca file dan ubah balik jadi objek UserConfig (Deserialisasi)
            UserConfig config = (UserConfig) in.readObject();
            
            // Terapkan settingan dari objek yg dibaca
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontSize()));
            JOptionPane.showMessageDialog(this, "Config dimuat: " + config.toString());
            
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Gagal baca objek: " + e.getMessage());
        }
    }

    // --- MAIN METHOD ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}

/*
==========PENJELASAN KODE==========
Kode ini adalah gabungan dari Latihan 1 sampai 4 di Modul 9. Intinya tentang Input/Output (IO) File.

1.  **Membaca/Menulis Teks (Latihan 1 & 4):**
    Aku pake `BufferedReader` dan `BufferedWriter`. Kenapa ada 'Buffered'? Biar bacanya sekalian banyak (buffer), nggak satu-satu huruf, jadi lebih cepet.
    Untuk fitur Latihan 4 (Append), kuncinya ada di `new FileWriter(file, true)`. Parameter `true` itu ngasih tau Java: "Jangan hapus isi file lama, tapi tambahin aja tulisan baru di bawahnya".

2.  **Auto-Load (Latihan 2):**
    Di konstruktor (pas aplikasi baru nyala), aku panggil method `muatCatatanTerakhir()`. Dia nyari file "last_notes.txt". Kalo ada, langsung ditampilin di layar. Aku pake `try-catch` di situ, jadi kalo filenya nggak ada (misal pas pertama kali run), program nggak error/crash, cuma diem aja.

3.  **Serialisasi Objek (Latihan 3):**
    Ini yang agak beda. Aku pake `ObjectOutputStream` buat nyimpen objek `UserConfig` (yang udah kubikin di file sebelah) ke file "config.obj". Ini bukan nyimpen tulisan biasa, tapi nyimpen struktur objek Java. Pas dibaca lagi pake `ObjectInputStream`, dia langsung jadi objek lagi, nggak perlu di-parsing manual.

4.  **Exception Handling:**
    Hampir semua operasi file aku bungkus pake blok `try-catch`. Soalnya urusan file itu rawan error (file ilang, harddisk penuh, dll). Jadi kalo ada apa-apa, program nangkep errornya dan ngasih tau user lewat pesan dialog, nggak langsung force close.
*/