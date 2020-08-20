package csv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main {

    public static String getRandomName() {
        return UUID.randomUUID().toString() + ".csv";
    }

    public static String createFile(String filename) throws IOException {
        return writeLineCSV(filename);
    }

    public static String writeLineCSV(String filename, String... data) throws IOException {
        File file = new File(filename);
        CSVWriter writer = new CSVWriter(new FileWriter(filename, file.exists()));
        if (data != null) writer.writeNext(data);
        writer.close();
        return filename;
    }

    public static String[] readFirstLineCSV(String filename) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(filename), ',');
        List<String[]> allRows = reader.readAll();
        reader.close();
        if (allRows.get(0).length == 0 || allRows.get(0)[0] == null || allRows.get(0)[0].isEmpty())
            throw new IndexOutOfBoundsException("В первой строке файла нет значения!");
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
