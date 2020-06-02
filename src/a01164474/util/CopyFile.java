package a01164474.util;

/**
 * Project: a01164474 Lab5
 * File:CopyFile.java
 */

/**
 * Copy File
 * 
 * @author Nathan Souza, A01164474
 *
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class CopyFile {

	public static void backup() throws IOException {

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("customers_report.txt"));
            outputStream = new PrintWriter(new FileWriter("customers_report.bak"));

            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);                
            }
            Logging.LOG.info("Backup customers_report.bak was successfully generated");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
	}
}

