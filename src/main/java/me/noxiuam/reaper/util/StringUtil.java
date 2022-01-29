package me.noxiuam.reaper.util;

import java.util.*;

public class StringUtil
{
    /*
     * A List of all the generated names so far.
     * This is mainly to prevent duplicates, as you will see later on.
     */
    private static final List<String> generatedNames = new ArrayList<>();

    /**
     * Generates a mapping name.
     * Runs through a for loop that randomly appends a random character in the given scheme to the obfuscatedName StringBuilder.
     *
     * @param nameLength the length of the obfuscated name that will be generated.
     * @return the randomly generated obfuscated name.
     */
    public static String generateName(int nameLength)
    {
        StringBuilder obfuscatedName = new StringBuilder();

        for (int i = 1; i <= nameLength; i++)
        {
            obfuscatedName.append(new String[]{"I", "l"}[(new Random()).nextInt(2)]);
        }

        /*
         * Checks the list of already generated names for the current one.
         * If it finds one, it prints that it has, and it sets the duplicated name to a new one.
         *
         * Duplicates will always be something new, since it gets generated from the same method that checks if it's a duplicate name or not.
         */
        if (generatedNames.contains(String.valueOf(obfuscatedName)))
        {
            obfuscatedName = new StringBuilder(generateName(nameLength));
            System.out.println("Found duplicate! New Name: " + obfuscatedName);
        }

        /*
         * Adds the new name to the generatedNames List.
         * This is to prevent duplicate names, as seen above.
         *
         * It will return the string afterwards.
         */
        generatedNames.add(String.valueOf(obfuscatedName));
        return obfuscatedName.toString();
    }
}
