/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodeFiles;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author personal
 */
public class Decompress {
    public static void decompressFunction(File file) throws IOException{
        String fileDirectory = file.getParent();
        System.out.println(fileDirectory);
        
        FileInputStream fis = new FileInputStream(file);
        GZIPInputStream gzipIS = new GZIPInputStream(fis);
        
        byte[] bufferFormate1 = new byte[1];
        String s1 = "";
        if(1 == gzipIS.read(bufferFormate1))
          s1 = new String(bufferFormate1);
        int lenOfFormate = Integer.valueOf(s1);
        
        byte[] bufferFormate = new byte[lenOfFormate];
        String s = "";
        if(lenOfFormate == gzipIS.read(bufferFormate))
          s = new String(bufferFormate);
        
        FileOutputStream fos = new FileOutputStream(fileDirectory + "/DecompressedFile." + s); // .txt
        
        byte[] buffer = new byte[1024];
        int len;
       
        System.out.println(s1);
        System.out.println(s);
        while((len = gzipIS.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }
        
        gzipIS.close();
        fis.close();
        fos.close();
    }
    
    public static void main(String[] args) throws IOException{
        File path = new File("C:\\Users\\personal\\Documents\\CompresseFile.gz");
        decompressFunction(path);
    }
}