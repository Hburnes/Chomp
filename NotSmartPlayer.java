import java.util.ArrayList;

public class NotSmartPlayer extends Player
{

   public int[] chipN= new int[10];
   public int[] boardChips = new int[10];
   
   public int low = 1;
   
   ArrayList<int []> AllBoards = new ArrayList<int []>(); 
   
   public int total=0;

   /*public int move(Chip[] chip)
   {
      total=0;
      convCol(chip);
         
   }//move*/

   public void makeBoards(int[] ChipN, int low, int column) {
      
      for(int x=0; x<10; x++) {
      System.out.print(x + " : " + ChipN[x]);
      }
      
      System.out.println("");
      
      AllBoards.add(ChipN);
            
      for(int a=0; a<low; a++) {
         
         boardChips[column] = a; 
            
         if(boardChips[column]<low) {
            low=a;
         }
            
         if(column<9) {
            makeBoards(boardChips, low, column++);
         }
            
         else {
            makeBoards(boardChips, low++, 0);
         }
      }
   }
   

   public void convCol(Chip[] chip)
   {
      for(int x=0; x<10;x++)
      {
         chipN[x]=0;
         for(int z=0; z<10;z++)
         {
            if (chip[total].isAlive)
            {
               chipN[x]=chipN[x]+1;
            }
            total=total+1;
         }//for z
      }//for n
   }//convCol
}//class