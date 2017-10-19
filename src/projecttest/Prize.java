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
    List<InnerLaureate> laureates;
    
    public Prize(int year, String category, List<InnerLaureate> laureates) {
        this.year = year;
        this.category = category;
        this.laureates = laureates;
    }
    
    @Override 
    public String toString() {
        StringBuilder prize = new StringBuilder();
        prize.append("Year: ").append(year).append("\n");
        prize.append("Category: ").append(category).append("\n");
        prize.append("Laureates: \n");
        laureates.forEach((l) -> {
            prize.append(l);
        });
        prize.append("\n");
        
        return prize.toString();
    }
}