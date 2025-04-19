package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * A treasure class whose drops are stored in a fixed‐length array.
 */
public class TreasureClass {

    private final String name;

    private final String drop1;

    private final String drop2;

    private final String drop3;

    public TreasureClass(String name, String drop1, String drop2, String drop3) {
        this.name  = name;
        this.drop1 = drop1;
        this.drop2 = drop2;
        this.drop3 = drop3;
    }

    private String getDrop(int dropNum) { 
        switch (dropNum) {
            case 1:
                return drop1;
            case 2:
                return drop2;
            case 3:
                return drop3;
            default:
                throw new IllegalArgumentException();
        }
    }

    public String getRandomDrop() {
        Random random = new Random();
        int dropNum = random.nextInt(3) + 1;
        return getDrop(dropNum);
    }

    public static Map<String, TreasureClass> loadTCs(String fp) throws FileNotFoundException {
        Map<String, TreasureClass> map = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(fp))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().trim().split("\t");
                map.put(data[0], new TreasureClass(data[0], data[1], data[2], data[3]));
            }
            scanner.close();
        }
        return map;
    }
}