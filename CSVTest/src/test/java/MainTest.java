import csv.*;
import org.junit.Assert;

import org.junit.Test;


public class MainTest {

    @Test
    public void createRowTest() throws Exception {
        String[] data = {"555152", "15214"};
        String filename = Main.createFile(data);
        Assert.assertEquals(data, Main.readCSV(filename));
    }

    @Test
    public void DeleteRowTest() throws Exception {
        String data = "Petrov";
        String filename = Main.createFile(data);
        Main.deleteRowCSV(filename, 0);
        try {
            Assert.assertNotEquals(data, Main.readCSV(filename));
        } catch (IndexOutOfBoundsException error) {
            System.out.println(error.getMessage());
            System.out.println("Строка удалена");
        }
    }

    @Test
    public void readEmptyFile() throws Exception {
        String filename = Main.createFile();
        try {
            Main.readCSV(filename);
        } catch (IndexOutOfBoundsException error) {
            System.out.println(error.getMessage());
            System.out.println("Файл пуст");
        }
        Assert.fail();
    }

}
