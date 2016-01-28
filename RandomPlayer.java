public class RandomPlayer extends Player
{

	public int randomNum;
	public boolean found;

	public int move(Chip[] chip)
	{

		found = false;

		while(!found)
		{
			randomNum = (int)(100.0*Math.random());
			if (chip[randomNum].isAlive)
				return(randomNum);

		}

		return(9); //default move


	}

}

