package edu.grinnell.csc207.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Affix {
    private final String name;
    private final String mod1code;
    private final int mod1min;
    private final int mod1max;

    public Affix(String name, String mod1code, int mod1min, int mod1max) {
        this.name = name;
        this.mod1code = mod1code;
        this.mod1min = mod1min;
        this.mod1max = mod1max;
    }

    public int generateAffixStat(Affix affix) {
        Random random = new Random();
        return random.nextInt(affix.mod1max - affix.mod1min + 1) + affix.mod1min;
    }

    public String getName() {
        return name;
    }

    public String getMod1Code() {
        return mod1code;
    }

    public int getMod1Min() {
        return mod1min;
    }

    public int getMod1Max() {
        return mod1max;
    }

    public static List<Affix> loadAffixes(String fp) throws FileNotFoundException {
        List<Affix> affixes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fp))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().trim().split("\t");
                affixes.add(new Affix(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }
            scanner.close();
        }
        return affixes;
    }

    
    
}
