package problems.maze;

import java.util.Objects;

import problems.maze.MazeState;
import search.State;
import utils.Position;

/**
 *  Represents an state, which corresponds with a position (cell) of the maze.
 */
public class MazeState extends State implements Cloneable{
	
	/** An state is includes a position given by the coordinates (x,y) */
	public Position position;
	public int numCheese;
	public int numCat;
	public int posX;
	public int posY;
	
	
	//(X,Y,Q,G)
	public MazeState(int x, int y, int numCheese, int numCat) {
		this.numCat=numCat;
		this.numCheese=numCheese;
		this.posX=x;
		this.posY=y;
		this.position=new Position(x,y);

	}
	public MazeState(Position pos, int numCheese, int numCat) {
		this.numCat=numCat;
		this.numCheese=numCheese;
		this.posX=pos.x;
		this.posY=pos.y;
		this.position=pos;

	}
	//
	public MazeState(int x, int y) {
		super();
		this.posX=x;
		this.posY=y;
		this.position=new Position(x,y);
	
	}
	
	
	//

	
	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
	
	
	
	
	
	
	@Override
	public boolean equals(Object anotherState) {
		// TODO Auto-generated method stub //comparamos si el objeto pasado como parametro es un objeto de tipo State.
		
		if (!(anotherState instanceof MazeState)){
			System.out.println("Estas comparando dos objetos de distinta clase.");
			return false;
		}
		
		//comparamos posiciones(x,y) para el objeto pasado como parámetro.
		MazeState m = (MazeState) anotherState;
		if(!(m.posX==this.posX) || !(m.posY==this.posY) || !(m.numCat==this.numCat) || !(m.numCheese==this.numCheese)) {
			return false;
		}
		
		return true;	
	}
	
	
	

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub //comparar objetos de una forma más rápida en estructuras Hash.
		return Objects.hash(this.posX,this.posY,this.numCheese,this.numCat);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub //imprimir el Estado:(posX->... ,posY->... ,quesos->... ,gatos->... ).
		
		return "Estado: (posX-> " + this.posX + ", posY->"+this.posY+" ,quesos->"+this.numCheese+", gatos->"+this.numCat+")";
		
	}
}
