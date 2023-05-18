/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package querys;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sebas
 */
public class ReadingFileAndGettingInsert {

    private final String fileUrl;
    public String sentence;
    public String command = "INSERT INTO album VALUES ";
    public int sizeOfRows = 500;

    public ReadingFileAndGettingInsert(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<String> getInsert() {
        List<String> queryS = new ArrayList<>();

        try {
            int contador = 0;
            File file = new File(fileUrl);
            Scanner sc = new Scanner(file);
            String query = "";
            while (sc.hasNextLine()) {
                sentence = sc.nextLine();
                contador++;
                String[] strSplited = sentence.split("_Title:", 2);
                query += "(" + strSplited[0].substring(4) + ",'" + strSplited[1] + "'),";
                if (contador % sizeOfRows == 0) {
                    query = command + query.substring(0, query.length() - 1) + ";";
                    queryS.add(query);
                    query = "";
                }
            }
            return queryS;
        } catch (FileNotFoundException e) {
            return queryS;
        }
    }

    public List<String> updateValues() {
        List<String> queryS = new ArrayList<>();

        try {
            int contador = 0;
            File file = new File(fileUrl);
            Scanner sc = new Scanner(file);
            String query = "";
            while (sc.hasNextLine()) {
                sentence = sc.nextLine();
                contador++;
                String[] strSplited = sentence.split("_Title:", 2);
                query += "(" + strSplited[0].substring(4) + ",'" + strSplited[1] + "'),";
                if (contador % sizeOfRows == 0) {
                    query = command + query.substring(0, query.length() - 1) + ";";
                    queryS.add(query);
                    query = "";
                }
            }
            return queryS;
        } catch (FileNotFoundException e) {
            return queryS;
        }
    }

    public static void main(String[] args) {
        ReadingFileAndGettingInsert archivo = new ReadingFileAndGettingInsert(
                "C:\\Users\\sebas\\repos\\ReadingFiles\\10KAlbums.txt");
        List<String> queryS = new ArrayList<>();
        queryS = archivo.getInsert();

        for (String query : queryS) {
            System.out.println(query);
        }
    }
}
