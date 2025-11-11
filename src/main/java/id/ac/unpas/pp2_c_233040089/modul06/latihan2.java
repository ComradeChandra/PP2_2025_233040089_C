/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul06; // Paketku...

// IMPORT KELAS-KELAS YANG DIPERLUKAN
import java.awt.GridLayout;            // Pake GridLayout (biar rapi kayak tabel)
import java.awt.Font;                  // Buat ganti font
import java.awt.event.ActionListener;  // Buat 'dengerin' klik tombol
import java.awt.event.ActionEvent;     // Objek "event" klik-nya
import javax.swing.JButton;            // Buat tombol
import javax.swing.JFrame;             // Buat jendelanya
import javax.swing.JLabel;             // Buat teks label
import javax.swing.JPanel;             // "Wadah" buat nampung blok-nya
import javax.swing.JTextField;         // Buat ngetik angkanya
import javax.swing.border.EmptyBorder; // Biar ada jarak/padding di dalem blok

/**
 *
 * @author Aurellia
 */
public class latihan2 { 

    public static void main(String[] args) {                       // Programnya mulai dari sini
        
        JFrame frame = new JFrame("Konverter Suhu (Bolak-Balik)"); // Bikin jendelanya
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // Biar 'X'-nya beneran nutup
        frame.setLayout(new GridLayout(2, 1, 10, 10));             // Pake GridLayout (2 baris, 1 kolom), tumpuk atas-bawah

        // FONT (biar rapi)
        Font fontLabel = new Font("Arial", Font.BOLD, 16); // Font buat label
        Font fontInput = new Font("Arial", Font.PLAIN, 16); // Font buat kotak input

        // BLOK 1: CELCIUS KE FAHRENHEIT

        // Bikin "wadah" atas (Blok 1)
        JPanel panelCtoF = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 baris, 2 kolom
        panelCtoF.setBorder(new EmptyBorder(10, 10, 10, 10));      // Kasih jarak pinggiran 10px

        // Komponen-komponen Blok 1
        JLabel lblC_in = new JLabel("Masukkan Celcius:");
        final JTextField txtC_in = new JTextField();
        JLabel lblF_out = new JLabel("Hasil Fahrenheit:");
        final JTextField txtF_out = new JTextField();
        txtF_out.setEditable(false);                     // Kunci, cuma buat nampilin hasil
        JButton btnCtoF = new JButton("Konversi C -> F");
        
        // Atur font Blok 1
        lblC_in.setFont(fontLabel);
        txtC_in.setFont(fontInput);
        lblF_out.setFont(fontLabel);
        txtF_out.setFont(fontInput);

        // Masukin ke panel atas (sesuai urutan grid)
        panelCtoF.add(lblC_in);     // Baris 1, Kolom 1
        panelCtoF.add(txtC_in);     // Baris 1, Kolom 2
        panelCtoF.add(lblF_out);    // Baris 2, Kolom 1
        panelCtoF.add(txtF_out);    // Baris 2, Kolom 2
        panelCtoF.add(new JLabel());// Baris 3, Kolom 1 (Kosong, biar tombolnya di kanan)
        panelCtoF.add(btnCtoF);     // Baris 3, Kolom 2

        // BLOK 2: FAHRENHEIT KE CELCIUS
        
        // Bikin "wadah" bawah (Blok 2)
        JPanel panelFtoC = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 baris, 2 kolom
        panelFtoC.setBorder(new EmptyBorder(10, 10, 10, 10));      // Kasih jarak pinggiran 10px

        // Komponen-komponen Blok 2
        JLabel lblF_in = new JLabel("Masukkan Fahrenheit:");
        final JTextField txtF_in = new JTextField();
        JLabel lblC_out = new JLabel("Hasil Celcius:");
        final JTextField txtC_out = new JTextField();
        txtC_out.setEditable(false);                              // Kunci, cuma buat nampilin hasil
        JButton btnFtoC = new JButton("Konversi F -> C");

        // Atur font Blok 2
        lblF_in.setFont(fontLabel);
        txtF_in.setFont(fontInput);
        lblC_out.setFont(fontLabel);
        txtC_out.setFont(fontInput);

        // Masukin ke panel bawah (sesuai urutan grid)
        panelFtoC.add(lblF_in);     // Baris 1, Kolom 1
        panelFtoC.add(txtF_in);     // Baris 1, Kolom 2
        panelFtoC.add(lblC_out);    // Baris 2, Kolom 1
        panelFtoC.add(txtC_out);    // Baris 2, Kolom 2
        panelFtoC.add(new JLabel());// Baris 3, Kolom 1 (Kosong)
        panelFtoC.add(btnFtoC);     // Baris 3, Kolom 2

        //BAGIAN FUNGSI / LISTENER

        // Listener 1: Tombol C -> F
        btnCtoF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        // Kalo tombol C->F diklik,
                try {                                           // Pake try-catch, jaga-jaga kalo inputnya ngaco
                    String teks = txtC_in.getText();            // Ambil teks (misal "12" atau "12,5")
                    String teksBener = teks.replace(',', '.');  // Ganti koma jadi titik (ini biang keroknya tadi wkwk)
                    double c = Double.parseDouble(teksBener);   // Ubah teks yg udah bener jadi angka
                    double f = (c * 9.0 / 5.0) + 32;            // Hitung
                    txtF_out.setText(String.format("%.2f", f)); // Tulis hasilnya (diformat 2 angka di blkg koma)
                } catch (NumberFormatException ex) {            // Kalo yg dimasukin bukan angka
                    txtF_out.setText("Error!");                 // tulis Error
                }
            }
        });
        
        // Listener 2: Tombol F -> C
        btnFtoC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Kalo tombol F->C diklik...
                try {
                    String teks = txtF_in.getText();            // Ambil teks (misal "53,60")
                    String teksBener = teks.replace(',', '.');  // Ganti koma jadi titik
                    double f = Double.parseDouble(teksBener);   // Ubah "53.60" jadi angka
                    double c = (f - 32) * 5.0 / 9.0;            // Hitung
                    txtC_out.setText(String.format("%.2f", c)); // Tulis hasilnya (jadi "12,00")
                } catch (NumberFormatException ex) {            // Kalo yg dimasukin bukan angka
                    txtC_out.setText("Error!");                 // tulis Error
                }
            }
        });

        //MASUKIN 2 BLOK TADI KE FRAME UTAMA
        frame.add(panelCtoF); // Blok 1 (ditaruh di baris 1 frame)
        frame.add(panelFtoC); // Blok 2 (ditaruh di baris 2 frame)

        // TAMPILKAN
        frame.pack();                         // Bikin ukurannya nge-pas sama isi
        frame.setLocationRelativeTo(null);    // Biar muncul di tengah layar
        frame.setVisible(true);               // Tunjukin ke user
    }
}