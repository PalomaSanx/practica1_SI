package algorithms;

import java.util.Collection;
import java.util.PriorityQueue;

import search.Node;

public class A_star extends Breath_first{
	
	
	 @Override
	    public Collection<Node> createFrontier() {
	        
	        return new PriorityQueue<Node>(100, Node.BY_EVALUATION);
	    }

	    /* Extract the first element from the priority queue */
	    @Override
	    public Node extract(Collection<Node> frontier) {
	        
	        return ((PriorityQueue<Node>) frontier).poll();
	    }

	    /* Insert an into the priority queue */
	    @Override
	    public void insert(Node n, Collection<Node> frontier) {
	       
	        ((PriorityQueue<Node>) frontier).add(n);
	    }

}
