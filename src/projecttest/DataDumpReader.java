/*
 * CMPT 305 - Main Project
 * Stephen Doyle - 1718939
 * 
 * Program to implement a user interface for the nobel prize database.
 */
package projecttest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * CountryReader - parses an input stream in JSON format and creates a list of
 * country objects from the data. Contains a list of Country objects
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public final class DataDumpReader {

    private final List<DataDump> allDataDumps;

    /**
     * CountryReader - class constructor to create new CountryReader object.
     * Constructor uses nested functions to complete the data parse and create
     * the list when it is called
     *
     * @param in - input stream containing the data dump file
     * @throws IOException
     */
    public DataDumpReader(InputStream in) throws IOException {
        this.allDataDumps = readData(in);
    }
    
    /**
     * readData - creates an InputStreamReader for the data dump file
     * @param in - input stream containing the data dump file
     * @return - a call to the allDumps function
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    private List<DataDump> readData(InputStream in) throws UnsupportedEncodingException, IOException {
        InputStreamReader reader = new InputStreamReader(in);
        return allDumps(reader);
    }

    /**
     * allDumps - creates a list of DataDump
     * @param reader - InputStreamReader pointed at the data dump file
     * @return list of DataDump objects
     * @throws IOException 
     */
    private List<DataDump> allDumps(InputStreamReader reader) throws IOException {
        List<DataDump> allDumps = new ArrayList<>();
        BufferedReader buff = new BufferedReader(reader);
        Stack<String> toCheck = new Stack<>();
        String parseLine;

        while ((parseLine = buff.readLine()) != null) {
            if (parseLine.equals("")) {
                allDumps.add(oneDump(toCheck));
            } else {
                toCheck.push(parseLine);
            }
        }
        return allDumps;
    }

    /**
     * oneDump - creates a data dump object.
     * Makes a data dump object from the data that was read from the
     * input reader.
     * 
     * @param allDumps - stack containing strings of that make up the key/values
     * of the data dump object
     * @return - new DataDump object
     */
    private DataDump oneDump(Stack<String> allDumps) {
        String year = null;
        String prize = null;
        String name = null;
        String longname = null;
        String gender = null;
        String photo = null;
        String country = null;
        String affiliation = null;
        String birthyear = null;
        String biography = null;
        String lecture = null;

        while (!allDumps.isEmpty()) {
            String temp = allDumps.pop();
            String keyVals[] = temp.split(":");
            switch (keyVals[0]) {
                case "year":
                    year = keyVals[1];
                    break;
                case "prize":
                    prize = keyVals[1];
                    break;
                case "name":
                    name = keyVals[1];
                    break;
                case "longname":
                    longname = keyVals[1];
                    break;
                case "gender":
                    gender = keyVals[1];
                    break;
                case "photo":
                    StringBuilder photoVal = new StringBuilder();
                    for (int i = 1; i < keyVals.length; i++) {
                        photoVal.append(keyVals[i]);
                    }
                    photo = photoVal.toString();
                    break;
                case "country":
                    country = keyVals[1];
                    break;
                case "affiliation":
                    affiliation = keyVals[1];
                    break;
                case "birthyear":
                    birthyear = keyVals[1];
                    break;
                case "biography":
                    StringBuilder bioVal = new StringBuilder();
                    for (int i = 1; i < keyVals.length; i++) {
                        bioVal.append(keyVals[i]);
                    }
                    biography = bioVal.toString();
                case "lecture":
                    StringBuilder lectureVal = new StringBuilder();
                    for (int i = 1; i < keyVals.length; i++) {
                        lectureVal.append(keyVals[i]);
                    }
                    lecture = lectureVal.toString();
                    break;
                default:
                    break;
            }
        }
        return new DataDump(year, prize, name, longname, gender,
                photo, country, affiliation, birthyear,
                biography, lecture);
    }

    /**
     * toString - override of the default toString method.
     *
     * @return - List of DataDump objects in an organized format
     */
    @Override
    public String toString() {
        StringBuilder dumps = new StringBuilder();
        allDataDumps.forEach((d) -> {
            dumps.append(d);
        });
        dumps.append("\n");
        return dumps.toString();
    }
}
