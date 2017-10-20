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
 * PrizeReader - parses an input stream in JSON format and creates a list of
 * Prize objects from the data. Contains a list of Prize objects
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public final class PrizeReader {

    private final List<Prize> allPrizes;

    /**
     * PrizeReader - class constructor to create new PrizeReader object.
     * Constructor uses nested functions to complete the data parse and create
     * the list when it is called
     *
     * @param in - input stream containing the JSON information
     * @throws IOException
     */
    public PrizeReader(InputStream in) throws IOException {
        this.allPrizes = readJsonStream(in);
    }

    /**
     * readJsonStream - creates a JSON reader (using GSON library)
     *
     * @param in - input stream containing the JSON information
     * @return - a call to the readPrizeArray function
     * @throws IOException
     */
    private List<Prize> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return readPrizeArray(reader);
        }
    }

    /**
     * readPrizeArray - creates a list of Prize objects
     *
     * @param reader - JSON reader pointed at the input stream containing JSON
     * information
     * @return - List of Prize objects
     * @throws IOException
     */
    private List<Prize> readPrizeArray(JsonReader reader) throws IOException {
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

    /**
     * readPrize - creates a single Country object
     *
     * @param reader - JSON reader pointed at the input stream containing JSON
     * information
     * @return - Newly created Prize object
     * @throws IOException
     */
    private Prize readPrize(JsonReader reader) throws IOException {
        int year = 0;
        String category = null;
        List<InnerLaureate> laureates = null;
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
                    laureates = InnerLaureateReader(reader);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Prize(year, category, laureates);
    }

    /**
     * InnerLaureateReader - creates a list of InnerLaureate objects from the
     * JSON information. InnerLaureate are the nested objects stored inside the
     * prize JSON items
     *
     * @param reader - JSON reader pointed at the input stream containing the
     * JSON information
     * @return - list InnerLaureate objects
     * @throws IOException
     */
    private List<InnerLaureate> InnerLaureateReader(JsonReader reader) throws IOException {
        List<InnerLaureate> laureates = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            laureates.add(readLaureate(reader));
        }
        reader.endArray();
        return laureates;
    }

    /**
     * readLaureate - creates an InnerLaureate object from the JSON information.
     *
     * @param reader - JSON reader pointed at the input stream containing the
     * JSON information.
     * @return - new InnerLaureate object
     * @throws IOException
     */
    private InnerLaureate readLaureate(JsonReader reader) throws IOException {
        int id = 0;
        String firstName = null;
        String surName = null;
        String motivation = null;
        int share = 0;
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
                case "motivation":
                    motivation = reader.nextString();
                    break;
                case "share":
                    share = reader.nextInt();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        return new InnerLaureate(id, firstName, surName, motivation, share);
    }

    /**
     * toString - override of the default toString method.
     *
     * @return - List of Prize objects in an organized format
     */
    @Override
    public String toString() {
        StringBuilder prizes = new StringBuilder();
        allPrizes.forEach((p) -> {
            prizes.append(p);
        });
        return prizes.toString();
    }

}
