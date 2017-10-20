/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

import java.util.List;

/**
 * Prize Class - contains the information inside of a Prize JSON item. Contains
 * year, category and a list of laureates.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class Prize {

    int year;
    String category;
    List<InnerLaureate> laureates;

    /**
     * Prize - class constructor to create a new Prize object.
     *
     * @param year - year of the prize
     * @param category - category of the prize
     * @param laureates - list of laureates that won the prize
     */
    public Prize(int year, String category, List<InnerLaureate> laureates) {
        this.year = year;
        this.category = category;
        this.laureates = laureates;
    }

    /**
     * toString - override of the default toString method.
     *
     * @return - Prize object in an organized format
     */
    @Override
    public String toString() {
        StringBuilder prize = new StringBuilder();
        prize.append("Year: ").append(year).append("\n");
        prize.append("Category: ").append(category).append("\n");
        prize.append("Laureates: \n");
        laureates.forEach((l) -> {
            prize.append(l);
        });
        prize.append("\n");

        return prize.toString();
    }
}
