/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

/**
 * InnerLaureate Class - contains the laureate information nested inside a prize
 * JSON item. Contains id, firstname, surname, motivation and share.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class InnerLaureate {

    int id;
    String firstName;
    String surName;
    String motivation;
    int share;

    /**
     * InnerLaureate - class constructor, makes news InnerLaureate object
     *
     * @param id - laureate's id
     * @param firstname - laureate's first name
     * @param surname - laureate's last name
     * @param motivation - laureate's motivation
     * @param share - laureates share of the prize
     */
    public InnerLaureate(int id, String firstname, String surname,
            String motivation, int share) {
        this.id = id;
        this.firstName = firstname;
        this.surName = surname;
        this.motivation = motivation;
        this.share = share;
    }

    /**
     * toString - override of the default toString method.
     *
     * @return - InnerLaureate object in an organized format
     */
    @Override
    public String toString() {
        StringBuilder laureate = new StringBuilder();
        laureate.append("\t").append("ID: ").append(id).append("\n");
        laureate.append("\t").append("FirstName: ").append(firstName).append("\n");
        laureate.append("\t").append("SurName: ").append(surName).append("\n");
        laureate.append("\t").append("Motivation: ").append(motivation).append("\n");
        laureate.append("\t").append("Share: ").append(share).append("\n");

        return laureate.toString();
    }

}
