package Project0;

import java.util.Scanner;
 
public class Diamond
 
 
 
 {
 
   public static void main(String[] args)
   
   {
   
   Scanner keyboard = new Scanner(System.in);
   int userData;
   int diamondSplit;
   int diamondRow;
   
   
   
   System.out.println("How many numbers lines do you want your diamond?");
   userData = keyboard.nextInt();
   
   int size = userData;
   int numCol=1;
   int numCol2;
   int start=0;
   int spaces= size/2;
   
   if (size == 5 || size == 7 || size == 9)
    {
     size = size/2+1;
    }
 
   
   
   
   
   for(int i = 1; i <=userData; i++)
   {
   
      for(int j=1; j<=spaces; j++)
      {
       System.out.print(" ");
      }
      if(i<size)
      {
       start=i;
       spaces = spaces -1;
      }
      else
      {
       start = (userData+1)-i;
       spaces = spaces +1;
      }
 
     
       for(int j = 1; j <= numCol; j++)
       {
        int middleCol = numCol/2+1;
        System.out.print(start);
        if(j<middleCol)
        {
         start--;
        }
        else
        {
         start ++;
        }
       }
       
       System.out.println();
       if(i<size)
       {
        numCol = numCol +2;
       }
       else
       {
        numCol = numCol -2;
       }
      }
   }
 
 
}