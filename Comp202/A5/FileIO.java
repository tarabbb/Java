

import java.io.*;
import java.util.ArrayList;
public class FileIO{
  public static Spaceship loadSpaceship(String filename){
    //use a FileReader and a BufferedReader to open the file specified by filename.
    String[] read=new String[3];
    try{
      FileReader fr=new FileReader(filename);
      BufferedReader br=new BufferedReader(fr);
      String currentLine=br.readLine();
      int index=0;
      //Because,the format of the reading file is one value per line, I need to use while loop to read every line
      //and put each line in the array read to prepared for creating a new Spaceship
      while(currentLine!=null){
        read[index]=currentLine;
        currentLine=br.readLine();
        index++;
      }
      br.close();
      fr.close();
    }
    //have two catch blocks to catch both FileNotFoundException and IOException when reading from the file. 
    //To work properly, the catch block for the FileNotFoundException must be above the catch block for the IOException 
    catch(FileNotFoundException e){
      throw new IllegalArgumentException("Sorry, the file was not found");
    }
    catch(IOException e){
      throw new IllegalArgumentException("Sorry, there was another error");
    }
    
    //creating a new Spaceship by using the constructor of Spaceship;
    //Because what the computer read from the file is all in String and put them in the String array,
    //and the constructor of spaceship need input of type String, double, int. 
    //I need to turn the last two String in the array to be double and integer
    Spaceship spaceship=new Spaceship(read[0],Double.parseDouble(read[1]),Integer.parseInt(read[2]));
    return spaceship;
  }
  
  
  //This method takes a filename of planets to read, and returns an ArrayList of Planets.
  public static ArrayList<Planet> loadPlanets(String filename){
    ArrayList<Planet> planets=new ArrayList<Planet>();
    try{
      //Read the file indicated by the method parameter filename, by using a FileReader and 
      //a BufferedReader to open the file specified by filename.
      FileReader fr=new FileReader(filename);
      BufferedReader br=new BufferedReader(fr);
      String currentLine=br.readLine();
      
      while(currentLine!=null){
        //The planets are defined on each line, consisting of the planet name, the chance to find an artifact, 
        //and the possible amount of damage.I need to use the split method to put these three values in a array "temp"
        //in order to use the Planet constructor to creat a new Planet.
        String line=currentLine;
        String[] temp=line.split(" ");
        //the Planet constructor need the input as a String, double,double
        ////I need to turn the last two String in the array to be double 
        Planet newPlanet=new Planet(temp[0],Double.parseDouble(temp[1]),Double.parseDouble(temp[2]));
        //add this new created Planet into the arrayList
        planets.add(newPlanet);
        currentLine=br.readLine();
        
      }
      br.close();
      fr.close();
    }
    catch(FileNotFoundException e){
      throw new IllegalArgumentException("Sorry, the file was not found");
    }
    catch(IOException e){
      throw new IllegalArgumentException("Sorry, there was another error");
    }
    return planets;
  }
  
  //add throws IOException to the header of the saveSpaceship method in order to not catch the IOException 
  //that can occur when writing a file
  public static void saveSpaceship(Spaceship x,String fileName) throws IOException{
    //use FileWriter and BufferedWriter instances to write the spaceship’s information back to a file. 
    FileWriter fw=new FileWriter(fileName);
    BufferedWriter bw=new BufferedWriter(fw);
    //the format of a spaceship in a file should be :
    //• Name of the character 
    //• Maximum health 
    //• Number of wins so far in the space game
    bw.write(x.getName());
    //use bw.newLine() to write maxHelath in next line to match the format that is expected when a spaceship is loaded
    bw.newLine();
    String maxHealth=""+x.getMaxHealth();
    bw.write(maxHealth);
    bw.newLine();
    String numberOfWins=""+x.getNumberOfWins();
    bw.write(numberOfWins); 
    bw.close();
    fw.close();
  }
  
  
  
  
  
  
  
  
  
}