/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package information_exploration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author adriedger
 */
public class ReadNobel {
    
    private File text;
    private List<Laureate> laureates;
    
    public ReadNobel(){
        text = new File("nobel.txt");
        laureates = new ArrayList<>();
        
    }
    
    public List<Laureate> read(){
        try{
            Scanner input = new Scanner(text);
            Laureate current = new Laureate();
            while(input.hasNextLine()){
                String line = input.nextLine();
                if(line.equals("")){
                    laureates.add(current);
                    current = new Laureate();
//                    System.out.println("");                    
                }
                else{
                    String[] tokenizer = line.split(" ");
                    String key = tokenizer[0].replace(":", "");
                    String data = "";
                    for(int i = 1; i < tokenizer.length; i++){
                        data += tokenizer[i] + " ";
                    }
                    current.addEntry(key, data);
//                    System.out.println(key+": "+data);
                }
            }
                
        }
        catch(IOException ex){
            System.out.println ("Couldn't find 'nobel.txt'");
        }
        return laureates;
    }
}