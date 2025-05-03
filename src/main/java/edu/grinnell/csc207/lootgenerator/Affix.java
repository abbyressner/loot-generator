package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Affix class.
 */
public class Affix {
    private final String name;
    private final String mod1code;
    private final int mod1min;
    private final int mod1max;

    /**
     * Constructor for Affix class.
     *
     * @param name    the name of the affix
     * @param mod1code the mod code of the affix
     * @param mod1min  the minimum value of the affix
     * @param mod1max  the maximum value of the affix
     */
    public Affix(String name, String mod1code, int mod1min, int mod1max) {
        this.name = name;
        this.mod1code = mod1code;
        this.mod1min = mod1min;
        this.mod1max = mod1max;
    }

    /**
     * Generates a random stat value for the affix based on its min and max values.
     *
     * @param affix the affix to generate a stat for
     * @return a random stat value within the affix's range
     */
    public int generateAffixStat(Affix affix) {
        Random random = new Random();
        return random.nextInt(affix.mod1max - affix.mod1min + 1) + affix.mod1min;
    }

    /**
     * Returns the name of the affix.
     *
     * @return the name of the affix
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the mod code of the affix.
     *
     * @return the mod code of the affix
     */
    public String getMod1Code() {
        return mod1code;
    }

    /**
     * Returns the minimum value of the affix.
     *
     * @return the minimum value of the affix
     */
    public int getMod1Min() {
        return mod1min;
    }

    /**
     * Returns the maximum value of the affix.
     *
     * @return the maximum value of the affix
     */
    public int getMod1Max() {
        return mod1max;
    }

    /**
     * Loads affixes from a file.
     *
     * @param fp the file path to load affixes from
     * @return a list of Affix objects
     * @throws FileNotFoundException if the file is not found
     */
    public static List<Affix> loadAffixes(String fp) throws FileNotFoundException {
        List<Affix> affixes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fp))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().trim().split("\t");
                affixes.add(new Affix(data[0], 
                                      data[1], 
                                      Integer.parseInt(data[2]), 
                                      Integer.parseInt(data[3])));
            }
            scanner.close();
        }
        return affixes;
    }
}
