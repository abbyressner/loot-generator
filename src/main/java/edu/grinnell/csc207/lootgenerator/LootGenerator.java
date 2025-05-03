package edu.grinnell.csc207.lootgenerator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * LootGenerator class.
 */
public class LootGenerator {

    public static final String DIRECTORY = "data/large/";
    private static final Random random = new Random();

    /**
     * Main method to run the LootGenerator program.
     *
     * @param args command line arguments
     * @throws FileNotFoundException if the required data files are not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nThis program kills monsters and generates loot!\n");

        List<Monster> monsters = Monster.loadMonsters(DIRECTORY + "monstats.txt");
        Map<String, TreasureClass> treasureClasses
                = TreasureClass.loadTCs(DIRECTORY + "TreasureClassEx.txt");
        Map<String, Armor> armorMap = Armor.loadArmor(DIRECTORY + "armor.txt");
        List<Affix> prefixes = Affix.loadAffixes(DIRECTORY + "MagicPrefix.txt");
        List<Affix> suffixes = Affix.loadAffixes(DIRECTORY + "MagicSuffix.txt");

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            Monster monster = pickMonster(monsters);
            String mName = monster.getName();
            System.out.println("\nFighting " + mName + "...");
            System.out.println("You have slain " + mName + "!");
            System.out.println(mName + " dropped:\n");

            TreasureClass tc = fetchTreasureClass(monster, treasureClasses);
            Armor baseItem = generateBaseItem(tc, treasureClasses, armorMap);
            String itemName = baseItem.getName();
            int defense = generateBaseStats(baseItem);
            List<String> affixLines = new ArrayList<>();

            if (random.nextBoolean()) {
                Affix prefix = generateAffix(prefixes);
                int val = prefix.generateAffixStat(prefix);
                affixLines.add(val + " " + prefix.getMod1Code());
                itemName = prefix.getName() + " " + itemName;
            }
            if (random.nextBoolean()) {
                Affix suffix = generateAffix(suffixes);
                int val = suffix.generateAffixStat(suffix);
                affixLines.add(val + " " + suffix.getMod1Code());
                itemName = itemName + " " + suffix.getName();
            }

            System.out.println(itemName);
            System.out.println("Defense: " + defense);
            for (String line : affixLines) {
                System.out.println(line);
            }
            System.out.println();
            isRunning = promptFightAgain(scanner);
        }
        scanner.close();
    }

    private static boolean promptFightAgain(Scanner scanner) {
        while (true) {
            System.out.print("Fight again [y/n]? ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("y")) {
                return true;
            }
            if (input.equalsIgnoreCase("n")) {
                return false;
            }
        }
    }

    /**
     * Picks a random monster from the list of monsters.
     *
     * @param monsters the list of monsters
     * @return a randomly selected monster
     */
    public static Monster pickMonster(List<Monster> monsters) {
        return monsters.get(random.nextInt(monsters.size()));
    }

    /**
     * Fetches the treasure class for a given monster.
     *
     * @param monster the monster to fetch the treasure class for
     * @param treasureClasses the map of treasure classes
     * @return the treasure class of the monster
     */
    public static TreasureClass fetchTreasureClass(
            Monster monster,
            Map<String, TreasureClass> treasureClasses) {
        return treasureClasses.get(monster.getTreasureClass());
    }

    /**
     * Generates a base item from the treasure class.
     *
     * @param tc the treasure class
     * @param treasureClasses the map of treasure classes
     * @param armorMap the map of armor items
     * @return a randomly generated base item
     */
    public static Armor generateBaseItem(TreasureClass tc, 
                                         Map<String, TreasureClass> treasureClasses, 
                                         Map<String, Armor> armorMap) {
        String drop = tc.getRandomDrop();
        if (treasureClasses.containsKey(drop)) {
            return generateBaseItem(treasureClasses.get(drop), treasureClasses, armorMap);
        } else {
            return armorMap.get(drop);
        }
    }

    /**
     * Generates base stats for the armor item.
     *
     * @param armor the armor item
     * @return the defense value of the armor
     */
    public static int generateBaseStats(Armor armor) {
        return armor.getDefenseValue();
    }

    /**
     * Generates a random affix from the list of affixes.
     *
     * @param affixes the list of affixes
     * @return a randomly selected affix
     */
    public static Affix generateAffix(List<Affix> affixes) {
        return affixes.get(random.nextInt(affixes.size()));
    }
}
