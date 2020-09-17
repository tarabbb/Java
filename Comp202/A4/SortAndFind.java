
import java.util.Random;
import java.util.Arrays;

public class SortAndFind{
  public static void main(String[] args){
    int[][]x=generateRandomMatrix(6,8);
    displayMatrix(x);
    System.out.println();
    sortMatrix(x);
    displayMatrix(x);
    System.out.println(Arrays.toString(findElement(x,14)));
    System.out.println(Arrays.toString(findElement(x,26)));
    System.out.println(Arrays.toString(findElement(x,5)));
    
  }
  //a
  public static int[][] generateRandomMatrix(int m, int n){
    int seed=123;
    Random Generator=new Random(seed);
    //create a 2-dimensional array
    int[][] randomMatrix=new int[m][n];
    //because it is the 2-dimensional array, I need 2 for loop to populate all the elements in it.
    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){
        //use the method I learned in class to create random integer for each elements
        //because I need to get the integer between 0(included) and 50(excluded), I need to use nextInt(int n)method
        //for each elements, i is their row and j is their column
        randomMatrix[i][j]=Generator.nextInt(50);
      }
    }
    return randomMatrix;
  }
  
  //b
  //because it only print the matrix, so the mehtod is void
  public static void displayMatrix(int[][] x){
    //because it is the 2-dimensional array, I need 2 for loop to display elements in it
    for(int i=0;i<x.length;i++){
      for(int j=0;j<x[i].length;j++){
        //use" "before[i][j] and character'\t' to concatenate the thing after it 
        System.out.print(" "+x[i][j]+'\t');
      }
      //to make the different subarray in different row 
      System.out.println();
    }
  }
  
  //c
  //because the method do not return any value, it is a void method
  public static void sortOneRow(int[] x){
    
    int swapTemp=0;
    //because each time after swaping two elements, the index of unsortBegin will updated by increasing 1
    //I can use the index of unsortBegin to create a for loop
    //At first, the unsortBegin is 0.
    for(int unsortBegin=0;unsortBegin<x.length;unsortBegin++){
      //find the smallest element in the unsorted part, so I can assume the smallest is the first element
      //of the unsorted part, then compare the value of the first element with the elements after it using 
      //the for loop and finally get the smallest value and index of the smallest element.
      int smallestPosition=unsortBegin;
      int smallest=x[unsortBegin];
      for(int i=unsortBegin+1;i<x.length;i++){
        if(smallest>x[i]){
          smallest=x[i];
          smallestPosition=i;
        }
      }
      //swap the element with the element in the initial position of the unsorted part of the array
      //create swapTemp to help to swap
      swapTemp=x[unsortBegin];
      x[unsortBegin]=x[smallestPosition];
      x[smallestPosition]=swapTemp;
    }
    //update the index indicating where the unsorted part of the array begin(unsortBegin++)
    
  }
  
  //d
  //because this method implement the algorithm described in part c to sort the specified column
  //the method we used is very similar to part c
  public static void sortOneColumn(int[][]x,int column){
    int swapTemp=0;
    //use the index of unsortBegin to create a for loop
    for(int unsortBegin=0;unsortBegin<x.length;unsortBegin++){
      //find the smallest element in the unsorted part, so I can assume the smallest is the first element
      //of the unsorted part, then compare the value of the first element with the elements after it using 
      //the for loop and finally get the smallest value and index of the smallest element.
      //Because it is the 2-dimensional array and the operation is in the same column,
      //I can use x[i][column]to indicate the element in that column
      int smallestPosition=unsortBegin;
      int smallest=x[unsortBegin][column];
      for(int i=unsortBegin+1;i<x.length;i++){
        if(smallest>x[i][column]){
          smallest=x[i][column];
          smallestPosition=i;
        }
      }
      //swap the element with the element in the initial position of the unsorted part of the array
      //create swapTemp to help to swap
      swapTemp=x[unsortBegin][column];
      x[unsortBegin][column]=x[smallestPosition][column];
      x[smallestPosition][column]=swapTemp;
    }
    
  }
  
  //e
  public static void sortMatrix(int[][]x){
    //first, sorting all the rows of matrix
    //using a for loop and method sortOneRow to sort every row
    for(int j=0;j<x.length;j++){
    sortOneRow(x[j]);
    }
    //then,sorting all the columns of matrix using for loop and method sortOneColumn
    for(int i=0;i<x[0].length;i++){
      sortOneColumn(x,i);
    }
    
  }
  
  //f
  public static int[] findElement(int[][]x,int n){
    //get the coordinate of the bottom left element of the matrix
    int row=x.length-1;
    int column=0;
    //create the matrix that need to be return(which is the coordinate of the element same as n)
    int[] findElement=new int[2];
    //because I don't know after how many times of running the loop I will get the coordinate
    //,I need to use while loop instead of for loop
    //only when column index of the element<columns of matrix(column<x[0].length) and row index of the
    //element >=0, the elements is in the matrix
    while(column<x[0].length && row>=0){
      //compare the element with the input
      //if they are equal, return its column index and row index
      if(x[row][column]==n){
        findElement[0]=row;
        findElement[1]=column;
        return findElement;
      }
      //if n is smaller than the element you are looking at, then move up in the matrix by one position.
      else if(n<x[row][column]){
        row=row-1;
      }
      //If n is greater than the element you are looking at, then move to the right in the matrix by one position.
      else{
        column=column+1;
      }
    }
    //if the computer do not find elements that equal to our input value in the while loop, it means the element
    //is not in the matrix, we can return(-1,-1)
    findElement[0]=-1;
    findElement[1]=-1;
    return findElement;
  }
    
      
    
    
    
  
  
  
}
        
        
  
  