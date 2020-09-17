

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
public class SpaceGame{
  //attributes
  private Scanner read;
  private Spaceship player;
  private static final int NUM_ARTIFACTS_WIN = 5;
  private Spaceship enemy;
  //constructor
  public SpaceGame(String fileName){
    //Initialize the Scanner attribute to take input from the user
    this.read=new Scanner(System.in);
    System.out.println("Welcome to the SpaceGame!");
    System.out.println("Loaded solar system from "+fileName+" :");
    //Initialize the player Spaceship using loadSpaceship method in FileIO class
    //because loadSpaceship method is static, simply call it with its class name
    player=FileIO.loadSpaceship("player.txt");
    //Initialize the enemy spaceship attribute in the SpaceGame constructor
    enemy=FileIO.loadSpaceship("enemy.txt");
    //Use the method loadPlanets method from the FileIO class to load a file containing planets
    ArrayList<Planet> planets=FileIO.loadPlanets(fileName);
    //Use the setPlanets method from the Spaceship class to set the planets for all Spaceships
    Spaceship.setPlanets(planets);
    //Move the player to the first Planet in the list (index 0) by using the moveTo method
    player.moveTo(planets.get(0).getName());
    //Move the enemy to the last Planet in the list (index planets.size()-1) by using the moveTo method
    enemy.moveTo(planets.get(planets.size()-1).getName());
    System.out.println();
    //informs the player how many artifacts they must find
    System.out.println("You must find "+NUM_ARTIFACTS_WIN+" artifacts to win");
  }
  
  //checkForDestroyed method
  //This method returns 1 if the player’s health is equal to or below zero. 
  //return 2 enemy’s current health is below zero
  //Return 0 otherwise
  private int checkForDestroyed(){
    if(player.getCurrentHealth()<=0){
      return 1;
    }
    if(enemy.getCurrentHealth()<0){
      return 2;
    }
    return 0;
  }
  
  //checkForWin method
  //This method returns 1 if the player’s number of artifacts found is equal to or greater than NUM ARTIFACTS WIN 
  //return 2 if the enemy found enough artifacts.
  //Return 0 otherwise
  private int checkForWin(){
    if(this.player.getNumberOfArtifacts()>=NUM_ARTIFACTS_WIN){
      return 1;
    }
    
    if(this.enemy.getNumberOfArtifacts()>=NUM_ARTIFACTS_WIN){
      return 2;
    }
    
    return 0;
  }
  
  public void playGame(){
    //if the player and enemy is still alive then the checkForDestroyed and checkForWin method must both are 0
    while(checkForDestroyed()==0 && checkForWin()==0){
      System.out.println();
      //Ask the user for a command using the readLine method of the Scanner instance
      System.out.println("Enter a command:");
      String reading=read.nextLine();
      //using if statement to choose a command based on the choices, ignoring the case
      if(reading.equalsIgnoreCase("moveIn")){
        player.moveIn();
        
      }else if(reading.equalsIgnoreCase("moveOut")){
        player.moveOut();
      }else if((reading.equalsIgnoreCase("moveto"))){
        //Ask the user for a destination and get a line of input from the user by using scanner
        System.out.println("Enter the destination:");
        player.moveTo(read.nextLine());
      }else if((reading.equalsIgnoreCase("search"))){
        player.doSearch();
        
      }else {
        System.out.println("Command not recognized: "+ reading);
        
      }
      //using Random object to create the random artificial intelligence to control the enemy spaceship.
      Random randomGenerator=new Random();
      int random=randomGenerator.nextInt(3);
      if(random==0){
        enemy.doSearch();
      }else if(random==1){
        enemy.moveIn();
      }else if(random==2){
        enemy.moveOut();
      }
      
    }
    //print the appropriate message saying the result of the game
    //because getting out of the loop can because the player's spaceship destroy,the enemy's spaceship destroy,
    //the enemy spaceship collects enough artifacts and the player's spaceship collects enough artifacts
    //I need to use if statement to distinguish these 4 different situation and print the suitable message corresponding
    //to the different case
    //Once someone wins, using increaseWins() method to increase the record of winning
    if(checkForDestroyed()==1){
      System.out.println("Sorry, you lose! Your spaceship destroyed!");
      enemy.increaseWins();
    }else if(checkForWin()==2){
      System.out.println("Sorry, you lose! the enemy spaceship collects enough artifacts!");
      enemy.increaseWins();
    }else if(checkForDestroyed()==2){
      System.out.println("Congratulation! the enemy spaceship destroyed!");
      player.increaseWins();
    }  else{
      System.out.println("Congratulation!You've found enough artifacts");
      player.increaseWins();
    }
    
    //print how many wins each spaceship has. 
    System.out.println(player.getName()+" has won: "+player.getNumberOfWins()+" times");
    System.out.println(enemy.getName()+" has won: "+enemy.getNumberOfWins()+" times");
    //call the saveSpaceship method from the FileIO class, and pass the spaceship and filename as parameters.
    //catch the IOException when the saveSpaceship method is called
    try{
      FileIO.saveSpaceship(player,"player.txt");
      System.out.println("Successfully wrote to file:  player.txt");
    }catch(IOException e){
      System.out.println("Sorry,there is an error with the file player.txt");
    }
    try{
      FileIO.saveSpaceship(enemy,"enemy.txt");
      System.out.println("Successfully wrote to file:  enemy.txt");
    }catch(IOException e){
      System.out.println("Sorry,there is an error with the file enemy.txt");
    }
    
    
    
  }
}

          
               
               

      
               
               
              
        
      
        
    
    
  