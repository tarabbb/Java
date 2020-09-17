
import java.util.Arrays;
import java.util.Random;
public class Deck{
  //attribute
  private Card[] cards;
  
  //constructor
  //A constructor that takes nothing as input and initializes the attribute with a Card[] of size 52.
  public Deck(){
   cards=new Card[52];
    String[] suitsValues={"hearts","spades","clubs","diamonds"};
    int index=0;
    //because a Card object contains both suitsValues and values, I need to use 2 for loop to populate each element
    //I have to use int index to help me to put every new object that I created into the corresponding place in the array
    for(int j=0;j<suitsValues.length;j++){
      for(int i=1;i<=13;i++){
        cards[index]=new Card(i,suitsValues[j]);
        index++;
      }
    }
  }
  
  //shuffle() which takes no input and returns no value. This method should shuffle the array of Cards of this Deck. 
  public void shuffle(){
    //create an object of type Random
    int seed=123;
    Random otherGenerator= new Random(seed);
    //create a loop that iterates 1000 times
    for(int i=0;i<1000;i++){
      //generate 2 random integers between 0 and 51 (both included)
      //because nextInt(int n)method returns the int value that between 0(inclusive) and specified value(exclusive)
      //I have to use n=52 to create the value that between 0(inclusive) and 51(inclusive);
      int number1=otherGenerator.nextInt(52);
      int number2=otherGenerator.nextInt(52);
      //swap the values at those positions in the array
      Card temp=cards[number1];
      cards[number1]=cards[number2];
      cards[number2]=temp;
    }
  }
  
  public Card[] dealHand(int n,int playerNumber){
    //If there are not enough cards to deal,throw an IllegalArgumentException
    //52/n is integer division,and tell us for at most what number of player can get enough cards
    if(52/n<playerNumber){
      throw new IllegalArgumentException("Sorry,there are not enough cards in the deck");
    }
    else{
      //return an array of Cards representing the hand dealt to the player
      Card[] handDealt=new Card[n];
      for(int i=0;i<n;i++){
        //(playerNumber-1)*n+i give me the index in the array of cards that this player get as his ith handDealt 
        handDealt[i]=cards[(playerNumber-1)*n+i];
       
      }
      return handDealt;
    }
  }

   
      
    
  
  
  
  
}
      
      
        