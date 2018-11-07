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
	public int numCat = 0;
	HashSet<Position> quesosComidos = new HashSet<>();
	public int numQuesos= quesosComidos.size();

	public MazeState(Position position) {
		this.position = position;

	}

	public MazeState(int x, int y) {
		this.position = new Position(x, y);

	}

	public MazeState(int x, int y, int numCat) {
		this.position = new Position(x, y);
		this.numCat = numCat;
	}

	//
	public MazeState(int x, int y, HashSet<Position> quesosComidos, int numCat, int numQuesos) {
		this.position = new Position(x, y);
		this.numCat = numCat;
		this.quesosComidos = quesosComidos;
		this.numQuesos=numQuesos;

	}

	// (X,Y,G)

	//

	public int getX() {
		return this.position.x;
	}

	public int getY() {
		return this.position.y;
	}

	@Override
	public boolean equals(Object anotherState) {
		// TODO Auto-generated method stub //comparamos si el objeto pasado como
		// parametro es un objeto de tipo State.

		if (!(anotherState instanceof MazeState)) {
			System.out.println("Estas comparando dos objetos de distinta clase.");
			return false;
		}

		// comparamos posiciones(x,y) para el objeto pasado como parámetro.
//		if ((this.numCat == ((MazeState) anotherState).numCat)
//				&& (((MazeState) anotherState).position.equals(this.position))) {
//			for (Position QuesosComidosAnother : (((MazeState) anotherState).quesosComidos)) {
//				if (!(this.quesosComidos.contains(QuesosComidosAnother))) {
//					return false;
//				}
//			}
//			return true;
//
//		}
//
//		return false;
		
		if(this.quesosComidos.size() == ((MazeState)anotherState).quesosComidos.size() && 
				((MazeState) anotherState).position.equals(this.position)
				&& (this.numCat == ((MazeState) anotherState).numCat)) {
			return true;
		}
		return false;
		
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub //comparar objetos de una forma más rápida en
		// estructuras Hash.
		return Objects.hash(this.position, this.numCat);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub //imprimir el Estado:(posX->... ,posY->...
		// ,quesos->... ,gatos->... ).

		return "Estado: (posX-> " + this.position.x + ", posY->" + this.position.y + " ,quesos->"
				+ this.quesosComidos.size() + ", gatos->" + this.numCat + ")";

	}
}
