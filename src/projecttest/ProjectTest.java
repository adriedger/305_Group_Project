/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

import java.io.InputStream;
import java.net.URL;

/**
 * ProjectTest Class - main function currently used for testing the interface.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class ProjectTest {

    /**
     * @param ignored
     * @throws java.lang.Exception
     */
    public static void main(String[] ignored) throws Exception {
        InputStream input1 = new URL("http://api.nobelprize.org/v1/country.json").openStream();
        CountryReader countries = new CountryReader(input1);
        //System.out.println(countries);
        InputStream input2 = new URL("http://api.nobelprize.org/v1/prize.json?").openStream();
        PrizeReader prizes = new PrizeReader(input2);
        //System.out.println(prizes);
        InputStream input3 = new URL("http://api.nobelprize.org/v1/laureate.json?").openStream();
        LaureateReader laureates = new LaureateReader(input3);
        //System.out.println(laureates);
    }
}
