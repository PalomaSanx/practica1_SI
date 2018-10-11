package problems.maze;

import java.util.ArrayList;
import java.util.HashSet;

import search.State;
import search.Action;
import search.SearchProblem;
import utils.Position;

import visualization.ProblemView;
import visualization.ProblemVisualizable;

/**
 * Extends SearchProblem and implements the functions which define the maze
 * problem. Always uses two cheeses.
 */
public class MazeProblem implements SearchProblem, ProblemVisualizable {

	// Uses always three cheeses (to make it easier implementation).
	private static final int NUM_CHEESES = 3;
	// Penalty factor for fight with the cat.
	private static final double PENALTY = 2;

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
		// TODO Auto-generated method stub
		return new MazeState(maze.input());
	}

	@Override
	public State applyAction(State state, Action action) {
		// TODO Auto-generated method stub

		MazeState mazeState = (MazeState) state;
		MazeAction mazeAction = (MazeAction) action;
		int x=mazeState.position.x;
		int y=mazeState.position.y;

		if ((maze.containsCheese(mazeState.position))) {
			mazeState.numCheese++;
			// quitar queso de matriz
		} else if ((maze.containsCat(mazeState.position))) {
			if (mazeState.numCat == 2) {
				// mueres
			}
			mazeState.numCat++;
			// aumentamos el coste x2
		} else {
			// nos movemos
			switch (mazeAction) {
			case RIGHT: x++;

				break;
			case LEFT:	x--;

				break;
			case UP:	y--;
				break;

			case DOWN: y++;
				break;
			}			
		}
		
		if(x<0 || x>maze.size-1 || y<0 || y>maze.size-1) {
			return null;
		}else {
			
			return new MazeState(x,y,mazeState.numCheese,mazeState.numCat);
		}
		
	}

	@Override
	public ArrayList<Action> getPossibleActions(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double cost(State state, Action action) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean testGoal(State chosen) {
		// TODO Auto-generated method stub
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
