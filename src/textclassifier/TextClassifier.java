package textclassifier;

import java.io.File;
import java.util.Scanner;
import utils.benchmarking.Logging;
import utils.benchmarking.MemoryUsage;
import utils.io.IO;

/**
 *
 * @author Luis
 */
public class TextClassifier {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean trainingConfigSelected = true,
                dataHasBeenProcessed = false;

        //AmtOfTextFiles, AmtOfCategories, AmtOfFrequentWords
        TrainingConfig tc1 = new TrainingConfig(10, 5, 25),
                tc2 = new TrainingConfig(20, 5, 25),
                tc3 = new TrainingConfig(10, 10, 50);

        prompt();

        while (trainingConfigSelected) {
            switch (input.next().toLowerCase().charAt(0)) {
                case 'a':
                    Logging.setStartTime();
                    tc1.preProcessData();
                    Logging.setEndTime();

                    dataHasBeenProcessed = true;
                    break;
                case 'b':
                    Logging.setStartTime();
                    tc2.preProcessData();
                    Logging.setEndTime();

                    dataHasBeenProcessed = true;
                    break;
                case 'c':
                    Logging.setStartTime();
                    tc3.preProcessData();
                    Logging.setEndTime();

                    dataHasBeenProcessed = true;
                    break;
                case 'q':
                    System.exit(0);
                    break;
                case 'z':
                    if (!dataHasBeenProcessed) {
                        System.out.println("Please process data first using 'a', 'b', or 'c'\n");
                    } else {
                        System.out.println("Please insert full path to text file you'd like to compare.");

                        TrainingConfig tcFileRead = new TrainingConfig();
                        File textToRead = IO.readDirectoryListOfFiles(input.next());

                        if (textToRead != null) {
                            tcFileRead.preProcessDataThenCompare(textToRead);
                        } else {
                            System.out.println("No File Selected or File Not Found.");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid Input, please try again.");
                    break;
            }
            System.out.println("\n\nTime to Process Data: " + Logging.benchmarkTime() + "ms"
                    + "\nMemory Used: " + MemoryUsage.memoryUsageInMBytes() + "MB");
            prompt();
        }
    }

    private static void prompt() {
        System.out.println("\nWhich Training Configuration would you like to run: "
                + "\nTC1: 10 TextFiles out of each 5 Categories recording 25 frequent words."
                + "\nTC2: 20 TextFiles out of each 5 Categories recording 25 frequent words."
                + "\nTC3: 10 TextFiles out of each 10 Categories recording 50 frequent words."
                + "\nType 'a' for TC1, 'b' for TC2, 'c' for TC3, or 'q' to close the application."
                + "\nType 'z' to load a file to read.");
    }
}
