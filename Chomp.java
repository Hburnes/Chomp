// Chomp Applet Version 2
// December 2, 2010   KJC
// Cleaned up variable names and reformatted the code.

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Chomp extends Applet implements ActionListener
{
   public int test=0;
   public Rectangle mouserec;
   public Board[] allboards;
   public Board[] losers;
   public Board[] winners;
   public int[] reduce;
   public int counter;
   
   public int losersCounter;
   public int winnersCounter;
   
   public int secplace;
   public int firplace;

   public int cellWidth= 25;
   public int rows=10;
   public int columns=10;
   public boolean position=false;
   public int xpos=0,ypos=0;
   public Chip[] piece =new Chip[101];
   public int chipNum=0;
   public boolean youLose=false;
   public boolean legal=false;
   public Button start,suicide,random,notsmart,play;
   public Panel p=new Panel();
   public Player regPlayer;
   public RandomPlayer randomDude;
   public NotSmartPlayer dumbPlayer;
   public SmartPlayer smartDude;
   public MyPlayer myChomp;
   public String loser;
 
   public int[] chipN= new int[10];
   public int[] boardChips = new int[10];
   
   public int low = 2;
      
   public void init()
   {
      smartDude=new SmartPlayer();
      dumbPlayer=new NotSmartPlayer();
      randomDude=new RandomPlayer();
      regPlayer=new Player();
      myChomp = new MyPlayer();
      
   	//Set up buttons
      start= new Button("NewGame");
      start.addActionListener(this);
      p.add(start);
      
      play= new Button("Play");
      play.addActionListener(this);
      p.add(play);
      
      random= new Button("random");
      random.addActionListener(this);
      p.add(random);
   
      notsmart= new Button("notsmart");
      notsmart.addActionListener(this);
      p.add(notsmart);
   
      suicide= new Button("myChomp");
      suicide.addActionListener(this);
      p.add(suicide);
      setLayout(new BorderLayout());
      add("South",p);
         
      allboards = new Board[10000];
      makeBoards(); 
       
   	//create the chips
      for( int i = 0; i < columns; i++ )
      {
         for( int z = 0; z < rows; z++ )
         {
            xpos=i*25;
            ypos=z*25;
            piece[chipNum]= new Chip(xpos+18,ypos+18,chipNum);
            if (chipNum<100)
            {chipNum++;}
         }
      
      }
   
   }//init
   
   public void makeBoards() {
      
      for(int a=1; a<=3; a++) {
         for(int b=0; b<=a; b++) {
            for(int c=0; c<=b; c++) {
            
               allboards[counter] = new Board(a,b,c);
            
               if(a==1 && b==0 && c==0) {
                  allboards[counter].winner= true;
                  losers[0]=new Board(a,b,c);
               }
               else{
               
                  if(reduce(allboards[counter])==false){
                  
                     System.out.println("Adding to losers arrays");
                     allboards[counter].winner=false;
                     losers[losersCounter]= new Board(allboards[counter].chips[0], allboards[counter].chips[1], allboards[counter].chips[2]);
                     losersCounter++;
                  //System.out.println("This is a looser: " + losers[losersCounter].chips[0] + losers[losersCounter].chips[1] + losers[losersCounter].chips[2]);
                  }
               }
               
               if(reduce(allboards[counter])==true) {
               
                  allboards[counter].winner=true;
                  winners[winnersCounter]= new Board(allboards[counter].chips[0], allboards[counter].chips[1], allboards[counter].chips[2]);winners[winnersCounter].winningmove=allboards[counter].winningmove;
                  winnersCounter++;
               }
               counter++;
            }
         }
      }
      Print();
   }
   
   public void Print(){
      
      System.out.println("");
      System.out.println("*********************************************************");
      System.out.println("WE ARE NOW PRINTING THE BOARDS PRESENT IN THE LOSER ARRAY");
    
      for(int x=0; x<losersCounter; x++){
      
         System.out.println(losers[x].chips[0]+""+losers[x].chips[1]+""+losers[x].chips[2]);
      }
    
      System.out.println("");
      System.out.println("*********************************************************");
      System.out.println("WE ARE NOW PRINTING THE BOARDS PRESENT IN THE WINNER ARRAY");
    
      for(int x=0; x<winnersCounter; x++){
         
         System.out.println(winners[x].chips[0]+""+winners[x].chips[1]+""+winners[x].chips[2] + "Winning chip: "+winners[x].winningmove);
      }
   
   }
   
   public boolean reduce(Board current) {
   
      //System.out.println("Reducing Board:"+current.chips[0]+","+current.chips[1]+","+current.chips[2]);
      for(int d=current.chips[2]-1; d>=0; d--) {
         int[] place = {current.chips[0], current.chips[1], d};
         //System.out.println(current.chips[0]+""+current.chips[1]+""+d);
         for(int x=0; x<losersCounter; x++){ 
            System.out.println("Checking board: "+current.chips[0]+""+current.chips[1]+""+d+" against loser: "+x);
            if(isLoser(place, losers[x].chips)){
               
               current.winningmove=findMove(current.chips, losers[x].chips);
               System.out.println("Winning move for: " + current.chips[0] + current.chips[1] + current.chips[2] + "is " + current.winningmove);
               return(true);
            }
         }
      }
      secplace = current.chips[2]; 
      for(int e=current.chips[1]-1; e>=0; e--) {
         if(secplace > e){
            secplace = e;
         }
         int[] place = {current.chips[0], e, secplace};
         System.out.println(current.chips[0]+""+e+""+secplace);
         for(int x=0; x<losersCounter; x++){
            System.out.println("Checking board: "+current.chips[0]+""+e+""+secplace+" against loser: "+x);
            if(isLoser(place, losers[x].chips)){
            
               current.winningmove=findMove(current.chips, losers[x].chips);
               System.out.println("Winning move for: " + current.chips[0] + current.chips[1] + current.chips[2] + "is " + current.winningmove);
               return(true);
            }
         }
      }
      secplace = current.chips[2];
      firplace = current.chips[1];
      
      for(int f=current.chips[0]-1; f>=1; f--) {
         if(firplace>f){
            firplace--;
         }
         if(secplace>firplace){
            secplace--;
         }
         int[] place = {f, firplace, secplace};
         System.out.println(f+""+firplace+""+secplace);
         
         for(int x=0; x<losersCounter; x++){
            System.out.println("Checking board: "+f+""+firplace+""+secplace+" against loser: "+x);
            if(isLoser(place, losers[x].chips)){
            
               current.winningmove=findMove(current.chips, losers[x].chips);
               System.out.println("Winning move for: " + current.chips[0] + current.chips[1] + current.chips[2] + "is " + current.winningmove);
               return(true);
            }
         }
      }
      return(false);
   }
   
   public boolean isLoser(int[] p, int[] l) {
      for(int x=0; x<3; x++)  {
      //System.out.println("P"+p[x]+"L"+l[x]);
         if(p[x] != l[x]) {
            return(false);
         }
      }
      return(true);
   }
   
   public int findMove(int[] p, int[] l) {  
      for(int x=0; x<3; x++) {
         if(p[x] != l[x]) {
            return(10*x+9-l[x]);
         }
      }
      return(18);
   }
   //mouse methods
   public boolean mouseDown(Event evt, int x, int y)
   {
      mouserec= new Rectangle(x,y,2,2);
      for(int z=0;z<100;z++)
      {
         if (mouserec.intersects(piece[z].myRect))
         {
            move(z);
            System.out.println("Chip  "+z+"  taken");
            repaint();
         }
      }
      return(true);
   }

   public void actionPerformed(ActionEvent e){
   
      if(e.getActionCommand().equals("Play"))
      {
         System.out.println("play");
      
         while(youLose==false)
         {
            int num;
            loser="player1";
            num= smartDude.move(piece);
            move(num);
         
            if(youLose==false)
            {
               loser="player2";
               num=smartDude.move(piece);
               move(num);
            }
         }//while
      }//if
   
      if(e.getActionCommand().equals("NewGame"))
      {
         youLose=false;
         for(int z=0;z<100;z++)
         {
            piece[z].isAlive=true;
         }
      
      	//play(getCodeBase(), "audio/yahoo2.au");
         repaint();
      }//if
   
      if(e.getActionCommand().equals("myChomp"))
      {
         int num;
         num=myChomp.move(piece);
         move(num);
         repaint();
      }//if
   
      if(e.getActionCommand().equals("notsmart"))
      {
         int num;
         //num=dumbPlayer.move(piece);
        // move(num);
         repaint();
      }//if
   
      if(e.getActionCommand().equals("random"))
      {
         int num;
         num=randomDude.move(piece);
         move(num);
         repaint();
      }//if
   
   }//actionPerformed

   public void move(int z)
   {
   	//play(getCodeBase(), "audio/return.au");
      notLegal(piece[z],z);
      for(int q=0;q<100;q++)
      {
         if ((piece[z].xpos<=piece[q].xpos)&&(piece[z].ypos>=piece[q].ypos))
            piece[q].isAlive=false;
      }
   
      if (youLose==true)
      {System.out.println(loser+"  is the loser");}
   }//move

   public void notLegal(Chip chip, int chipNum)
   {
      if (chip.isAlive==false)
      {
         play(getCodeBase(), "audio/return.au");
         youLose=true;
      	//repaint();
      }
   
      if (chipNum==9)
      {
         youLose=true;
      }
   }


   public void update(Graphics g) { paint(g); }

   public void paint( Graphics g )  // was boardPaint
   {
   	//System.out.println("paint");
      if (youLose)
      {	g.setColor(Color.black);
         g.fillRect(0,0,600,600);
         g.setColor(Color.red);
         g.drawString("YOU LOSE ",100,100);
      }
   
      if (youLose==false)
      {
         chipNum=0;
      	// paint the framework
         g.setColor( Color.lightGray );
         g.fillRect( 15, 15, cellWidth*rows, cellWidth*rows );
         g.setColor( Color.black );
         g.drawRect( 10, 10, cellWidth*rows + 10, cellWidth*rows + 10 );
      
         for( int c = 0; c < rows; c++ )
         {
            for( int r = 0; r < rows; r++ )
            {
               g.drawRect( 15 + cellWidth*c, 15 + cellWidth*r, cellWidth, cellWidth );
            }
         }
      
         for( int i = 0; i < 100; i++ )
         {
            g.setColor( Color.red );
         
            if (piece[i].isAlive)
            {
               g.fillOval( piece[i].xpos, piece[i].ypos, cellWidth - 6,  cellWidth - 6 );
               g.setColor( Color.blue );
               g.drawOval( piece[i].xpos, piece[i].ypos, cellWidth - 6,  cellWidth - 6 );
               g.setColor( Color.red );
            
            }//if
         
         }//for
      
      	//draw the poison chip in a different color.
         g.setColor( Color.blue );
         g.fillOval( piece[9].xpos, piece[9].ypos, cellWidth - 6,  cellWidth - 6 );
         g.drawOval( piece[9].xpos, piece[9].ypos, cellWidth - 6,  cellWidth - 6 );
      }//close for (youlose==fales)
   }//paint()
}	// close Chomp


