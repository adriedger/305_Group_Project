/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

/**
 * Country Class - holds the data for a Country JSON item. Contains country's
 * name and code.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class Country {

    String name;
    String code;

    /**
     * Country Constructor - creates new Country object
     *
     * @param name - country name
     * @param code - country code
     */
    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * toString - override of the default toString method.
     *
     * @return - Country object in an organized format
     */
    @Override
    public String toString() {
        StringBuilder country = new StringBuilder();
        country.append("Name: ").append(name).append("\n");
        country.append("Code: ").append(code).append("\n");
        country.append("\n");

        return country.toString();
    }

}
