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
public class Country {
    String name;
    String code;

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }
    
    @Override
    public String toString(){
        StringBuilder country = new StringBuilder();
        country.append("Name: ").append(name).append("\n");
        country.append("Code: ").append(code).append("\n");
        country.append("\n");
        
        return country.toString();
    }

}
