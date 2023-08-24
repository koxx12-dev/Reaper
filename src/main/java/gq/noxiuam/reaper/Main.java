package gq.noxiuam.reaper;

import gq.noxiuam.reaper.util.StringUtil;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author Noxiuam (github.com/Noxiuam)
 */
public class Main
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
    public static void main(String[] args) {
        OptionParser parser = new OptionParser();
        parser.accepts("debug");
        parser.accepts("allowDuplicates");
        parser.accepts("file").withRequiredArg().ofType(File.class).defaultsTo(new File("mapping.csv"));
        parser.accepts("length").withRequiredArg().ofType(Integer.class).defaultsTo(25);
        parser.accepts("amount").withRequiredArg().ofType(Integer.class).defaultsTo(5500);
        parser.accepts("pattern").withRequiredArg().defaultsTo("Il");

        OptionSet options = parser.parse(args);
        debug = options.has("debug");
        boolean allowDuplicates = options.has("allowDuplicates");
        File file = (File) options.valueOf("file");
        Integer length = (Integer) options.valueOf("length");
        Integer amount = (Integer) options.valueOf("amount");
        String pattern = (String) options.valueOf("pattern");

        // Resolve annoying stackoverflow issue, never use below 10 for obf mappings anyway, it isn't very good.
        if (length < 10) {
            length = 10;
            System.out.println("[WARNING] Lower values may cause errors due to the limited amount of combinations that lower values can give! Setting the value to 10 just in case.");
        }

        /*
         * Check if there is an old mapping in the same directory where the new one will be generated.
         * If there is, then it removes it, this is important that this works otherwise it will just write to the old one.
         */
        if (file.exists() && file.delete()) {
            System.out.println("[Reaper] Removed old mapping file");
        }

        // Create a BufferedWriter for the mapping file.
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));

        System.out.println("Generating mapping...");

        // Writes a new line until it reaches the maximum amount defined.
        for (int i = 0; i < amount; i++) {
            writer.write(StringUtil.generateName(length, pattern, allowDuplicates) + (i == amount - 1 ? "" : "\n"));
        }

        // Close the BufferedWriter.
        writer.close();
    }
}
