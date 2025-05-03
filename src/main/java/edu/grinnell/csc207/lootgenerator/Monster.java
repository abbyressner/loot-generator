package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Monster class.
 */
public class Monster {

    private String name;

    private String trClass;

    /**
     * Constructor for Monster class.
     *
     * @param name the name of the monster
     * @param trClass the treasure class of the monster
     */
    public Monster(String name, String trClass) {
        this.name = name;
        this.trClass = trClass;
    }

    /**
     * Returns the name of the monster.
     *
     * @return the name of the monster
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the treasure class of the monster.
     *
     * @return the treasure class of the monster
     */
    public String getTreasureClass() {
        return trClass;
    }

    /**
     * Load every monster from a given file.
     *
     * @param fp the file path
     * @return a list of monster objects
     * @throws FileNotFoundException if the file is not found
     */
    public static List<Monster> loadMonsters(String fp) throws FileNotFoundException {
        List<Monster> monsters = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fp))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().trim().split("\t");
                monsters.add(new Monster(data[0], data[3]));
            }
            scanner.close();
        }
        return monsters;
    }
}
