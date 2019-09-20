import csv.*;
import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


public class MainTest {
    String filename;

    @Before
    public void createFile() throws IOException {
        filename = Main.getRandomName();
        System.out.println("Created: " + filename);
    }

    @After
    public void deleteFile() {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
            System.out.println("Deleted: " + filename);
        }
        filename = null;
    }

    @Test
    public void createRowTest() throws Exception {
        String[] data = {"555152", "15214"};
        Main.writeLineCSV(filename, data);
        Assert.assertEquals(data, Main.readFirstLineCSV(filename));
    }

    @Test
    public void DeleteRowTest() throws Exception {
        String data = "Petrov";
        Main.writeLineCSV(filename, data);
        Main.deleteRowCSV(filename, 0);
        try {
            Assert.assertNotEquals(data, Main.readFirstLineCSV(filename));
        } catch (IndexOutOfBoundsException error) {
            System.out.println(error.getMessage());
            System.out.println("Строка удалена");
        }
    }

    @Test
    public void readEmptyFile() throws Exception {
        Main.createFile(filename);
        try {
            Main.readFirstLineCSV(filename);
        } catch (IndexOutOfBoundsException error) {
            System.out.println(error.getMessage());
            System.out.println("Файл пуст");
            return;
        }
        Assert.fail();
    }

}
