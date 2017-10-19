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
            String dcc, String dct,String g, List<InnerPrize> p) {
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

}
