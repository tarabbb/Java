

import java.util.ArrayList;
public class Spaceship{
  //attributes
  private String name;
  private double currentHealth;
  private double maxHealth;
  private int numberOfArtifacts;
  private int numberOfWins;
  private Planet currPlanet;
  private static ArrayList<Planet> planets;
  
  //constructor
  public Spaceship(String name, double maxHealth, int numberOfWins){
    this.name=name;
    this.maxHealth=maxHealth;
    this.numberOfWins=numberOfWins;
    this.currentHealth=maxHealth;
    
  }
  
  //getter
  public String getName(){
    return this.name;
  }
  
  public double getMaxHealth(){
    return this.maxHealth;
  }
  
  public int getNumberOfWins(){
    return this.numberOfWins;
  }
  
  public double getCurrentHealth(){
    return this.currentHealth;
  }
  
  public int getNumberOfArtifacts(){
    return this.numberOfArtifacts;
  }
  
  
  
  
  
  //toString method
  public String toString(){
    return this.name+" "+this.currentHealth+" "+this.numberOfArtifacts;
  }
  
  //setPlanets method
  //Because the ArrayList<Planet> planet is a static attribute, the method can also be static
  public static void setPlanets(ArrayList<Planet> planet){
    //ArrayList is a mutable reference type, so I should make a copy of the input ArrayList when 
    //initializing the attribute. 
    ArrayList<Planet> copy=new ArrayList<Planet>();
    for(int i=0;i<planet.size();i++){
      copy.add(planet.get(i));
    }
    planets=copy;
    //print out the details of every planet using toString method(the println will automatically using toString method)
    for(int j=0;j<planets.size();j++){
      System.out.println(planets.get(j));
    }
  }
  //moveTo method
  //This method will move the spaceship to a different planet.
  public void moveTo(String planetName){
    //because findPlanet is static method, call it using its class name Planet
    int index=Planet.findPlanet(planetName,planets);
    
    //If the planet wasn’t found(index==-1)
    if(index==-1){
      System.out.println("The spaceship "+this.name+" tried to move to "+planetName+" ,but that planet isn't in the solar system!");
    }
    else{
      //if the planet was found
      currPlanet=planets.get(index);
      
      System.out.println("The spaceship "+this.name+" moved to "+planetName);
    }
  }
  
  //moveIn method
  //The method will move the spaceship closer into the solar system (closer to the sun).
  public void moveIn(){
    //because findPlanet is static method, call it using its class name Planet
    //find the index of the current planet by calling the findPlanet method.
    int index=Planet.findPlanet(currPlanet.getName(),planets);
    if(index==0){
      //If the spaceship is already at the first planet in the planet list
      System.out.println("The spaceship "+this.name+" couldn't move in. No planet is closer in");
    }else{
      //move closer into the solar system means the index-1 in the ArrayList of planets
      Planet moveToPlanet=planets.get(index-1);
      moveTo(moveToPlanet.getName());
    }
  }
  //moveOut method
  //opposite of the moveIn method.
  public void moveOut(){
    int index=Planet.findPlanet(currPlanet.getName(),planets);
    if(index==planets.size()-1){
      ////If the spaceship is already at the last planet(planets.size()-1) in the planet list
      System.out.println("The spaceship "+this.name+" couldn't move out. No planet is farther out");
    }else{
      ////move farther out of the solar system means the index+1 in the ArrayList of planets
      Planet moveToPlanet=planets.get(index+1);
      moveTo(moveToPlanet.getName());
    }
  }
  
  //increaseWins method
  //increase the number of wins by the character by one
  public void increaseWins(){
    this.numberOfWins=this.numberOfWins+1;
  }
  
  public void doSearch(){
    //Call the searchForArtifact method on the current planet and store the value
    boolean possible=currPlanet.searchForArtifact();
    if(possible==true){
      System.out.println("the spaceship "+this.name+" found an artifact!");
      numberOfArtifacts=numberOfArtifacts+1;
      
    }else{
      System.out.println("the spaceship "+ this.name+" did not find an artifact");
    }
    //Call the getDamageTaken method on the current planet to find out how much damage the spaceship took while 
    //performing the search
    double damageTaken=currPlanet.getDamageTaken();
    //Print a message saying that the spaceship took that much damage
    String damageStr = String.format("%1$.2f", damageTaken);
    System.out.println("The spaceship "+this.name+" took "+damageStr+" damage while searching for an artifact on "+currPlanet.getName());
    //Subtract the damage from the current health of the spaceship
    currentHealth=currentHealth-damageTaken;
    String currHealth=String.format("%1$.2f", currentHealth);
    System.out.println("Name: "+this.name+" Current Health: "+currHealth+" Artifacts: "+this.numberOfArtifacts);
    //If the current health is below zero, print a message that the spaceship explodes
    if(currentHealth<0){
      System.out.println("The spaceship "+this.name+" explodes");
    }
  }
  
  
  
  
  
}
  
  
  