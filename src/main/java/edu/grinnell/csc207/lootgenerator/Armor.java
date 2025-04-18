package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Armor {

    private final String name;

    private final int minac;

    private final int maxac;

    /**
     * Create new armor object.
     *
     * @param name  The name of the armor
     * @param minac The minimum armor class of the armor
     * @param maxac The maximum armor class of the armor
     */
    public Armor(String name, int minac, int maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }

    /**
     * Return the name of the armor.
     *
     * @return name of the armor
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the armor's defense value.
     * @return armor's defense value
     */
    public int getDefenseValue() {
        Random random = new Random();
        return random.nextInt(maxac - minac + 1) + minac;
    }

    public static Map<String, Armor> loadArmor(String fp) throws FileNotFoundException {
        Map<String, Armor> armorMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(fp))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().trim().split("\t");
                armorMap.put(data[0], new Armor(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            }
            scanner.close();
        }
        return armorMap;
    }


}
