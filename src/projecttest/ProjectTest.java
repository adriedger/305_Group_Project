/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecttest;

import java.io.InputStream;
import java.net.URL;
/**
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class ProjectTest {    
    /**
     * @param ignored
     * @throws java.lang.Exception
     */
    public static void main(String[] ignored) throws Exception {
        InputStream input = new URL("http://api.nobelprize.org/v1/country.json").openStream();
        CountryReader countries = new CountryReader(input);
        countries.displayCountries();
        input = new URL("http://api.nobelprize.org/v1/prize.json?").openStream();
        PrizeReader prizes = new PrizeReader(input);
        prizes.displayPrizes();
        input = new URL("http://api.nobelprize.org/v1/laureate.json?").openStream();
        LaureateReader laureates = new LaureateReader(input);
        laureates.displayLaureates();
    }
}
