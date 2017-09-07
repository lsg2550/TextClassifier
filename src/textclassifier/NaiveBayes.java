/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassifier;

/**
 *
 * @author Luis
 */
public class NaiveBayes {

    public static double naiveBayesFive(double matches) { //Input amount of matches
        return matches * .05;
    }

    public static double naiveBayesTen(double matches) { //Input amount of matches
        return matches * .10;
    }

    public static double naiveBayesFifteen(double matches) { //Input amount of matches
        return matches * .15;
    }

    public static double naiveBayesTwenty(double matches) { //Input amount of matches
        return matches * .20;
    }

    public static double naiveBayesTwentyFive(double matches) { //Input amount of matches
        return matches * .25;
    }

    public static double naiveBayesFifty(double matches) { //Input amount of matches
        return matches * .50;
    }
}
