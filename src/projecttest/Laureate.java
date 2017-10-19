/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecttest;

import java.util.List;

/**
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
