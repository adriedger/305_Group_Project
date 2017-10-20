/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

import java.util.List;

/**
 * InnerPrize Class - contains the prize information nested inside a Laureate
 * JSON item. Contains year, category, share, motivation, affiliations.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class InnerPrize {

    int year;
    String category;
    int share;
    String motivation;
    List<Affiliations> affiliations;

    /**
     * InnerPrize - class constructor to create new InnerPrize object
     *
     * @param year - year of the prize
     * @param category - category of the prize
     * @param share - share of the prize
     * @param motivation - motivation for the prize
     * @param affiliations - list of affiliations for the prize
     */
    public InnerPrize(int year, String category, int share,
            String motivation, List<Affiliations> affiliations) {
        this.year = year;
        this.category = category;
        this.share = share;
        this.motivation = motivation;
        this.affiliations = affiliations;
    }

    /**
     * toString - override of the default toString method.
     *
     * @return - InnerPrize object in an organized format
     */
    @Override
    public String toString() {
        StringBuilder prize = new StringBuilder();
        prize.append("\t").append("Year: ").append(year).append("\n");
        prize.append("\t").append("Category: ").append(category).append("\n");
        prize.append("\t").append("Share: ").append(share).append("\n");
        prize.append("\t").append("Motivation: ").append(motivation).append("\n");
        prize.append("\t").append("Affiliations: ").append("\n");
        affiliations.forEach((p) -> {
            prize.append(p);
        });
        prize.append("\n");
        return prize.toString();
    }
}
