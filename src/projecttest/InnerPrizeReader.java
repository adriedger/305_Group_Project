/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecttest;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public final class InnerPrizeReader {
    List<InnerPrize> prizes;
    
    public InnerPrizeReader(JsonReader reader) throws IOException {
        //reader.beginObject();
        //reader.skipValue();
        reader.beginArray();
        //reader.skipValue();
        while (reader.hasNext()) {
            prizes.add(readPrize(reader));
        }
        reader.endArray();
        //reader.endObject();
    }

    public InnerPrize readPrize(JsonReader reader) throws IOException {
        int year = 0;
        String category = null;
        int share =  0;
        String motivation = null;
        List<Affiliations> affiliations = null;
        System.out.println("Going over inner prize object");
        reader.beginObject();
        //reader.beginArray();
        //reader.skipValue();
        while (reader.hasNext()) {
            String key = reader.nextName();
            System.out.println(key);
            switch (key) {
                case "year":
                    year = reader.nextInt();
                    break;
                case "category":
                    category = reader.nextString();
                    break;
                case "share":
                    share = reader.nextInt();
                    break;
                case "motivation":
                    motivation = reader.nextString();
                    break;
                //case "affiliation":
                //Need a nested function to make affilation list
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        
        return new InnerPrize(year, category, share, motivation, affiliations);
    }
}
