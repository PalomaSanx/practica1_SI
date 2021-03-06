package algorithms;

import java.util.Collection;
import java.util.Stack;

import search.Node;

public class Depth_first extends Breath_first{

	
	
	@Override
    public Collection<Node> createFrontier() {
        // tipo LIFO (Last In - First Out, o �ltimo en entrar - primero en salir). 
        return new Stack<Node>();
    }

    /* Return the node extracted from the frontier */
    @Override
    public Node extract(Collection<Node> frontier) {
        //saca elemento de la pila
        return (((Stack<Node>) frontier).pop());
    }

    /* Add a new node in the frontier */
    @Override
    public void insert(Node n, Collection<Node> frontier) {
        
        ((Stack<Node>) frontier).push(n);
    }
}
