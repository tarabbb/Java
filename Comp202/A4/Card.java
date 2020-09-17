

public class Card{
  //attributes
  private String suit;
  private int value;
  
  //constructor
  public Card(int value,String suit){
    //the input are valid if they create a card of value between 1 and 13(both included) and of suit equal
    //to either hearts,spades,clubs,or diamonds. so use if statement to distinguish if it is valid.
    //because ignoring capitalization, using equalsIgnoreCase method
    if(value>=1 && value<=13 && (suit.equalsIgnoreCase("hearts")||suit.equalsIgnoreCase("spades")||suit.equalsIgnoreCase("clubs")||suit.equalsIgnoreCase("diamonds")))
    {
      this.value=value;
      this.suit=suit;
    }
    else{
      //If the input are invalid, then the constructor should throw an IllegalArgumentException
      throw new IllegalArgumentException("the value or suit is invalid,no card of such type can be created");
    }
  }
  
  //getter and setter
  //getSuit() which returns the suit of the card.
  public String getSuit(){
    return this.suit;
  }
  
  //getValue() which returns the value of the card.
  public int getValue(){
    return this.value;
  }
  
  //other method
  //print() which takes nothing as input and prints the content of the card.
  public void print(){
    System.out.print(this.value+" of "+this.suit+", ");
  }
}
   
  