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
public class Prize {
    int year;
    String category;
    List<Laureate> laureates;
    
    public Prize(int year, String category, List<Laureate> laureates) {
        this.year = year;
        this.category = category;
        this.laureates = laureates;
    }
}