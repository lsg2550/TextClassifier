/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.io;

import java.io.File;

/**
 *
 * @author Luis
 */
public class IO {

    public static File readDirectoryListOfFiles(String path) {
        if (path.equals(null) || path.equals("")) {
            return null;
        } else {
            File selectedDirectory = new File(path);
            return selectedDirectory;
        }
    }
}
