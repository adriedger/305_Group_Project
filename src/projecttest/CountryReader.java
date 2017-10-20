/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * CountryReader - parses an input stream in JSON format and creates a list of
 * country objects from the data. Contains a list of Country objects
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public final class CountryReader {

    private final List<Country> allCountries;

    /**
     * CountryReader - class constructor to create new CountryReader object.
     * Constructor uses nested functions to complete the data parse and create
     * the list when it is called
     *
     * @param in - input stream containing the JSON information
     * @throws IOException
     */
    public CountryReader(InputStream in) throws IOException {
        this.allCountries = readJsonStream(in);
    }

    /**
     * readJsonStream - creates a JSON reader (using GSON library)
     *
     * @param in - input stream containing the JSON information
     * @return - a call to the readCountryArray function
     * @throws IOException
     */
    private List<Country> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return readCountryArray(reader);
        }
    }

    /**
     * readCountryArray - creates a list of Country objects
     *
     * @param reader - JSON reader pointed at the input stream containing JSON
     * information
     * @return - List of country objects
     * @throws IOException
     */
    private List<Country> readCountryArray(JsonReader reader) throws IOException {
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

    /**
     * readCountry - creates a single Country object
     *
     * @param reader - JSON reader pointed at the input stream containing JSON
     * information
     * @return - Newly created Country object
     * @throws IOException
     */
    private Country readCountry(JsonReader reader) throws IOException {
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

    /**
     * toString - override of the default toString method.
     *
     * @return - List of Country objects in an organized format
     */
    @Override
    public String toString() {
        StringBuilder countries = new StringBuilder();
        allCountries.forEach((c) -> {
            countries.append(c);
        });
        countries.append("\n");
        return countries.toString();
    }
}
