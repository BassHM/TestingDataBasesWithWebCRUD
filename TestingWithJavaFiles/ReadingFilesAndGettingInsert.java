/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package querys;

import java.io.IOException;
import java.io.FileWriter;
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
    public String update = "UPDATE album SET title = ";
    public int numberOfRows;
    public int sizeOfQueryS;

    public ReadingFileAndGettingInsert(String fileUrl, int numberOfRows, int sizeOfQueryS) {
        this.fileUrl = fileUrl;
        this.numberOfRows = numberOfRows;
        this.sizeOfQueryS = sizeOfQueryS;
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
                if (contador % sizeOfQueryS == 0) {
                    query = command + query.substring(0, query.length() - 1) + ";";
                    queryS.add(query);
                    query = "";
                }
            }

            return queryS;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred." + e.getMessage());
            return queryS;
        }
    }

    public List<String> getUpdate() {
        List<String> queryS = new ArrayList<>();
        try {
            String query = "";
            int numberOfQuerys = numberOfRows / sizeOfQueryS;
            for (int i = 0; i < numberOfQuerys; i++) {
                query = update + "'Editado query " + i + "' WHERE id >= " + (i * sizeOfQueryS + 1) + " AND id<= "
                        + (i * sizeOfQueryS + sizeOfQueryS) + ";";
                queryS.add(query);
            }

            return queryS;
        } catch (Exception e) {
            System.out.println("An error occurred." + e.getMessage());
            return queryS;
        }
    }

    public List<String> getQueryBlocks() {
        List<String> queryBlocks = new ArrayList<>();
        try {
            int numberOfBlocks = numberOfRows / sizeOfQueryS;
            for (int i = 0; i < numberOfBlocks; i++) {
                String query = "SELECT * FROM album WHERE id >= " + (i * sizeOfQueryS + 1) + " AND id <= "
                        + ((i + 1) * sizeOfQueryS) + ";";
                queryBlocks.add(query);
            }
            return queryBlocks;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return queryBlocks;
        }
    }

    public List<String> getDeleteQueries() {
        List<String> deleteQueries = new ArrayList<>();
        try {
            int numberOfBlocks = numberOfRows / sizeOfQueryS;
            for (int i = 0; i < numberOfBlocks; i++) {
                String deleteQuery = "DELETE FROM album WHERE id >= " + (i * sizeOfQueryS + 1) + " AND id <= "
                        + ((i + 1) * sizeOfQueryS) + ";";
                deleteQueries.add(deleteQuery);
            }
            return deleteQueries;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return deleteQueries;
        }
    }

    public static void saveListToFile(List<String> list, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String item : list) {
                writer.write(item);
                writer.write(System.lineSeparator());
            }
            System.out.println("List saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the list to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ReadingFileAndGettingInsert archivo = new ReadingFileAndGettingInsert(
                "C:\\Users\\sebas\\repos\\TestingDataBasesWithWebCRUD\\10KAlbums.txt", 10000, 500);
        List<String> queryS = new ArrayList<>();

        queryS = archivo.getDeleteQueries();

        // Para guardar las querys en un arhcivo.txt
        String filePath = "queryS.txt";
        saveListToFile(queryS, filePath);
    }
}
