/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecttest;

/**
 * DataDump Class - holds an object from the provided DataDump file. Contains
 * year, prize, name, long name, gender, photo, country, affiliation, birth
 * year, biography, lecture.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class DataDump {

    String year;
    String prize;
    String name;
    String longname;
    String gender;
    String photo;
    String country;
    String affiliation;
    String birthyear;
    String biography;
    String lecture;

    /**
     * DataDump - class constructor creates new DataDump object
     *
     * @param year - year of the prize
     * @param prize - category of prize
     * @param name - name of laureate
     * @param longname - long name of laureate
     * @param gender - gender of laureate
     * @param photo - link to photo of laureate
     * @param country - country of laureate
     * @param affiliation - affiliation of prize
     * @param birthyear - birth year of laureate
     * @param biography - link to laureates bio
     * @param lecture - link to prize lecture
     */
    public DataDump(String year, String prize, String name, String longname,
            String gender, String photo, String country,
            String affiliation, String birthyear,
            String biography, String lecture) {
        this.year = year;
        this.prize = prize;
        this.name = name;
        this.longname = longname;
        this.gender = gender;
        this.photo = photo;
        this.country = country;
        this.affiliation = affiliation;
        this.birthyear = birthyear;
        this.biography = biography;
        this.lecture = lecture;
    }

    /**
     * toString - override of the default toString method.
     *
     * @return - DataDump object in an organized format
     */
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append("Year: ").append(year).append("\n");
        data.append("Prize: ").append(prize).append("\n");
        data.append("Name: ").append(name).append("\n");
        data.append("LongName: ").append(longname).append("\n");
        data.append("Gender: ").append(gender).append("\n");
        data.append("Photo: ").append(photo).append("\n");
        data.append("Coutry: ").append(affiliation).append("\n");
        data.append("BirthYear: ").append(birthyear).append("\n");
        data.append("Bio: ").append(biography).append("\n");
        data.append("Lec: ").append(lecture).append("\n");
        data.append("\n");

        return data.toString();
    }

}
