/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul09;

import java.io.Serializable;

/**
 *
 * @author Aurellia
 */
// Kelas ini harus implement Serializable biar bisa diubah jadi byte (stream) dan disimpen ke file
public class UserConfig implements Serializable {
    
    // Ini atribut yang mau disimpen
    private String username;
    private int fontSize;

    // Konstruktor kosong, kadang dibutuhin sama Java
    public UserConfig() {
    }

    // Konstruktor buat langsung isi data pas objek dibuat
    public UserConfig(String username, int fontSize) {
        this.username = username;
        this.fontSize = fontSize;
    }

    // Getter buat ngambil username
    public String getUsername() {
        return username;
    }

    // Setter buat ngubah username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter buat ngambil ukuran font
    public int getFontSize() {
        return fontSize;
    }

    // Setter buat ngubah ukuran font
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
    
    // Method toString biar pas dicetak hasilnya enak dibaca, bukan alamat memori doang
    @Override
    public String toString() {
        return "UserConfig{username=" + username + ", fontSize=" + fontSize + "}";
    }
}

/*
==========PENJELASAN KODE==========
Kelas UserConfig ini sebenernya cuma kelas biasa (POJO - Plain Old Java Object) yang isinya data user (nama dan ukuran font).
Tapi, ada satu syarat penting buat Latihan 3: Kelas ini MENG-IMPLEMENTS SERIALIZABLE.
Kenapa? Karena komputer itu nggak ngerti cara nyimpen "Objek Java" langsung ke file harddisk.
Interface 'Serializable' itu kayak ngasih izin ke Java buat "mencincang" objek ini jadi aliran byte (010101) biar bisa ditulis ke file (pake ObjectOutputStream), dan nanti bisa disusun ulang jadi objek lagi (pake ObjectInputStream).
*/