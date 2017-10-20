/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

import java.util.List;

/**
 * Laureate Class - contains the laureate information contained inside a
 * laureate JSON item. Contains id, firstname, surname, born, died, born
 * country, born country code, born city, died country, died country code, died
 * city, gender, and a list of InnerPrize objects. NOTE: These values can be
 * NULL.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class Laureate {

    int id;
    String firstName;
    String surName;
    String born;
    String died;
    String bornCountry;
    String bornCountryCode;
    String bornCity;
    String diedCountry;
    String diedCountryCode;
    String diedCity;
    String gender;
    List<InnerPrize> prizes;

    /**
     * Laureate - class constructor to make new laureate object
     *
     * @param id - laureates id
     * @param fn - laureates first name
     * @param sn - laureates last name
     * @param b - laureates birth date
     * @param d - laureates date of death
     * @param bc - laureates birth country
     * @param bcc - laureates birth country code
     * @param bct - laureates birth city
     * @param dc - laureates death country
     * @param dcc - laureates death country code
     * @param dct - laureates death city
     * @param g - laureates gender
     * @param p - list of prizes laureate has won
     */
    public Laureate(int id, String fn, String sn, String b, String d,
            String bc, String bcc, String bct, String dc,
            String dcc, String dct, String g, List<InnerPrize> p) {
        this.id = id;
        this.firstName = fn;
        this.surName = sn;
        this.born = b;
        this.died = d;
        this.bornCountry = bc;
        this.bornCountryCode = bcc;
        this.bornCity = bct;
        this.diedCountry = dc;
        this.diedCountryCode = dcc;
        this.diedCity = dct;
        this.gender = g;
        this.prizes = p;
    }

    /**
     * toString - override of the default toString method.
     *
     * @return - Laureate object in an organized format
     */
    @Override
    public String toString() {
        StringBuilder laureate = new StringBuilder();
        laureate.append("ID: ").append(id).append("\n");
        laureate.append("FirsName: ").append(firstName).append("\n");
        laureate.append("SurName: ").append(surName).append("\n");
        laureate.append("Born: ").append(born).append("\n");
        laureate.append("Died: ").append(died).append("\n");
        laureate.append("BornCountry: ").append(bornCountry).append("\n");
        laureate.append("BornCountryCode: ").append(bornCountryCode).append("\n");
        laureate.append("BornCity: ").append(bornCity).append("\n");
        laureate.append("DiedCountry: ").append(diedCountry).append("\n");
        laureate.append("DiedCountryCode: ").append(diedCountryCode).append("\n");
        laureate.append("DiedCity: ").append(diedCity).append("\n");
        laureate.append("Gender: ").append(gender).append("\n");
        laureate.append("Prizes: \n");
        prizes.forEach((p) -> {
            laureate.append(p);
        });
        laureate.append("\n");
        return laureate.toString();

    }
}
