
public class BridgeUtilities{
  //constructor
  //A private constructor that takes no inputs and does nothing.
  //By adding this constructor we make sure that no objects of type BridgeUtilities can be created from outside this class.
  private BridgeUtilities(){
    
  }
  //The method should return how many Cards in the array have the specified value.
  private static int countValue(Card[] cards,int value){
    int count=0;
    //using for loop to compare every card value in the array with the input value, and once equal,count++
    for(int i=0;i<cards.length;i++){
      //getValue() is a non-static method in class Card
      if(cards[i].getValue()==value){
        count++;
      }
    }
    return count;
  }
  
  //The method should return how many Cards in the array have the specified suit
  private static int countSuit(Card[] cards,String suit){
    int count=0;
    for(int i=0;i<cards.length;i++){
      //getSuit() is a non-static method in class Card and returns a String
      //use equalsIgnoreCase to ignore capitalization
      if(cards[i].getSuit().equalsIgnoreCase(suit)){
        count++;
      }
    }
    return count;
  }
  
  //the method returns the number of points that the hand is worth.
  public static int countPoints(Card[] cards){
    //evaluation high cards points acording to the schema
    int highCardPoints=0;
    int countValue1=countValue(cards,1);
    int countValue13=countValue(cards,13);
    int countValue12=countValue(cards,12);
    int countValue11=countValue(cards,11);
    highCardPoints=4*countValue1+3*countValue13+2*countValue12+1*countValue11;
    //evaluation distributional points
    int countSuitC=countSuit(cards,"clubs");
    int countSuitH=countSuit(cards,"hearts");
    int countSuitD=countSuit(cards,"diamonds");
    int countSuitS=countSuit(cards,"spades");
    int distributionalPoints=0;
    if(countSuitC>4){
      distributionalPoints=distributionalPoints+(countSuitC-4);
    }
    if(countSuitH>4){
      distributionalPoints=distributionalPoints+(countSuitH-4);
    }
    if(countSuitD>4){
      distributionalPoints=distributionalPoints+(countSuitD-4);
    }
    if(countSuitS>4){
      distributionalPoints=distributionalPoints+(countSuitS-4);
    }
    //total points
    int points=highCardPoints+distributionalPoints;
    return points;
  }
    
      
    
    
 
  
  
  
  
  
  
  
}