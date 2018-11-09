package problems.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import problems.maze.MazeState;
import search.State;
import utils.Position;

/**
 * Represents an state, which corresponds with a position (cell) of the maze.
 */
public class MazeState extends State implements Cloneable {

	/** An state is includes a position given by the coordinates (x,y) */
	public Position position;
	public int numCat;
	public int numQ;
	HashSet<Position> quesosComidos;
	private Object hashQue;
	int daño=0;

	public MazeState(Position position) {
		this.position = position;
		this.quesosComidos = new HashSet<>();
		this.numCat = 0;
		this.numQ = 0;

	}

	public MazeState(int x, int y) {
		this.position = new Position(x, y);
		this.quesosComidos = new HashSet<>();
		this.numCat = 0;
		this.numQ = 0;
	}

	//
	public MazeState(Position pos, HashSet<Position> quesosComidos, int numCat) {
		this.position = pos;
		this.numCat = numCat;
		this.quesosComidos = quesosComidos;
		this.numQ = quesosComidos.size();

	}

	public MazeState(int x, int y, HashSet<Position> quesosComidos, int numCat) {
		this.position = new Position(x, y);
		this.numCat = numCat;
		this.quesosComidos = quesosComidos;
		this.numQ = this.quesosComidos.size();

	}



	@Override
	public boolean equals(Object anotherState) {
		// TODO Auto-generated method stub //comparamos si el objeto pasado como
		// parametro es un objeto de tipo State.

		if (!(anotherState instanceof MazeState)) {
			System.out.println("Estas comparando dos objetos de distinta clase.");
			return false;
		}

//
//		return false;
//		
//		if(this.quesosComidos.size() == ((MazeState)anotherState).quesosComidos.size() && 
//				((MazeState) anotherState).position.equals(this.position)
//				&& (this.numCat == ((MazeState) anotherState).numCat)) {
//			return true;
//		}
//		return false;

		MazeState mazeAnother = (MazeState) anotherState;

		if (this.quesosComidos.equals(mazeAnother.quesosComidos) && this.position.equals(mazeAnother.position)
				&& this.numCat == mazeAnother.numCat) {
			return true;
		}

		return false;

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub //comparar objetos de una forma más rápida en
		// estructuras Hash.
		int hashQue = 0;
		for (Position pos : this.quesosComidos) {
			hashQue += pos.x + 3 * 5 + pos.y + 2 * 3;
		}

		return Objects.hash(this.position.hashCode(), this.numCat, this.numQ, this.hashQue);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub //imprimir el Estado:(posX->... ,posY->...
		// ,quesos->... ,gatos->... ).

		return "Estado: (posX-> " + this.position.x + ", posY->" + this.position.y + " ,quesos->"
				+ this.quesosComidos.size() + ", gatos->" + this.numCat + ")";

	}
}
