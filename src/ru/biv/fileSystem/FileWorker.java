/*
 * Copyright (C) 2016 Игорь
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package ru.biv.fileSystem;

import java.io.*;


/**
 *
 * @author Igor Banchkov
 */
public class FileWorker {

    /**
     *
     * @param fileName You need to specify a parameter of type string. Full path to the simple text file which you want to use as dictionary database
     * @param text The string which will be written to the file
     */
    public static void write(String fileName, String text) {
        
        File file = new File(fileName);

        try {
            //exist file?
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter wrOut = new FileWriter(file, true);

            try {
                //write text to file
                wrOut.write(text);
            } finally {
                wrOut.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param fileName You need to specify a parameter of type string. Full path to the simple text file with word pair
     * @return List of word pair
     * @throws FileNotFoundException Return the exception if the file not found
     */
    public static String read(String fileName) throws FileNotFoundException {
        //this object for string build
        StringBuilder sb = new StringBuilder();
                
        File file = exists(fileName);
        
        try {
            //file to buffer
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                //read file
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        //result string
        return sb.toString();
    }

    private static File exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
        return file;
    }
}
