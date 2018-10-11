package problems.maze;

import problems.maze.MazeState;
import search.State;
import utils.Position;

/**
 *  Represents an state, which corresponds with a position (cell) of the maze.
 */
public class MazeState extends State implements Cloneable{
	
	/** An state is includes a position given by the coordinates (x,y) */
	public Position position;
	public int numCheese=0;
	public int numCat=0;
	public int posX=position.x;
	public int posY=position.y;
	
	
	//
	
	
	
	public MazeState(int x, int y, int numCheese, int numCat) {
		this.numCat=numCat;
		this.numCheese=numCheese;
		this.posX=x;
		this.posY=y;
	}
	
	
	public MazeState(Position pos) {
		// TODO Auto-generated constructor stub
		this.position=pos;
	}
	//
	
	
	
	@Override
	public boolean equals(Object anotherState) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
