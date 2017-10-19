/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecttest;

/**
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class InnerLaureate {
    int id;
    String firstName;
    String surName;
    String motivation;
    int share;
    
    public InnerLaureate(int id, String firstname, String surname,
                            String motivation,int share) {
        this.id = id;
        this.firstName = firstname;
        this.surName = surname;
        this.motivation = motivation;
        this.share = share;
    }
    
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
