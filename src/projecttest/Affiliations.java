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
public class Affiliations {
    String name;
    String city;
    String country;
    
    public Affiliations(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

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
