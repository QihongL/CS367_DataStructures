//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  SocialNetworkingApp.java
// File:             SocialGraph.java
// Semester:         CS367 Spring 2015
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//
//////////////////////////// 80 columns wide /////////////////////////////////
import java.io.StringWriter;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The class that defines the SocialGraph. It has some functions that specific 
 * to the social networking application. It also extends the functionality
 * of a genearl undirectedGraph.
 * @author Qihong
 *
 */
public class SocialGraph extends UndirectedGraph<String> {

	/**
	 * Creates an empty social graph.
	 */
	public SocialGraph() {
		super();
	}

	/**
	 * Creates a graph from a preconstructed hashmap.
	 * This method will be used to test your submissions. You will not find this
	 * in a regular ADT.
	 * 
	 * @param hashmap adjacency lists representation of a graph that has no
	 * loops and is not a multigraph.
	 */
	public SocialGraph(HashMap<String, ArrayList<String>> hashmap) {
		super(hashmap);
	}

	/**
	 * Given a person, this function will find all friends of friends of 
	 * this person. 
	 * 
	 * @param person
	 * @return fofs - a set of friends of friends of type String 
	 */
	public Set<String> friendsOfFriends(String person) {
		// create a empty set to store friends of friends
		Set<String> fofs = new HashSet();
		Iterator<String> itr = hashmap.get(person).iterator();
		// loop over all 1-dgree friends
		while(itr.hasNext()){
			String friend = itr.next();
			Iterator<String> fofItr = hashmap.get(friend).iterator();
			// get friends of friends
			while(fofItr.hasNext()){
				String fof = fofItr.next();
				fofs.add(fof);
			}
		}
		// exclude the current person 
		fofs.remove(person);
		// remove 1-dgree friend
		itr = hashmap.get(person).iterator();
		while(itr.hasNext()){
			String friend = itr.next();
			fofs.remove(friend);
		}    	
		return fofs;
	}

	/**
	 * Get shortest path between two people. 
	 * 
	 * @param pFrom
	 * @param pTo
	 * @return a path sequence if there is a path, null of no path can be found
	 */
	public List <String> getPathBetween(String pFrom, String pTo) {
		if(pFrom == null || pTo == null) throw new IllegalArgumentException();
		List<String> shortestPath = new ArrayList<String>();// store the path
		// trivial case: if source and target are the same
		if(pFrom.equals(pTo)) {
			shortestPath.add(pFrom); 
			return shortestPath; // just return the source
		}
		
		// perform deeper and deeper BFS, until the target is found 
		HashMap <String, String> prev = new HashMap<String, String>();
		HashMap <String, String> temp = new HashMap<String, String>();
		int depth = 0;// determine the limiting depth of the BFS
		while(true){
			// perform BFS
			prev = getVerticesAtDepthN(pFrom, depth);
			depth ++;
			// if the target was found 
			if(prev.containsKey(pTo)) break;
			if(depth > 1 && prev.equals(temp)) return null;
			temp = prev;
		}
		
		// reconstruct the shortest path
		String curr = pTo;
		while(true){
			shortestPath.add(curr);	// attach the previous vertex
			curr = prev.get(curr); 	// get the previous vertex
			if(curr == pFrom){		// stop if the target is found
				shortestPath.add(pFrom);
				break; 
			}
		}
		// reverse the path 
		Collections.reverse(shortestPath);
		return shortestPath;
	}

	/**
	 * This function perform BFS to with a depth limit, n. Namely, it BFS 
	 * the graph until the the searching depth is higher than n. It returns
	 * a map that records the traversing order in the form of previous vertex.
	 *      
	 * @param start - the starting vertex
	 * @param n - the limiting depth
	 * @return prev
	 */
	public HashMap <String, String> getVerticesAtDepthN(String start, int n){
		if(start == null) throw new IllegalArgumentException();
		// simulate a queue using an ArrayList
		ArrayList<String> frontier = new ArrayList<String>();
		frontier.add(start);
		// Create an new empty set
		HashSet <String> explored = new HashSet<String>();
		// Create new empty hashmap
		HashMap <String, Integer> depths = new HashMap<String, Integer>();
		// keep track of the previous vertex
		HashMap <String, String> prev = new HashMap<String, String>();
		// Map every vertex in frontier and explored to its depth
		depths.put(start,0);
		
		while (!frontier.isEmpty()){
			// dequeue the next vertex to explore 
			String vertex = frontier.remove(0);
			if (depths.get(vertex) > n ) 
				break;
			explored.add(vertex);
			
			// traverse all neighbors of the current vertex
			Iterator<String> itr = hashmap.get(vertex).iterator();
			while(itr.hasNext()){
				String neighbor = itr.next();
				if(!frontier.contains(neighbor) && !explored.contains(neighbor)){
					depths.put(neighbor, depths.get(vertex) + 1);
					// keep track of the previous vertex
					prev.put(neighbor, vertex);
					frontier.add(neighbor);
				}
			}
		}
		return prev;
	}


	/**
	 * Returns a pretty-print of this graph in adjacency matrix form.
	 * People are sorted in alphabetical order, "X" denotes friendship.
	 * 
	 * This method has been written for your convenience (e.g., for debugging).
	 * You are free to modify it or remove the method entirely.
	 * THIS METHOD WILL NOT BE PART OF GRADING.
	 *
	 * NOTE: this method assumes that the internal hashmap is valid (e.g., no
	 * loop, graph is not a multigraph). USE IT AT YOUR OWN RISK.
	 *
	 * @return pretty-print of this graph
	 */
	public String pprint() {
		// Get alphabetical list of people, for prettiness
		List<String> people = new ArrayList<String>(this.hashmap.keySet());
		Collections.sort(people);

		// String writer is easier than appending tons of strings
		StringWriter writer = new StringWriter();

		// Print labels for matrix columns
		writer.append("   ");
		for (String person: people)
			writer.append(" " + person);
		writer.append("\n");

		// Print one line of social connections for each person
		for (String source: people) {
			writer.append(source);
			for (String target: people) {
				if (this.getNeighbors(source).contains(target))
					writer.append("  X ");
				else
					writer.append("    ");
			}
			writer.append("\n");
		}

		// Remove last newline so that multiple printlns don't have empty
		// lines in between
		String string = writer.toString();
		return string.substring(0, string.length() - 1);
	}

}