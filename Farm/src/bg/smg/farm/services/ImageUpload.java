/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.smg.farm.services;

import bg.smg.farm.frames.NewAnimal;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author smgCCFE
 */
public class ImageUpload {
    public ImageIcon SlozhiSnimka(NewAnimal zhiv) {
       JFileChooser fileChooser = new JFileChooser();
       fileChooser.addChoosableFileFilter(new ImageFilter());
       fileChooser.setAcceptAllFileFilterUsed(false);

       int option = fileChooser.showOpenDialog(zhiv);
       if(option == JFileChooser.APPROVE_OPTION){
          File file = fileChooser.getSelectedFile();
          try {               
               Path resourceDirectory = Paths.get("src","resources");
               String absolutePath = resourceDirectory.toFile().getAbsolutePath();
              
               FileChannel src = new FileInputStream(file).getChannel();
               FileChannel dest = new FileOutputStream(new File(absolutePath+"/"+file.getName())).getChannel();
               dest.transferFrom(src, 0, src.size());
               src.close();
               dest.close();
               ImageIcon imgIcon = new ImageIcon(absolutePath+"/"+file.getName());
               return imgIcon;
           } catch (Exception ex) {
               // TODO Auto-generated catch block
               ex.printStackTrace();
               
           }
        }
       return null;
    }
}