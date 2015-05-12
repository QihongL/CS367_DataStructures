//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  SocialNetworkingApp.java
// File:             GraphADT.java
// Semester:         CS367 Spring 2015
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//
//////////////////////////// 80 columns wide /////////////////////////////////
import java.util.Set;

/**
 * This is the interface of the Graph data structure. It requires the graph
 * to have several basic operations.
 * 
 * @author Qihong
 *
 * @param <V>
 */
public interface GraphADT<V> {
	
	boolean addVertex(V vertex);
	boolean addEdge(V v1, V v2);
	void removeEdge(V v1, V v2);
	Set<V> getNeighbors(V vertex);
	Set<V> getAllVertices();
	
}
