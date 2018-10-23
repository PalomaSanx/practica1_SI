package problems.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import search.State;
import search.Action;
import search.SearchProblem;
import utils.Position;

import visualization.ProblemView;
import visualization.ProblemVisualizable;
import search.Action;
import search.Node;
import search.SearchProblem;
import search.State;

/**
 * Extends SearchProblem and implements the functions which define the maze
 * problem. Always uses two cheeses.
 */
public class MazeProblem implements SearchProblem, ProblemVisualizable {

	// Uses always three cheeses (to make it easier implementation).
	private static final int NUM_CHEESES = 3;
	// Penalty factor for fight with the cat.
	private static final double PENALTY = 2;
	
	ArrayList<Position>quesosComidos = new ArrayList<>();

	/* Maze */
	Maze maze;

	/** Builds a maze */
	public MazeProblem() {
		this.maze = new Maze(10);
	}

	public MazeProblem(int size, int seed, int cats) {
		this.maze = new Maze(size, seed, cats, NUM_CHEESES);
	}

	@Override
	public void setParams(String[] args) {
		// The maze already exists.
		// It is generated with the new parameters.
		int size = this.maze.size;
		int seed = this.maze.seed;
		int cats = this.maze.numCats;

		if (args.length == 3)
			try {
				size = Integer.parseInt(args[0]);
				cats = Integer.parseInt(args[1]);
				seed = Integer.parseInt(args[2]);
			} catch (Exception e) {
				System.out.println("Parameters for MazeProblem are incorrect.");
				return;
			}

		// Generates the new maze.
		this.maze = new Maze(size, seed, cats, NUM_CHEESES);
	}

	// PROBLEM REPRESENTATION (CORRESPONDS TO THE FUNCTIONS IN THE INTERFACE
	// SearchProblem)

	@Override
	public State initialState() {
		// TODO Auto-generated method stub //se inicia estado x=x y=0, quesos=0 y
		// gatos=0;
		return new MazeState(maze.input(), 0, 0);
	}

	@Override
	public State applyAction(State state, Action action) {
		// TODO Auto-generated method stub// devuelve el estado después de aplicar una
		// accion 'a' a un estado 'e'.

		MazeState mazeState = (MazeState) state;
		MazeAction mazeAction = (MazeAction) action;
		int x = mazeState.posX;
		int y = mazeState.posY;
	
		switch (mazeAction) {
		case EAT:
			mazeState.numCheese++;
			quesosComidos.add(mazeState.position);
					
			// quitar queso de laberinto
			break;
		case RIGHT:
			x++;

			
			break;
		case LEFT:
			x--;
			
			
			break;
		case UP:
			y--;
			
			

			break;

		case DOWN:
			y++;
			
			

			break;
		}
		if ((x < 0 || x > maze.size - 1 || y < 0 || y > maze.size - 1)) {
			return null;
		}

		return new MazeState(x, y, mazeState.numCheese, mazeState.numCat);

	}

	@Override
	public ArrayList<Action> getPossibleActions(State state) {
		// TODO Auto-generated method stub

		MazeState mazeState = (MazeState) state;
		int x = mazeState.position.x;
		int y = mazeState.posY;
		int a=0;

		ArrayList<Action> possibleActions = new ArrayList<Action>();
	
		Set<Position> rechp = maze.reachablePositions(mazeState.position);
		ArrayList<Action>catAc = new ArrayList<Action>();
		
		if ((maze.containsCat(mazeState.position))) {
			mazeState.numCat++;
			if (mazeState.numCat == 2) {
				return catAc;
			}
		}
		
		
		if (maze.containsCheese(mazeState.position) ) {
			
					possibleActions.add(MazeAction.EAT);
					quesosComidos.add(mazeState.position);					
		}		

		for (Position pos : rechp) {
			int x2 = pos.x;
			int y2 = pos.y;
			if ((x2 < 0 || x2 > maze.size - 1 || y2 < 0 || y2 > maze.size - 1)) {
				return catAc;
			}

			if (x2 == x+1 && y2 == y) {
				possibleActions.add(MazeAction.RIGHT);
			}
			if (x2 == x-1 && y2 == y) {
				possibleActions.add(MazeAction.LEFT);
			}
			if (x2 == x && y2 == y-1) {
				possibleActions.add(MazeAction.UP);
			}
			if (x2 == x && y2 == y+1) {
				possibleActions.add(MazeAction.DOWN);
			}
		}

	

		return possibleActions;
	}

	@Override
	public double cost(State state, Action action) {
		// TODO Auto-generated method stub //si gato=1 el coste x2.
		
		MazeState mazestate = (MazeState) state;
		MazeState estado_nuevo = (MazeState)applyAction(mazestate, action);
		if (estado_nuevo.numCat == 1) {
			return PENALTY;
		}
		return (double) 1;
	}

	@Override
	public boolean testGoal(State chosen) {
		// TODO Auto-generated method stub //¿Soy estado objetivo?

		MazeState choseeen = (MazeState) chosen;

		if ((choseeen.numCheese == 3) && (choseeen.position.x==maze.outputX && choseeen.position.y==maze.size-1)) {
			return true;
		}

		return false;

	}

	@Override
	public double heuristic(State state) {
		// TODO Auto-generated method stub
		return 0;
	}

	// VISUALIZATION
	/** Returns a panel with the view of the problem. */
	@Override
	public ProblemView getView(int sizePx) {
		return new MazeView(this, sizePx);
	}
}
