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
public final class LaureateReader {

    private final List<Laureate> allLaureates;

    public LaureateReader(InputStream in) throws IOException {
        this.allLaureates = readJsonStream(in);
    }

    public List<Laureate> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return readLaureateArray(reader);
        }
    }

    public List<Laureate> readLaureateArray(JsonReader reader) throws IOException {
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

    public Laureate readLaureate(JsonReader reader) throws IOException {
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
                    //InnerPrizeReader temp = new InnerPrizeReader(reader);
                    //temp.prizes = prizes;
                    //prizes = readPrizeArray(reader);
                //Need a nested function to make prize list
                /*
                Function should be like readPrizeArray.
                Likely need to remove the beginObject and skipValue.
                Next readArray should work just like the javadoc example
                 */
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Laureate(id,firstName,surName,born,died,bornCountry,
                bornCountryCode,bornCity,diedCountry,diedCountryCode,
                diedCity,gender,prizes);
                
    }

    public void displayLaureates() {
        allLaureates.forEach((l) -> {
            System.out.println("ID: " + l.id);
            System.out.println("FirstName: " + l.firstName);
            System.out.println("SurName: " + l.surName);
            System.out.println("Born: " + l.born);
            System.out.println("Died: " + l.died);
            System.out.println("BornCountry: " + l.bornCountry);
            System.out.println("BornCountryCode: " + l.bornCountryCode);
            System.out.println("BornCity: " + l.bornCity);
            System.out.println("DiedCountry: " + l.diedCountry);
            System.out.println("DiedCountryCode: " + l.diedCountryCode);
            System.out.println("DiedCity: " + l.diedCity);
            System.out.println("Gender: " + l.gender);
            System.out.println();
        });
    }
}
