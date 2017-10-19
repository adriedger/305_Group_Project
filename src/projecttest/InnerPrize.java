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
public class InnerPrize {
    int year;
    String category;
    int share;
    String motivation;
    List<Affiliations> affiliations;
    
    public InnerPrize(int year, String category, int share,
                    String motivation,List<Affiliations> affiliations) {
        this.year = year;
        this.category = category;
        this.share = share;
        this.motivation = motivation;
        this.affiliations = affiliations;
    }
}
