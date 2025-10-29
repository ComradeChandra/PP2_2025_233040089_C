/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040089.modul05;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Aurellia
 */
public class latihan2 {

    public static void main(String [] args){
        //menjalankan kode gui di event dispatch thread (EDT)
        //ini adalah praktik terbaik untuk menghindari masalah thread
        //akan dijelaskan detail nanti
        SwingUtilities.invokeLater(new Runnable() {
            
            
             public void run(){
                
                // 1.buat objek JFrame
                 JFrame frame = new JFrame("Jendela Pertama Saya / Ini bingkai");
                 
                 // 2.ATUR UKURAN JENDELA (LEBAR 400 TINGGI 300)
                 frame.setSize(400, 300);
                 
                 // 3.ATUR AKSI TOMBOL CLOSE
                 frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
                 
                 // 4.BUAT KOMPONEN JLABEL
                 JLabel label = new JLabel("Ini adalah JLabel.");
                 
                 // 5.TAMBAHKAN JLABEL KE JFRAME 
                 //SECARA DEFAULT JFRAME MENGGUNAKAN BorderLayout,
                 //dan .add() akan menambahkanya ke bagian tengah (center)
                 frame.add(label);
                 // 6. BUAT JENDELA TERLIHAT
                 frame.setVisible(true);
        
            }
        } );
}

}


                
                
