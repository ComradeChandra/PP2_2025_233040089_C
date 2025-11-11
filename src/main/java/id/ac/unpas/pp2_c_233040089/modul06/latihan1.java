/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul06; 

// IMPORT KELAS-KELAS YANG DIPERLUKAN
import java.awt.BorderLayout;        // Buat layout atas-bawah-tengah
import java.awt.GridLayout;          // Buat layout grid (tabel) buat tombol
import java.awt.Font;                // Buat ganti font
import java.awt.event.ActionListener; // Buat 'dengerin' klik (ini materi Latihan 2 btw, wkwk)
import javax.swing.JButton;          // Buat tombol
import javax.swing.JFrame;           // Buat jendelanya
import javax.swing.JPanel;           // Buat "wadah" nampung tombol
import javax.swing.JTextField;       // Buat layar angkanya

// IMPORT BUAT NGITUNG (Pake Rhino)
import javax.script.ScriptEngine;        // Kelas 'mesin' ngitungnya
import javax.script.ScriptException;     // Buat nangkep error kalo ngitungnya ngaco
import org.mozilla.javascript.engine.RhinoScriptEngineFactory; // Ini 'pabrik'-nya si Rhino yg kita panggil

/**
 *
 * @author Aurellia
 */
public class latihan1 {

    public static void main(String[] args) { // Programnya mulai dari sini
        
        JFrame frame = new JFrame("Kalkulator Sederhana");      // Bikin jendelanya, kasih judul
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Biar tombol X beneran nge-close program
        frame.setSize(400, 500);                                // Ukuran jendelanya (lebar, tinggi)
        frame.setLayout(new BorderLayout());                    // Pake layout atas-bawah-tengah

        // 3. BUAT LAYAR (TEXTFIELD)
        final JTextField layar = new JTextField("0");         // Bikin layar, default-nya "0"
        layar.setFont(new Font("Arial", Font.BOLD, 32));      // Gedein font-nya biar keliatan
        layar.setEditable(false);                             // Biar gabisa diketik user
        layar.setHorizontalAlignment(JTextField.RIGHT);       // Teksnya nempel kanan (kayak kalkulator)
        frame.add(layar, BorderLayout.NORTH);                 // Taruh layarnya di ATAS (NORTH)

        // 4. BUAT WADAH TOMBOL (PANEL)
        JPanel panelTombol = new JPanel();                      // Bikin panel baru buat nampung tombol
        panelTombol.setLayout(new GridLayout(4, 4, 5, 5));      // Panel ini pake layout grid 4x4, kasih jarak 5px

        // 6. BIKIN VARIABEL TOMBOL
        // (Dibikin variabel satu-satu biar bisa dipasangin listener)
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btnBagi = new JButton("/");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btnKali = new JButton("*");
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btnKurang = new JButton("-");
        JButton btn0 = new JButton("0");
        JButton btnC = new JButton("C");
        JButton btnEquals = new JButton("=");
        JButton btnTambah = new JButton("+");
        
        // 7. LEMPAR TOMBOL KE "WADAH"
        // (Masukin semua variabel tombol tadi ke panel)
        panelTombol.add(btn7);
        panelTombol.add(btn8);
        panelTombol.add(btn9);
        panelTombol.add(btnBagi);
        panelTombol.add(btn4);
        panelTombol.add(btn5);
        panelTombol.add(btn6);
        panelTombol.add(btnKali);
        panelTombol.add(btn1);
        panelTombol.add(btn2);
        panelTombol.add(btn3);
        panelTombol.add(btnKurang);
        panelTombol.add(btn0);
        panelTombol.add(btnC);
        panelTombol.add(btnEquals);
        panelTombol.add(btnTambah);

        // 8. BAGIAN FUNGSI (PERCOBAAN MEMBUAT KALKULATOR JALAN
        
        // Kalo mau kalkulatornya jalan, cukup hapus '/*' di baris bawah ini
        // Kalo mau Latihan 1 biasa (cuma layout), biarin aja slash bintangnya ada
        // Secara Default, Slash bintangnya nggak ada supaya kalkulator bisa dipake
        
         // <-- HAPUS INI BUAT NGIDUPIN FUNGSI (KALAU MAU DI NON AKTIFIN CUKUP TAMBAH SLASH BINTANG)
        
        // Bikin engine-nya langsung dari kelas Factory-nya (gak pake "nyari")
        ScriptEngine engine = new RhinoScriptEngineFactory().getScriptEngine();

        // Bikin 1 listener buat nempel angka/operator
        ActionListener appendListener = e -> {
            String command = ((JButton)e.getSource()).getText(); // Ambil teks dari tombol yg diklik
            String currentText = layar.getText();
            
            if (currentText.equals("0")) {                       // Kalo layarnya masih "0"
                layar.setText(command);                          // ganti jadi angka baru
            } else {                                             // Kalo udah ada angka
                layar.setText(currentText + command);            // tinggal tempel di belakangnya
            }
        };
        
        // Pasang listener tadi ke semua tombol ini
        btn7.addActionListener(appendListener);
        btn8.addActionListener(appendListener);
        btn9.addActionListener(appendListener);
        btnBagi.addActionListener(appendListener);
        btn4.addActionListener(appendListener);
        btn5.addActionListener(appendListener);
        btn6.addActionListener(appendListener);
        btnKali.addActionListener(appendListener);
        btn1.addActionListener(appendListener);
        btn2.addActionListener(appendListener);
        btn3.addActionListener(appendListener);
        btnKurang.addActionListener(appendListener);
        btn0.addActionListener(appendListener);
        btnTambah.addActionListener(appendListener);
        
        // Listener khusus buat tombol 'C'
        btnC.addActionListener(e -> {
            layar.setText("0"); // Gampang, tinggal balikin ke "0"
        });
        
        // Listener khusus buat '=' (biar ngitung)
        btnEquals.addActionListener(e -> {
            try {                                       // Pake try-catch, jaga-jaga kalo ekspresinya ngaco (misal 5//2)
                String ekspresi = layar.getText();      // Ambil semua teks di layar (misal "50-5")
                Object hasil = engine.eval(ekspresi);   // Suruh si mesin rhino ngitung teks itu
                
                // Rapiin hasilnya, kalo 80.0 jadi 80 aja
                if (hasil instanceof Double && (Double)hasil % 1 == 0) {
                    layar.setText(String.valueOf(((Double)hasil).intValue())); // Ubah 80.0 jadi 80 (integer)
                } else {
                    layar.setText(hasil.toString());      // Kalo desimal (misal 5/2 = 2.5) ya biarin
                }
                
            } catch (Exception ex) {    // Tangkep kalo ada error
                layar.setText("Error"); // Kalo ngaco, tulis Error aja di layar
            }
        });
        
         //  <-- HAPUS INI JUGA BUAT NGIDUPIN FUNGSI (KALAU MAU DI NON AKTIFIN CUKUP TAMBAH SLASH BINTANG)
        
        // 9. MASUKIN WADAH TOMBOL KE JENDELA
        frame.add(panelTombol, BorderLayout.CENTER); // Taruh panel tombolnya di TENGAH (CENTER)

        // 10. TAMPILKAN SEMUANYA
        frame.setVisible(true); // Tunjukin semua jendelanya ke user
    }
}