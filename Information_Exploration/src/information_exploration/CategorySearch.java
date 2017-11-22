/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information_exploration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class CategorySearch implements Command {

    private final List<Laureate> oldList;
    private final String category;
    private final String value;

    public CategorySearch(List<Laureate> list, String category, String value) {
        this.oldList = list;
        this.category = category;
        this.value = value;
    }

    @Override
    public List<Laureate> execute() {

        List<Laureate> newList = new ArrayList<>();

        for (Laureate l : oldList) {
            if (l.getEntry().get(category) != null) {
                if (l.getEntry().get(category).equals(value)) {
                    newList.add(l);
                }
            }

        }
        return newList;
    }

    @Override
    public List <Laureate> undo() {
        return oldList;
    }

}
