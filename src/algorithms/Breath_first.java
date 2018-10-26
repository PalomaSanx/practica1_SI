package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import problems.maze.MazeState;
import search.*;

public class Breath_first extends SearchAlgorithm {

	@Override
	public void setParams(String[] params) {
		// TODO Auto-generated method stub
		// ANCHURA no necesita

	}

	@Override
	public void doSearch() {
		// TODO Auto-generated method stub //debe leer el estado inicial del problema
		// y llevar a cabo la búsqueda
		
		//HashSet para almacenar los nodos explorados.
		HashSet<State> explored = new HashSet<State>();
		
		//ArrayList para almacenar los sucesores
		ArrayList<Node> successors = new ArrayList<Node>();
		//dependerá del algoritmo a tratar.
		Collection<Node> frontier;
		//límite de profundidad
		int limit = Integer.MAX_VALUE;
		//
		//secuencia de acciones
		this.actionSequence = new ArrayList<Action>();

		this.totalCost = 0;
		this.expandedNodes = 0;
		this.searchTime = System.currentTimeMillis();

		Node node = new Node(this.problem.initialState());

		frontier = createFrontier();
		frontier.add(node);
		
		
		while (!(frontier.isEmpty()) ) {
			
			
			node = extract(frontier);
			//System.out.println(((MazeState)node.getState()));
			
			
			if (!(explored.contains(node.getState()))) {
				
				
				if (problem.testGoal(node.getState())) {

					
					totalCost = node.getCost();
					
					
					actionSequence = recuperarRuta(node);
					break;
				}

				else {
					
					
					if (node.getDepth() < limit) {

					
						successors = getSuccessors(node);

					
						for (Node n : successors) {

							insert(n, frontier);
						}

					
						explored.add(node.getState());
					}
				}
			}
			
		}

		searchTime = System.currentTimeMillis() - searchTime;

		Collections.reverse(actionSequence);

		// si es nodo objetivo-> guardamos en Action[] actionSequence

		// método gestiona lista abiertos

		// método gestiona lista cerrados

	}

	public ArrayList<Action> recuperarRuta(Node n) {

		// Creates a new sequence of actions to be returned
		ArrayList<Action> path = new ArrayList<Action>();

		// Recover the path in a reverse order and calculate cost
		while (!(n.getState().equals(problem.initialState()))) {

			path.add(n.getAction());
			n = n.getParent();
		}

		return path;
	}

	public Collection<Node> createFrontier() {
        
        return new LinkedList<Node>();
    }

	/* Extract the first element from the priority queue */

	  public Node extract(Collection<Node> frontier) {
	        
	        return (((LinkedList<Node>) frontier).remove());
	    }

	/* Insert an into the priority queue */

	   public void insert(Node n, Collection<Node> frontier) {
	        
	        ((LinkedList<Node>) frontier).add(n);
	    }

}
