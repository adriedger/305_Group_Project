/*
 * CMPT 305 - Group Project (Fall 2017)
 * Group 6
 *
 */
package information_exploration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CountryList class - contains a list of all countries that appear in a given
 * list of laureates.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class CountryList {

    ArrayList<String> countries;

    /**
     * CountryList - creates a list of Country's that appear in the given list
     * of laureates
     *
     * @param list - the list of laureates to check
     */
    public CountryList(List<Laureate> list) {
        countries = new ArrayList<>();
        String temp;

        for (Laureate l : list) {
            if (l.getEntry().get("country") != null) {
                temp = l.getEntry().get("country").toString();
                if (!countries.contains(temp) && !temp.equals("bornCountry")) {
                    countries.add(temp);
                }
            }
        }
        Collections.sort(countries);
    }

    /**
     * toString - outputs the data in the list of countries
     *
     * @return - CountryList object in string format
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        countries.forEach((c) -> {
            builder.append(c);
            builder.append("\n");
        });
        return builder.toString();
    }
}
