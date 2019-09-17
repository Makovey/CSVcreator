package csv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main {

    public static String createFile() throws IOException {
        String id = UUID.randomUUID().toString() + ".csv";
        File file = new File(id);
        CSVWriter writer = new CSVWriter(new FileWriter(id, file.exists()));
        writer.close();
        return id;
    }

    public static String createFile(String... data) throws IOException {
        String id = UUID.randomUUID().toString() + ".csv";
        File file = new File(id);
        CSVWriter writer = new CSVWriter(new FileWriter(id, file.exists()));
        writer.writeNext(data);
        writer.close();
        return id;
    }


    public static String[] readCSV(String filename) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(filename), ',');
        List<String[]> allRows = reader.readAll();
        for (String[] row : allRows) {
            System.out.println(Arrays.toString(row));
        }
        reader.close();
        return allRows.get(0);
    }

    public static void deleteRowCSV(String filename, int rowNumber) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filename), ',');
        List<String[]> allRows = reader.readAll();
        allRows.remove(rowNumber);
        FileWriter csv = new FileWriter(filename);
        CSVWriter writer = new CSVWriter(csv);
        writer.writeAll(allRows);
        writer.close();
    }


}
