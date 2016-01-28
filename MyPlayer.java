public class MyPlayer extends Player
{
	//MyPlayer is the player template
	public int[] column = new int[10];
	public int[] row = new int[10];
	public Chip[] gameBoard = new Chip[100];


	public int move(Chip[] chip)
	{

		gameBoard = chip;
		countCols();
		countRows();



		//right now this AI only has one move.  It takes Chip 8.
		return(8);
	}




	public void countCols()
	{


	}//countCols()

	public void countRows()
	{


	}//countRows

















}//class

