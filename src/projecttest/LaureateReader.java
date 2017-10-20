/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * LaureateReader - parses an input stream in JSON format and creates a list of
 * laureate objects from the data. Contains a list of laureate objects
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public final class LaureateReader {

    private final List<Laureate> allLaureates;

    /**
     * LaureateReader - class constructor to create new LaureateReader object.
     * Constructor uses nested functions to complete the data parse and create
     * the list when it is called
     *
     * @param in - input stream containing the JSON information
     * @throws IOException
     */
    public LaureateReader(InputStream in) throws IOException {
        this.allLaureates = readJsonStream(in);
    }

    /**
     * readJsonStream - creates a JSON reader (using GSON library)
     *
     * @param in - input stream containing the JSON information
     * @return - a call to the readLaureateArray function
     * @throws IOException
     */
    private List<Laureate> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return readLaureateArray(reader);
        }
    }

    /**
     * readLaureateArray - creates a list of Laureate objects
     *
     * @param reader - JSON reader pointed at the input stream containing JSON
     * information
     * @return - List of laureate objects
     * @throws IOException
     */
    private List<Laureate> readLaureateArray(JsonReader reader) throws IOException {
        List<Laureate> laureates = new ArrayList<>();
        reader.beginObject();
        reader.skipValue();
        reader.beginArray();
        while (reader.hasNext()) {
            laureates.add(readLaureate(reader));
        }
        reader.endArray();
        reader.endObject();
        return laureates;
    }

    /**
     * readLaureate - creates a single Country object
     *
     * @param reader - JSON reader pointed at the input stream containing JSON
     * information
     * @return - Newly created Laureate object
     * @throws IOException
     */
    private Laureate readLaureate(JsonReader reader) throws IOException {
        int id = 0;
        String firstName = null;
        String surName = null;
        String born = null;
        String died = null;
        String bornCountry = null;
        String bornCountryCode = null;
        String bornCity = null;
        String diedCountry = null;
        String diedCountryCode = null;
        String diedCity = null;
        String gender = null;
        List<InnerPrize> prizes = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String key = reader.nextName();
            switch (key) {
                case "id":
                    id = reader.nextInt();
                    break;
                case "firstname":
                    firstName = reader.nextString();
                    break;
                case "surname":
                    surName = reader.nextString();
                    break;
                case "born":
                    born = reader.nextString();
                    break;
                case "died":
                    died = reader.nextString();
                    break;
                case "bornCountry":
                    bornCountry = reader.nextString();
                    break;
                case "bornCountryCode":
                    bornCountryCode = reader.nextString();
                    break;
                case "bornCity":
                    bornCity = reader.nextString();
                    break;
                case "diedCountry":
                    diedCountry = reader.nextString();
                    break;
                case "diedCountryCode":
                    diedCountryCode = reader.nextString();
                    break;
                case "diedCity":
                    diedCity = reader.nextString();
                    break;
                case "gender":
                    gender = reader.nextString();
                    break;
                case "prizes":
                    prizes = InnerPrizeReader(reader);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Laureate(id, firstName, surName, born, died, bornCountry,
                bornCountryCode, bornCity, diedCountry, diedCountryCode,
                diedCity, gender, prizes);

    }

    /**
     * toString - override of the default toString method.
     *
     * @return - List of Country objects in an organized format
     */
    @Override
    public String toString() {
        StringBuilder laureates = new StringBuilder();
        allLaureates.forEach((l) -> {
            laureates.append(l);
        });
        return laureates.toString();
    }

    /**
     * InnerPrizeReader - creates a list of InnerPrize objects from the JSON
     * information. InnerPrizes are the nested objects stored inside the
     * laureate JSON items
     *
     * @param reader - JSON reader pointed at the input stream containing the
     * JSON information
     * @return - list InnerPrize objects
     * @throws IOException
     */
    private List<InnerPrize> InnerPrizeReader(JsonReader reader) throws IOException {
        List<InnerPrize> prizes = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            prizes.add(readPrize(reader));
        }
        reader.endArray();
        return prizes;
    }

    /**
     * readPrize - creates an InnerPrize object from the JSON information.
     *
     * @param reader - JSON reader pointed at the input stream containing the
     * JSON information.
     * @return - new InnerPrize object
     * @throws IOException
     */
    private InnerPrize readPrize(JsonReader reader) throws IOException {
        int year = 0;
        String category = null;
        int share = 0;
        String motivation = null;
        List<Affiliations> affiliations = null;
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
                case "share":
                    share = reader.nextInt();
                    break;
                case "motivation":
                    motivation = reader.nextString();
                    break;
                case "affiliations":
                    affiliations = InnerAffiliationsReader(reader);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        //System.out.println(reader.peek());
        return new InnerPrize(year, category, share, motivation, affiliations);
    }

    /**
     * InnerAffiliatoinsReader - creates a list of Affiliations objects.
     * Affiliations are nested JSON items inside the prize information which is
     * nested inside the Laureate item. (Laureate -> Prize - > Affiliation)
     *
     * @param reader - JSON reader pointed to the input stream containing the
     * JSON information
     * @return - list of affiliations objects
     * @throws IOException
     */
    private List<Affiliations> InnerAffiliationsReader(JsonReader reader) throws IOException {
        List<Affiliations> affiliations = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            affiliations.add(readAffiliations(reader));
        }
        reader.endArray();
        return affiliations;
    }

    /**
     * readAffiliations - creates an Affiliations object from the JSON
     * information.
     *
     * @param reader - JSON reader pointed towards the input stream containing
     * JSON information.
     *
     * @return - new affiliations objects
     * @throws IOException
     */
    private Affiliations readAffiliations(JsonReader reader) throws IOException {
        String name = null;
        String city = null;
        String country = null;
        if (reader.peek() == JsonToken.BEGIN_ARRAY) {
            reader.beginArray();
            reader.endArray();
        } else {
            reader.beginObject();
            while (reader.hasNext()) {
                String key = reader.nextName();
                switch (key) {
                    case "name":
                        name = reader.nextString();
                        break;
                    case "city":
                        city = reader.nextString();
                        break;
                    case "country":
                        country = reader.nextString();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
        }
        return new Affiliations(name, city, country);
    }
}
