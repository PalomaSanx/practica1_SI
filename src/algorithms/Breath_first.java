package algorithms;

import java.util.Collection;
import java.util.LinkedList;

import search.Node;

public class Breath_first extends Busqueda_Base{

	public Collection<Node> createFrontier() {
        
        return new LinkedList<Node>();
    }

    
    
    public Node extract(Collection<Node> frontier) {
        
        return (((LinkedList<Node>) frontier).remove());
    }

   
    public void insert(Node n, Collection<Node> frontier) {
        
        ((LinkedList<Node>) frontier).add(n);
    }
	
	
}
