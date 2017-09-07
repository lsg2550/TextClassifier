package textclassifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis
 */
public class TableList {

    public static List<Table> list = new ArrayList<Table>();

    public static boolean lookForCategory(String category) { //Checks if Category already exists; Used in Training
        for (Table table : list) {
            if (table.getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }

    public static void printOutCategoryAndWord() { //Prints out table
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("printedTables.txt")))) {

            list.forEach((table) -> {
                System.out.println("Category: " + table.getCategory());

                for (String string : table.getWord()) {
                    String temp = "\nWord: " + string + "\nFrequency: " + table.getCounter(string);

                    try {
                        bw.write(temp);

                        //if (table.getCounter(string) > 1) {
                        System.out.println(temp);
                        //}

                    } catch (IOException ex) {
                    }

                }

            });

        } catch (IOException e) {

        }
    }

    public static void compare(Table table) {
        String likelyCategory = "";
        int maxMatchCounter = 0;

        for (Table category : list) { //Table per index in list
            int currentMatchCounter = 0;

            for (String string : table.getWord()) { //Table from user provided file

                for (String words : category.getWord()) { //Compares Strings
                    if (string.equals(words)) {
                        currentMatchCounter++;
                    }
                }

                if (currentMatchCounter > maxMatchCounter) {
                    maxMatchCounter = currentMatchCounter;
                    likelyCategory = list.get(list.indexOf(category)).getCategory();

                    //System.out.println(currentMatchCounter + " Matches from Current Category Index: " + list.indexOf(category) + " Likely belongs to " + likelyCategory); //Logging
                }
            }

            System.out.println("Current Category Index " + list.indexOf(category) + " which is " + likelyCategory + " with a probability distribution of (20%): " + NaiveBayes.naiveBayesTwenty(maxMatchCounter)); //Currently hard coding 20%
        }

        System.out.println("This file is likely from the " + likelyCategory + " category. With " + maxMatchCounter + " matches.");
    }

    private void benchmarkingSuite() {

    }
}
