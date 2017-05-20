/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresher.report.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

// TODO: Auto-generated Javadoc
/**
 * The Class IOMaster.
 */
public class IOMaster {
    
    /**
     * Instantiates a new IO master.
     */
    private IOMaster (){}

    /**
     * Read UTF 8 text.
     *
     * @param file the file
     * @return the string
     * @throws FileNotFoundException the file not found exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String readUTF8Text(String file) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        FileInputStream fi = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fi, "UTF-16");
        BufferedReader br = new BufferedReader(isr);
        String sR = "", sNewLine;

        while ((sNewLine = br.readLine()) != null){
            sR += sNewLine + "\n";
        }
        fi.close();
        return sR;
    }

    /**
     * Write UTF 8 text.
     *
     * @param file the file
     * @param text the text
     * @throws FileNotFoundException the file not found exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void writeUTF8Text(String file, String text) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        writeUTF8Text(file, text, false);
    }

    /**
     * Write UTF 8 text.
     *
     * @param file the file
     * @param text the text
     * @param append the append
     * @throws FileNotFoundException the file not found exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void writeUTF8Text(String file, String text, boolean append) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        FileOutputStream fo = new FileOutputStream(file, append);
        OutputStreamWriter osw = new OutputStreamWriter(fo, "utf-8");
        osw.write(text);
        osw.flush();
        fo.close();
    }
}
