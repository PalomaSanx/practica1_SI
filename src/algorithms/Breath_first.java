package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import problems.maze.MazeState;
import search.*;

//ESTA CLASE IMPLEMENTA EL ALGORITMO DE BÚSQUEDA POR ANCHURA, SERÁ LA BASE PARA EL RESTO DE ALGORITMOS.
public class Breath_first extends SearchAlgorithm {

	int limit = 0;

	@Override
	public void setParams(String[] params) {
		// TODO Auto-generated method stub
		// ANCHURA no necesita

		String[] s = params;
		this.limit = Integer.parseInt(params[0]);
		System.out.println("Limite: " + limit);

	}

	@Override
	public void doSearch() {
		// TODO Auto-generated method stub //debe leer el estado inicial del problema
		// y llevar a cabo la búsqueda

		// HashSet para almacenar los nodos explorados.
		HashSet<State> explored = new HashSet<State>();
		// ArrayList para almacenar los sucesores
		ArrayList<Node> successors = new ArrayList<Node>();
		// dependerá del algoritmo a tratar.
		Collection<Node> frontier;
		// inicio la busqueda
		this.initSearch();
		// se crea nodo que representa la inicializacion del estado
		Node node = new Node(this.problem.initialState());
		// se crea la frontera
		frontier = createFrontier();
		// se añade el nodo inicial creado a la frontera.
		frontier.add(node);

		int limit = 0;
		// aqui se comprueba si se esta metiendo un límite por parametro, si es así, se
		// añadirá dicho limite
		if (this.limit != 0) {
			limit = this.limit;
		} else { // sino, se establece un valor infinito para que se pueda realizar la búsqueda
			limit = Integer.MAX_VALUE;
		}
		while (!(frontier.isEmpty())) { // si la frontera no esta vacia se extrae un nodo, si no se habia explorado
										// antes, se comprueba si es la solucion final (se obtendría el coste, la secuencia de acciones 
										// y estabelcemos que es solucion.
										//sino es solucion: vemos que la profundida del nodo < limite (esto para el caso de algoritmo de profundidad limitada).
										// obtenemos los sucesores, y para cada sucesor lo insertamos en la frontera y añadimos a explorados.
			node = extract(frontier);
			
			if (!(explored.contains(node.getState()))) {

				if (problem.testGoal(node.getState())) {

					totalCost = node.getCost();

					actionSequence = recuperarRuta(node);
					
					System.out.println("tamaño ruta explorada ="+actionSequence.size()+"-->"+actionSequence);
					int p = (int) (actionSequence.size()+this.openMaxSize);
					System.out.println("espacio en memoria alg.prof="+p);
					if (explored.size() > this.exploredMaxSize) {
						this.exploredMaxSize = (int) (actionSequence.size()+this.openMaxSize);
					}

					solutionFound = true;
					break;
				}

				else {

					if (node.getDepth() < limit) {

						successors = getSuccessors(node);

						for (Node n : successors) {

							insert(n, frontier);
						}

			

					}
				}
			}
			explored.add(node.getState());
			if (frontier.size() > this.openMaxSize) {
				this.openMaxSize = frontier.size();
			}
			if (explored.size() > this.exploredMaxSize) {
				this.exploredMaxSize = explored.size();
			}
			
			
			
			

		}

		Collections.reverse(actionSequence);
		System.out.println("Nodos generados="+this.generatedNodes);
		System.out.println("Profundidad="+node.getDepth()+"\n");
		

	}

	public ArrayList<Action> recuperarRuta(Node n) {

		// Crea una nueva secuencia de acciones para retornala.
		ArrayList<Action> path = new ArrayList<Action>();

		
		//recorre el camino en orden inverso y calcula el coste.
		while (!(n.getState().equals(problem.initialState()))) {

			path.add(n.getAction());
			n = n.getParent();
		}

		return path;
	}
	
	
	
	
	//Para retornar un LinkedList con la frontera.
	public Collection<Node> createFrontier() {

		return new LinkedList<Node>();
	}

	//Retornar un nodo extraido de la frontera.

	public Node extract(Collection<Node> frontier) {

		return (((LinkedList<Node>) frontier).remove());
	}

	//Añade un nodo en la frontera.

	public void insert(Node n, Collection<Node> frontier) {

		((LinkedList<Node>) frontier).add(n);
	}

}
