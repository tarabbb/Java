

public class Game{
  public static void main(String[] args){
    //Create a Deck.
    Deck deck=new Deck();
    //Shuffle the Deck.
    //shuffle() is a non-static method in Deck class
    deck.shuffle();
    //Deal four hands of Cards (one per player). Each hand should contain 13 cards.
    //dealHand(int n,int playerNumber) is a non-static method in Deck class
    Card[] firstPlayer=deck.dealHand(13,1);
    Card[] secondPlayer=deck.dealHand(13,2);
    Card[] thirdPlayer=deck.dealHand(13,3);
    Card[] forthPlayer=deck.dealHand(13,4);
    //Compute how many points each hand is worth.
    //countPoints is a static method in BridgeUtilities class
    int firstPlayerPoints=BridgeUtilities.countPoints(firstPlayer);
    int secondPlayerPoints=BridgeUtilities.countPoints(secondPlayer);
    int thirdPlayerPoints=BridgeUtilities.countPoints(thirdPlayer);
    int forthPlayerPoints=BridgeUtilities.countPoints(forthPlayer);
    //For each player, display the cards inside their hand and how many points their hand is worth.
    //put ech players hands of cards into the array card[][]player and put each player's point in 
    //the array playerPoints, so that I can then use the for loop to show each player's hands of cards
    //and their points
    Card[][] playersDealHand={firstPlayer,secondPlayer,thirdPlayer,forthPlayer};
    int []playerPoints={firstPlayerPoints,secondPlayerPoints,thirdPlayerPoints,forthPlayerPoints};
    //using i+1 because i is begin with 0 but player number begin with 1
    for(int i=0;i<4;i++){
      System.out.println("Player "+(i+1)+" was dealt the following hand of cards: ");
      //display every hands of cards for each player using print() method in Card class.
      for(int j=0;j<13;j++){
        playersDealHand[i][j].print();
      }
      System.out.println();
      System.out.println("Their hand is worth "+playerPoints[i]+" points");
      System.out.println();
    }
        
    
  }
}