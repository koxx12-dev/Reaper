package me.noxiuam.reaper;

import me.noxiuam.reaper.util.StringUtil;

import lombok.*;
import java.io.*;
import java.util.Scanner;

public class Reaper
{
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

        System.out.println("How long should each name be? (ex: 5)");
        String nameLengthValue = input.nextLine();

        /*
         * Creates a BufferedWriter for the mapping file.
         */
        System.out.println("Generating mapping...");

        /*
         * Checks to see if there is an old mapping in the same directory where the new one will be generated.
         * If there is, then it removes it, this is important that this works otherwise it will just write to the old one.
         */
        if (file.exists() && file.delete())
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
            writer.write(StringUtil.generateName(Integer.parseInt(nameLengthValue)) + (i == Integer.parseInt(nameAmountValue) - 1 ? "" : "\n"));
        }

        /*
         * Closes the BufferedWriter.
         */
        writer.close();
    }
}
