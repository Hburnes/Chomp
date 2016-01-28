import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Board
{

	 public int winningmove;
    public boolean winner;
    
	 public int[] chips = new int[3];

	 public Board(int a, int b, int c)
	 {
    
    
    chips[0]=a;
    chips[1]=b;
    chips[2]=c;  
    
	 }

 }