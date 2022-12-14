/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodeFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author personal
 */
public class Compress {
    public static void compressFunction(File file) throws FileNotFoundException, IOException{
        String fileDirectory = file.getParent();
        String formate = getFormate(file);  // to get the formate of file (i.e pdf/txt)
        System.out.println(formate);
        System.out.println(fileDirectory);
        
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(fileDirectory + "/CompresseFile.gz");
        GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
        
        byte[] formateBuffer = formate.getBytes();    // to store the formate type of original file into coded file(just store pdf/txt)
            gzipOS.write(formateBuffer,0,formate.length());
        
        byte[] buffer = new byte[1024];
        int len;
        
        while((len = fis.read(buffer)) != -1){
            gzipOS.write(buffer,0,len);
        }
        
        gzipOS.close();
        fos.close();
        fis.close();
    }
    
    public static String getFormate(File file){
        String str = file.getPath();
        String formate = "";
        for(int i = str.length()-1; i>=0;i--){
            if(str.charAt(i) == '.') break;
            formate = str.charAt(i) + formate;
        }
        int len = formate.length();
        formate = Integer.toString(len) + formate;
        return formate;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        File path = new File("C:\\Users\\personal\\Documents\\Example.txt");
        
        compressFunction(path);
    }
}