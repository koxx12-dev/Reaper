package gq.noxiuam.reaper.util;

import gq.noxiuam.reaper.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringUtil {

    /**
     * All the generated names so far.
     * <br>
     * Primarily to prevent duplicates.
     */
    private static final List<String> generatedNames = new ArrayList<>();

    /**
     * Generates a mapping name and adds it to {@link #generatedNames}
     * <br>
     * Runs through a loop that randomly appends characters to the obfuscatedName StringBuilder.
     *
     * @param nameLength Length of the obfuscated name that will be generated.
     * @param pattern Pattern of the obfuscated name that will be generated.
     * @param allowDuplicates If duplicate patterns are allowed.
     * @return The randomly generated obfuscated name.
     */
    public static String generateName(int nameLength, String pattern, boolean allowDuplicates) {
        StringBuilder obfuscatedName = new StringBuilder();

        for (int i = 1; i <= nameLength; i++) {
            obfuscatedName.append(pattern.toCharArray()[(new Random()).nextInt(pattern.length())]);
        }


        // Checks the list of already generated names for the current one.
        if (generatedNames.contains(String.valueOf(obfuscatedName)) && !allowDuplicates) {
            String oldName = String.valueOf(obfuscatedName);
            obfuscatedName = new StringBuilder(generateName(nameLength, pattern, false));
            if (Main.debug) {
                System.out.println("[Reaper] Found duplicate: " + oldName + " -> " + obfuscatedName);
            }
        }

        // Adds the new name to the generatedNames List.
        if (!allowDuplicates) {
            generatedNames.add(String.valueOf(obfuscatedName));
        }

        return obfuscatedName.toString();
    }
}
