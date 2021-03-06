package problems.maze;

import java.util.ArrayList;
import java.util.Collections;
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

	public String heuristica = null;

	// numQ=0;

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
		heuristica = this.heuristica;

		if (args.length == 4)
			try {
				size = Integer.parseInt(args[0]);
				cats = Integer.parseInt(args[1]);
				seed = Integer.parseInt(args[2]);
				heuristica = args[3];
				System.out.println("heuristica:" + heuristica);
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
		return new MazeState(maze.input());
	}

	@Override
	public State applyAction(State state, Action action) {
		// TODO Auto-generated method stub// devuelve el estado despu�s de aplicar una
		// accion 'a' a un estado 'e'.

		MazeState mazeState = (MazeState) state;
		MazeAction mazeAction = (MazeAction) action;
		HashSet<Position> quesoscomidosclon = new HashSet<>();
		quesoscomidosclon.addAll(mazeState.quesosComidos);

		int x = mazeState.position.x;
		int y = mazeState.position.y;
		int numCat = mazeState.numCat;

		if (maze.containsCat(mazeState.position)) {
			numCat++;
		}

		switch (mazeAction) {
		case EAT:

			quesoscomidosclon.add(mazeState.position);

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
//		if ((x < 0 || x > maze.size - 1 || y < 0 || y > maze.size - 1)) {
//			return null;
//		}

		return new MazeState(x, y, quesoscomidosclon, numCat);

	}

	@Override
	public ArrayList<Action> getPossibleActions(State state) {
		// TODO Auto-generated method stub

		MazeState mazeState = (MazeState) state;
		int x = mazeState.position.x;
		int y = mazeState.position.y;

		ArrayList<Action> possibleActions = new ArrayList<Action>();

		Set<Position> rechp = maze.reachablePositions(mazeState.position);

		// ArrayList<Action>catAc = new ArrayList<Action>();

		if ((mazeState.numCat >= PENALTY)) {
			return new ArrayList<>();

		}

		if (maze.containsCheese(mazeState.position)) {
			if (!(mazeState.quesosComidos.contains(mazeState.position))) {
				possibleActions.add(MazeAction.EAT);

			}
		}

		for (Position pos : rechp) {
			int x2 = pos.x;
			int y2 = pos.y;
//			if ((x2 < 0 || x2 > maze.size - 1 || y2 < 0 || y2 > maze.size - 1)) {
//				System.out.println("TE VAS FUERA");;
//			}

			if (x2 == x + 1 && y2 == y) {
				possibleActions.add(MazeAction.RIGHT);
			}
			if (x2 == x - 1 && y2 == y) {
				possibleActions.add(MazeAction.LEFT);
			}
			if (x2 == x && y2 == y - 1) {
				possibleActions.add(MazeAction.UP);
			}
			if (x2 == x && y2 == y + 1) {
				possibleActions.add(MazeAction.DOWN);
			}
		}

		return possibleActions;
	}

	@Override
	public double cost(State state, Action action) {
		// TODO Auto-generated method stub //si gato=1 el coste x2.

		MazeState mazestate = (MazeState) state;
		MazeState estado_nuevo = (MazeState) applyAction(mazestate, action);
		if (estado_nuevo.numCat == 0) {
			return 1;
		}

		return 2;
	}

	@Override
	public boolean testGoal(State chosen) {
		// TODO Auto-generated method stub //�Soy estado objetivo?

		MazeState state = (MazeState) chosen;

		if (state.position.equals(maze.output()) && state.numQ == NUM_CHEESES && state.numCat <= 1) {
			return true;
		}
		return false;

	}

	@Override
	public double heuristic(State state) {
		// TODO Auto-generated method stub

		// h1=es la heuristica de encontrar el primer queso y irse a la salida.
		MazeState mazeState = (MazeState) state;
		HashSet<Position> posicionesQueso2 = (HashSet) maze.cheesePositions;
		HashSet<Position> posicionesQueso = (HashSet) maze.cheesePositions;
		ArrayList<Integer> sol = new ArrayList<>();
		HashSet<Position> queso = new HashSet<>();
		int minValue = 0;
		int minValue2 = 0;
		int x = 0;
		int Num_Q = this.NUM_CHEESES;

		if (this.heuristica.equals("h1")) {
			Position q = mazeState.position;
			Position qAnterior = mazeState.position;

			System.out.println(q);
			while (Num_Q != 0) {
				for (Position pos : posicionesQueso) {
					if (!queso.contains(pos)) {
						System.out.println("q" + q + "pos" + pos);

						System.out.println("1---pos x =" + q.x + "pos y=" + q.y);

						sol.add((Math.abs(q.x - pos.x)) + ((Math.abs(q.y - pos.y))));

					}

				}

				minValue2 = minValue2 + (Collections.min(sol)); // distancia m�s cercana
				System.out.println(minValue2 + "-->" + sol);// SOUT

				for (Position pos2 : posicionesQueso) {
					if (!queso.contains(pos2)) {
						if (Collections.min(sol) == ((Math.abs(q.x - pos2.x)) + ((Math.abs(q.y - pos2.y))))) { // 2>=2
							System.out.println(Collections.min(sol) + "== "+ ((Math.abs(q.x - pos2.x)) + ((Math.abs(q.y - pos2.y)))));
							qAnterior = q;
							q = pos2;
							x++;
							if (x == 2) {
								if((Math.abs(qAnterior.x - maze.outputX) + Math.abs(qAnterior.y - (maze.size - 1))) >= (Math.abs(q.x - maze.outputX) + Math.abs(q.y - (maze.size - 1)))){
									q = qAnterior;
								}
								
							}

						}
					}

				}
				x = 0;
				queso.add(q);
				System.out.println("q " + q);// SOUT
				System.out.println("---------------------------");// SOUT
				// sol.remove(Collections.min(sol));
				sol = new ArrayList<>();
				Num_Q--;// ->2,->1,->0
			}

			minValue = minValue2 + (Math.abs(q.x - maze.outputX) + Math.abs(q.y - (maze.size - 1)));

			System.out.println("heuristicaaaaaaaaaaaaaaaaaa=" + minValue);
			System.out.println("---------------------------------");
			return minValue;

		}

		if (this.heuristica.equals("h2")) {
			// h2= distancia de manhattan (distancia desde state a Finalstate.

			for (Position pos : posicionesQueso2) { // cogemos todas la posiciones
				sol.add(((Math.abs(mazeState.position.x - pos.x)) + ((Math.abs(mazeState.position.y - pos.y)))));
				System.out.println(x + "=" + sol);
				x++;
			}

			minValue = (Collections.min(sol))
					+ (Math.abs(mazeState.position.x - maze.outputX) + Math.abs(mazeState.position.y - maze.size - 1));

			return minValue;
		}

		return minValue;

	}

	// VISUALIZATION
	/** Returns a panel with the view of the problem. */
	@Override
	public ProblemView getView(int sizePx) {
		return new MazeView(this, sizePx);
	}
}
