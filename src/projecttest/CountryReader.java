/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecttest;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public final class CountryReader {
    private final List<Country> allCountries;
    
    public CountryReader(InputStream in) throws IOException {
        this.allCountries = readJsonStream(in);
    }
    
    public List<Country> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return readCountryArray(reader);
        }
    }

    public List<Country> readCountryArray(JsonReader reader) throws IOException {
        List<Country> countries = new ArrayList<>();
        reader.beginObject();
        reader.skipValue();
        reader.beginArray();
        while (reader.hasNext()) {
            countries.add(readCountry(reader));
        }
        reader.endArray();
        reader.endObject();
        return countries;
    }

    public Country readCountry(JsonReader reader) throws IOException {
        String name = null;
        String code = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String key = reader.nextName();
            switch (key) {
                case "name":
                    name = reader.nextString();
                    break;
                case "code":
                    code = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Country(name, code);
    }
    
    public void displayCountries() {
        allCountries.forEach((c) -> {
            System.out.println("Name: " + c.name);
            System.out.println("Code: " + c.code);
            System.out.println();
        });
    }
    
    public List<Prize> readPrizeArray(JsonReader reader) throws IOException {
        List<Prize> prizes = new ArrayList<>();
        reader.beginObject();
        reader.skipValue();
        reader.beginArray();
        while (reader.hasNext()) {
            prizes.add(readPrize(reader));
        }
        reader.endArray();
        reader.endObject();
        return prizes;
    }
    
        public Prize readPrize(JsonReader reader) throws IOException {
        int year = 0;
        String category = null;
        List<Laureate> laureates = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String key = reader.nextName();
            switch (key) {
                case "year":
                    year = reader.nextInt();
                    break;
                case "category":
                    category = reader.nextString();
                    break;
                case "laureates":
                //Need a nested function to make laureate list
                /*
                Function should be like readLaureateArray.
                Likely need to remove the beginObject and skipValue.
                Next readArray should work just like the javadoc example
                 */
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Prize(year, category, laureates);
    }
}
