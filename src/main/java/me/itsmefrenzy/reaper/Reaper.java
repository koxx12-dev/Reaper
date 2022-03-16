package me.itsmefrenzy.reaper;

import me.itsmefrenzy.reaper.util.StringUtil;

import lombok.*;
import java.io.*;
import java.util.Scanner;

public class Reaper
{

    /*
     * Debug mode, only use this if you want to see the debug information being printed in console.
     */
    public static boolean debug = false;

    /*
     * Main function.
     * @SneakyThrows is for the BufferedWriter.
     */
    @SneakyThrows
    public static void main(String[] args)
    {
        File file = new File("mapping.csv");
        Scanner input = new Scanner(System.in);

        System.out.println("How many names do you want to generate? (ex: 100)");
        String nameAmountValue = input.nextLine();

        System.out.println("How long should each name be? (ex: 10-15)");
        int nameLengthValue = input.nextInt();

        if (nameLengthValue < 10) {
            nameLengthValue = 10;
            System.out.println("Lower values may cause errors due to the limited amount of combinations that lower values can give, setting the value to 10 just in case.");
        }

        /*
         * Creates a BufferedWriter for the mapping file.
         */
        System.out.println("Generating mapping...");

        /*
         * Checks to see if there is an old mapping in the same directory where the new one will be generated.
         * If there is, then it removes it, this is important that this works otherwise it will just write to the old one.
         */
        if (file.exists() && file.delete() && debug)
        {
            System.out.println("Found an old mapping, removing it!");
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));

        /*
         * A for loop that writes a new line until it reaches the "nameAmountValue"
         * It writes to the file a randomly generated String of I's and lowercase L's, followed by a line break.
         */
        for (int i = 0; i < Integer.parseInt(nameAmountValue); i++)
        {
            writer.write(StringUtil.generateName(nameLengthValue) + (i == Integer.parseInt(nameAmountValue) - 1 ? "" : "\n"));
        }

        /*
         * Closes the BufferedWriter.
         */
        writer.close();
    }
}
