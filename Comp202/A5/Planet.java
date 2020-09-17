
import java.util.ArrayList;
import java.util.Random;
public class Planet{
  //attributes
  private String name;
  private double chance;
  private double damagePossible;
  
  //constructor
  public Planet(String name, double chance,double damagePossible){
    this.name=name;
    this.chance=chance;
    this.damagePossible=damagePossible;
    //using if statement to throw IllegalArugmentException when if the chance of artifact success is less than zero 
    //or greater than one, or if the damage is less than 0.
    if(this.chance<0 || this.chance>1||this.damagePossible<0){
      throw new IllegalArgumentException("the chance of artifact success can't be less than 0 or greater than 1 and damage cannot be less than 0"); 
    }                                   
    
  }
  //getter 
  public String getName(){
    return this.name;
  }
  
  //toString method
  //the attribute chance is the double between 0.0 and 1.0, but when output, I want to the success chance to be 
  //a percentage from 0 to 100, so I multiply this.chance by 100 to get the percentage of chance
  public String toString(){
    return "Name: "+this.name+" Chance: "+100*this.chance+"% Possible Damage: "+this.damagePossible;
  }
  
  //findPlanet method
  //because this planet takes a String and an ArrayList of Planets as input and search the ArrayList to find the planet
  //with a name matching the String, there is no difference when this method was called on different object, as this 
  //method can find its value by its input. So, I can use static method.
  public static int findPlanet(String x,ArrayList<Planet> planets){
    for(int i=0;i<planets.size();i++){
      if(planets.get(i).name.equalsIgnoreCase(x)){
        return i;
      }
    }
    return -1;
  }
  
  //searchForArtifact
  //searchForArtifact method which returns true or false whether an artifact was found.
  public boolean searchForArtifact(){
    //using random object to generated a number from 0 to 1
    Random randomGenerator=new Random();
    double random=randomGenerator.nextDouble();
    
    //Return true if the random number is below the chance to find an artifact, otherwise false. 
    if(random<this.chance){
      return true;
    }else{
      return false;
    }
  }
  //getDamageTaken
  //returns the amount of damage taken by a spaceship when searching for an artifact
  public double getDamageTaken(){
    //Generate a random number from 0 to 1
    Random randomGenerator=new Random();
    double random=randomGenerator.nextDouble();
    //multiply that number by the possible damage
    double damage=this.damagePossible*random;
    return damage;
  }
  
  
  
  
  
}
  
    
  
  
  