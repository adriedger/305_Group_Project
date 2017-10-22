/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package information_exploration;

/**
 *
 * @author adriedger
 */
public class Laureate {
    private Map<String, String> entry = new hashMap
    
    private String year;
    private String prize;
    private String name;
    private String longname;
    private String gender;
    private String photo;
    private String country;
    private String affiliation;
    private String birthyear;
    private String deathyear;
    private String biography;
    private String lecture;
    
    public Laureate(){
        this.year = "";
        this.prize = "";
        this.name = "";
        this.longname = "";
        this.gender = "";
        this.photo = "";
        this.country = "";
        this.affiliation = "";
        this.birthyear = "";
        this.deathyear = "";
        this.biography = "";
        this.lecture = "";
    }
    
    public void setEntry(String key, String data){
        
    }
    
    
    public void output(){
        System.out.println(year);
        System.out.println(prize);
        System.out.println(name);
        System.out.println(longname);
        System.out.println(gender);
        System.out.println(photo);
        System.out.println(country);
        System.out.println(affiliation);
        System.out.println(birthyear);
        System.out.println(deathyear);
        System.out.println(biography);
        System.out.println(lecture);        
    }    
}
