/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Elsa
 */
public class Tgs5Controller {
    private Tgs5 view;
    private List<Integer> list = new ArrayList<>();
 
     public Tgs5Controller(Tgs5 view) {
         this.view = view;
         
         this.view.getBtBaca().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                proses();
             }
         });
         
         this.view.getBtSimpan().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 save();
             }
         });
         
     }
     
     private void proses(){
         JFileChooser loadFile = view.getLoadFile();
         StyledDocument doc = view.getTxtPane().getStyledDocument();
             if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
                 BufferedReader reader = null;
                 try {
                     
                     int desimal;
                     char ascii;
                     
                     String line = null;
                     int kat = 0;
                     int kar = 0;
                     
                     LineNumberInputStream inputstream = new LineNumberInputStream(new FileInputStream(loadFile.getSelectedFile()));
                     LineNumberReader numberReader = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));
                     
                     reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
                     String file = null;
                     doc.insertString(0, "", null);
                     while ((file = reader.readLine()) != null) {                         
                         doc.insertString(doc.getLength(), file, null);
                         doc.insertString(doc.getLength(), " ", null);
                     } 
                     while ((desimal = inputstream.read()) !=-1) {                         
                         ascii = (char) desimal;
                     }
                     while((line = numberReader.readLine()) != null){
                        String [] wordList = line.split("\\s+");
                        kar += line.length();
                        kat += wordList.length;
                     }
                        JOptionPane.showMessageDialog(view, "File berhasil dibaca. \n Jumlah Baris = "+ (inputstream.getLineNumber() +1)+ 
                             "\n Jumlah kata = "+(kat)+" \n Jumlah Baris = "+(kar), "Informasi", JOptionPane.INFORMATION_MESSAGE);
                     

             } catch (FileNotFoundException ex) {
                     Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException | BadLocationException ex) {
                     Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE, null, ex);
                 } finally {
                     if (reader != null) {
                         try {
                             reader.close();
                         } catch (IOException ex) {
                             Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                 }
             }
     }

     private void save(){
        JFileChooser loadFile = view.getLoadFile();
        if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
            BufferedOutputStream writer = null;
            try {
                String contents = view.getTxtPane().getText();
                 if (contents != null && !contents.isEmpty()) {
                     writer = new BufferedOutputStream(new FileOutputStream(loadFile.getSelectedFile()));
                     writer.write(contents.getBytes());
                     JOptionPane.showMessageDialog(view, "File berhasil ditulis.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
//            BufferedWriter writer = null;
//            try {
//                String contents = view.getTxtPane().getText();
//                if (contents != null && !contents.isEmpty()) {
//                    writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
//                    writer.write(contents);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE,null,ex);
            } catch (IOException ex){
                Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE,null,ex);
            }finally{
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                        view.getTxtPane().setText("");
                    } catch (IOException ex) {
                        Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE,null,ex);
                    }
   
                }
            }
        }
    }
}
