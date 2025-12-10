/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul08;

import id.ac.unpas.pp2_c_233040089.modul08.controller.PersegiPanjangController;
import id.ac.unpas.pp2_c_233040089.modul08.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040089.modul08.view.PersegiPanjangView;
import javax.swing.SwingUtilities;

/**
 *
 * @author Aurellia
 */
public class Main {
    public static void main(String[] args) {
        // Menggunakan invokeLater untuk memastikan GUI Thread-safe
        SwingUtilities.invokeLater(() -> {
            // 1. Instansiasi Model (Data & Logika)
            PersegiPanjangModel model = new PersegiPanjangModel();
            
            // 2. Instansiasi View (Tampilan)
            PersegiPanjangView view = new PersegiPanjangView();
            
            // 3. Instansiasi Controller (Menghubungkan keduanya)
            new PersegiPanjangController(model, view);
            
            // 4. Menampilkan aplikasi
            view.setVisible(true);
        });
    }
}

/*
 ==============================================================================
 RINGKASAN LOGIKA FILE INI:
 ------------------------------------------------------------------------------
 File "Main" adalah titik awal program berjalan.
 Tugasnya hanya merakit komponen MVC:
 - Membuat objek Model.
 - Membuat objek View.
 - Membuat objek Controller (dan memasukkan Model & View ke dalamnya).
 - Menampilkan View ke layar.
 Setelah itu, kontrol alur program diserahkan ke sistem Event Handling (Controller).
 ==============================================================================
*/