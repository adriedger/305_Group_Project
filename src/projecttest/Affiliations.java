/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

/**
 * Affiliations Class - holds the Affiliation JSON item. Contains a string for
 * name, city, and country.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class Affiliations {

    String name;
    String city;
    String country;

    /**
     * Affiliations - class constructor, make new affiliation object
     *
     * @param name - name of the affiliation
     * @param city - city of the affiliation
     * @param country - country of the affiliation
     */
    public Affiliations(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

    /**
     * toString - override of the default toString method.
     *
     * @return - Affiliations object in an organized format
     */
    @Override
    public String toString() {
        StringBuilder affiliations = new StringBuilder();
        affiliations.append("\t\t").append("Name: ").append(name).append("\n");
        affiliations.append("\t\t").append("City: ").append(city).append("\n");
        affiliations.append("\t\t").append("Country: ").append(country).append("\n");
        affiliations.append("\n");
        return affiliations.toString();
    }
}
