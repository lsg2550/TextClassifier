package textclassifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Luis
 */
public class Table {

    private List<String> word = new ArrayList();
    private String category;

    public Table(String category) {
        this.category = category;
    }

    public boolean wordExists(String foundWord) {
        for (String string : word) {
            if (string.equals(foundWord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    public int getCounter(String foundWord) {
        return Collections.frequency(word, word.get(word.indexOf(foundWord)));
    }

    /**
     * @return the word
     */
    public List<String> getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(List<String> word) {
        this.word = word;
    }
}
