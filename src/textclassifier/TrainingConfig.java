package textclassifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Luis
 */
public class TrainingConfig {

    private final File TRAINING_DATA_PATH = new File("mini_newsgroups/");
    private final File[] LIST_OF_CATEGORIES = TRAINING_DATA_PATH.listFiles();
    private final String[] STOP_WORDS = {"a", "about", "above", "above", "across",
        "after", "afterwards", "again", "against", "all", "almost", "alone", "along",
        "already", "also", "although", "always", "am", "among", "amongst", "amoungst",
        "amount", "an", "and", "another", "any", "anyhow", "anyone", "anything", "anyway",
        "anywhere", "are", "around", "as", "at", "back", "be", "became", "because", "become",
        "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below",
        "beside", "besides", "between", "beyond", "bill", "both", "bottom", "but", "by",
        "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de",
        "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight",
        "either", "eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever",
        "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify",
        "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found",
        "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt",
        "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers",
        "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc",
        "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly",
        "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more",
        "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither",
        "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing",
        "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others",
        "otherwise", "our", "ours", "ourselves", "out", "over", "own", "part", "per", "perhaps", "please",
        "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several",
        "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow",
        "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system",
        "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence",
        "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they",
        "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout",
        "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty",
        "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well",
        "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter",
        "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which",
        "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will",
        "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself",
        "yourselves", "the"};
    private final char[] SPECIAL_CHARACTERS = {'!', '@', '#', '$', '%', '^',
        '&', '*', '(', ')', '-', '=', '|', ']', '[', '}', '{', '/', '?',
        '>', ',', '<', '+', ':', ';', '.', '`', '~', '"', '\\', '_'};

    private int amtOfTextFiles = 0,
            amtOfCategories = 0,
            amtOfFrequentWords = 0;

    private Table table;

    public TrainingConfig() {
    }

    public TrainingConfig(int amtOfTextFiles, int amtOfCategories, int amtOfFrequentWords) {
        this.amtOfTextFiles = amtOfTextFiles;
        this.amtOfCategories = amtOfCategories;
        this.amtOfFrequentWords = amtOfFrequentWords;
    }

    public void preProcessDataThenCompare(File file) {
        BufferedReader br;

        /*Due to issues with Java's regex, I had to hardcode the substring & splitting*/
        String nameOfCategory = file.getParentFile().getName();
        //nameOfCategory = nameOfCategory.split("\\.")[0];

        //System.out.println(nameOfCategory); //Logging
        System.out.println("Current Category: " + nameOfCategory);
        System.out.println("Current File: " + file.toString() + "\n");

        table = new Table(nameOfCategory); //

        try {
            br = Files.newBufferedReader(Paths.get(file.toURI()));

            for (String line = null; (line = br.readLine()) != null;) {
                checker(line);
            }

        } catch (IOException ex) {
        }

        System.out.println("");
        //TableList.printOutCategoryAndWord();
        TableList.compare(table);
    }

    public void preProcessData() {
        BufferedReader br;
        boolean categoryExists;

        for (int i = 0; i < amtOfCategories; i++) {
            /*Loads File*/
            File[] temp = LIST_OF_CATEGORIES[i].listFiles();

            /*Due to issues with Java's regex, I had to hardcode the substring & splitting*/
            String nameOfCategory = LIST_OF_CATEGORIES[i].getName();
            //nameOfCategory = nameOfCategory.substring(16);
            //nameOfCategory = nameOfCategory.split("\\.")[0];

            //System.out.println(nameOfCategory); //Logging
            System.out.println("Current Category: " + nameOfCategory);

            categoryExists = TableList.lookForCategory(nameOfCategory); //True if the category already exists
            if (!categoryExists) {
                table = new Table(nameOfCategory); //
            }

            /* WHERE THE FILE READING HAPPENS */
            for (int j = 0; j < amtOfTextFiles; j++) {
                System.out.println("Current File: " + temp[j].toString() + "\n");

                try {
                    br = Files.newBufferedReader(Paths.get(temp[j].toURI()));

                    for (String line = null; (line = br.readLine()) != null;) {
                        checker(line);
                    }

                    System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
                } catch (IOException ex) {
                }

            }

            if (!categoryExists) { //If we are under the same category, don't insert a new table
                TableList.list.add(table);
            }

            System.out.println("");
        }

        TableList.printOutCategoryAndWord();
    }

    private void checker(String line) {
        for (int i = 0; i < line.length(); i++) { //Scans the line character by character, replacing special characters with a blank
            for (int j = 0; j < SPECIAL_CHARACTERS.length; j++) {
                if (line.charAt(i) == SPECIAL_CHARACTERS[j]) {
                    //System.out.println("Character: " + line.charAt(i) + " matched with: " + SPECIAL_CHARACTERS[j]); //Logging
                    line = line.replace(line.charAt(i), ' ');
                }
            }
        }

        /*The code would be cleaner & efficient if I moved it to another method, however I want to get it working first before I do that*/
        //It looks for words as "whitespace - any character within the range of 1 - 20 - whitespace"; basically looking for words *whitespace*processor*whitespace*, then the whitespace will be trimmed later leaving only "processor"
        String wordRegex = "\\s[A-Za-z]{1,20}\\s", wordRegex1 = "[A-Za-z]{1,20}\\s";
        String[] splitLine = line.split(wordRegex); //Splits each line using the regular expression above

        for (int i = 0; i < splitLine.length; i++) {
            String temp = splitLine[i].trim().toLowerCase();

            for (int j = 0; j < STOP_WORDS.length; j++) {
                if (temp.equals(STOP_WORDS[i]) || temp.contains(STOP_WORDS[i])) { //If the word is something not meant to be recorded, make it null so that the next conditional can ignore it
                    temp = null;
                    break;
                } else { //Will split the word up again (like a second run) - not efficient, but regex is giving me a hard time
                    String[] innerSplitLine = temp.split(wordRegex1);

                    for (int k = 0; k < innerSplitLine.length; k++) {
                        String innerTemp = innerSplitLine[k].trim().toLowerCase();

                        for (int l = 0; l < innerSplitLine.length; l++) {
                            if (innerTemp.equals(STOP_WORDS[i])) {
                                innerTemp = null;
                                break;
                            }
                        }

                        checkAndInsertToTable(innerTemp);
                    }
                }
            }
        }
    }

    private void checkAndInsertToTable(String line) {
        if (line != null) { //If the line isn't a whitespace, then print out the string
            if (!table.wordExists(line)) { //If the word doesn't exist, add it to the table
                table.getWord().add(line);
            }
            //System.out.println("Line: " + line); //Logging Purposes
        }
    }

    /**
     * @return the amtOfTextFiles
     */
    public int getAmtOfTextFiles() {
        return amtOfTextFiles;
    }

    /**
     * @param amtOfTextFiles the amtOfTextFiles to set
     */
    public void setAmtOfTextFiles(int amtOfTextFiles) {
        this.amtOfTextFiles = amtOfTextFiles;
    }

    /**
     * @return the amtOfCategories
     */
    public int getAmtOfCategories() {
        return amtOfCategories;
    }

    /**
     * @param amtOfCategories the amtOfCategories to set
     */
    public void setAmtOfCategories(int amtOfCategories) {
        this.amtOfCategories = amtOfCategories;
    }

    /**
     * @return the amtOfFrequentWords
     */
    public int getAmtOfFrequentWords() {
        return amtOfFrequentWords;
    }

    /**
     * @param amtOfFrequentWords the amtOfFrequentWords to set
     */
    public void setAmtOfFrequentWords(int amtOfFrequentWords) {
        this.amtOfFrequentWords = amtOfFrequentWords;
    }
}
