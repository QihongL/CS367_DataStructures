//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            SocialNetworkingApp.java
// Files:            SocialNetworkingApp.java, SocialGraph.java, GraphADT.java
//                   UndirectedGraph.java
// Semester:         CS367 Spring 2015
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//
//////////////////////////// 80 columns wide /////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This me the main program for the social networking application. It can 
 * load connection between people via a .txt file. And one can manipulate 
 * the connection afterwards/
 * 
 * @author Qihong
 *
 */
public class SocialNetworkingApp {

	static Scanner stdin = new Scanner(System.in);
	static SocialGraph graph;
	static String prompt = ">> ";  // Command prompt

	/**
	 * Returns a social network as defined in the file 'filename'.
	 * See assignment handout on the expected file format.
	 * @param filename filename of file containing social connection data
	 * @return the graph with loaded connections
	 * @throws FileNotFoundException if file does not exist
	 */
	public static SocialGraph loadConnections(String filename) 
			throws FileNotFoundException {
		if(filename == null) throw new IllegalArgumentException();
		// connect a scanner to the input file 
		File inputFile = new File(filename);
		SocialGraph tempGraph = new SocialGraph();

		// create all users (vertices)
		Scanner scnr = new Scanner(inputFile);
		while(scnr.hasNext()){
			// identify the username (the 1st element in a line is the user)
			String username = scnr.next();
			tempGraph.addVertex(username);
			if(scnr.hasNext()) scnr.nextLine();	// get rid of the rest 
		}

		// create all friends (edges) 
		scnr = new Scanner(inputFile);
		while(scnr.hasNext()){
			String textline = scnr.nextLine();
			// split the text line 
			String [] names = textline.split(" ");
			// identify the user (the 1st element in a line is the user)
			String username = names[0];
			// add the friends (the remaining text represents the friends)
			for(int i = 1; i < names.length; i ++)
				tempGraph.addEdge(username, names[i]);
		} // end of while

		scnr.close(); // close the scanner for reading initial input file 
		return tempGraph;
	}

	/**
	 * Access main menu options to login or exit the application.
	 */
	public static void enterMainMenu() {
		boolean exit = false;
		while (!exit) {
			System.out.print(prompt);
			String[] tokens = stdin.nextLine().split(" ");
			String cmd = tokens[0];
			String person = (tokens.length > 1 ? tokens[1] : null);

			switch(cmd) {
			case "login":
				System.out.println("Logged in as " + person);
				enterSubMenu(person);
				System.out.println("Logged out");
				break;
			case "exit":
				exit = true;
				break;
			default:
				System.out.println("Invalid command");
			}
		}
	}

	/**
	 * Access submenu options to view the social network from the perspective
	 * of currUser. Assumes currUser exists in the network.
	 * @param currUser
	 */
	public static void enterSubMenu(String currUser) {

		// Define the set of commands that have no arguments or one argument
		String commands =
				"friends fof logout print\n" +
						"connection friend unfriend";
		Set<String> noArgCmds = new HashSet<String>
		(Arrays.asList(commands.split("\n")[0].split(" ")));
		Set<String> oneArgCmds = new HashSet<String>
		(Arrays.asList(commands.split("\n")[1].split(" ")));

		boolean logout = false;
		while (!logout) {
			System.out.print(prompt);

			// Read user commands
			String[] tokens = stdin.nextLine().split(" ");
			String cmd = tokens[0];
			String otherPerson = (tokens.length > 1 ? tokens[1] : null);

			// Reject erroneous commands
			if (tokens.length == 0) continue;
			if (!noArgCmds.contains(cmd) && !oneArgCmds.contains(cmd)) {
				System.out.println("Invalid command");
				continue;
			}
			if (oneArgCmds.contains(cmd) && otherPerson == null) {
				System.out.println("Did not specify person");
				continue;
			}

			// switch on the input command
			switch(cmd) {

			case "connection": {
				// print the shortest path
				List <String> path = graph.getPathBetween(currUser, tokens[1]);
				if(path == null){
					System.out.println("You are not connected to " + tokens[1]);
				} else {
					System.out.println(path);
				}
				break;
			}

			case "friends": {
				Set friends = graph.getNeighbors(currUser);
				if(friends.size() <= 0){ 
					System.out.println("You do not have any friends");
				} else { 
					// store friends in a list 
					ArrayList <String> friendsList = new ArrayList<String>();
					Iterator<String> itr = friends.iterator();
					while(itr.hasNext()){
						friendsList.add(itr.next());
					}
					// sort the friends alphabetically and print them
					Collections.sort(friendsList);
					System.out.println(friendsList.toString());
				}
				break;
			}

			case "fof": {
				// get friends of friends as a set 
				Set <String> fofs = graph.friendsOfFriends(currUser);
				// store fofs in an arrayList (for sorting)
				ArrayList<String> fofsList = new ArrayList<String>();
				Iterator<String> itr = fofs.iterator();
				while(itr.hasNext()){
					fofsList.add(itr.next());
				}
				// sort the list 
				Collections.sort(fofsList);
				if(fofsList.size() <= 0){
					System.out.println("You do not have any friends of friends");
				} else {
					System.out.println(fofsList.toString());
				}
				break;
			}

			case "friend": {
				// add connection and get the results of this operation 
				boolean success = graph.addEdge(currUser, tokens[1]);
				// and print the feedback correspondingly 
				if(success){
					System.out.println("You are now friends with " + tokens[1]);
				} else { 
					System.out.println("You are already friends with " + tokens[1]);
				}
				break;
			}

			case "unfriend": {
				// remove the connection only if they have connection
				if (graph.getNeighbors(currUser).contains(tokens[1])){
					graph.removeEdge(currUser, tokens[1]);
					System.out.println("You are no longer friends with " + tokens[1]);
				} else {
					// prompt correspondingly if no connection to be removed
					System.out.println("You are already not friends with " + tokens[1]);
				}
				break;
			}

			case "print": {
				System.out.println("--------------------------\n"
						+ "The graph:" +"\n" + graph.toString() 
						+ "--------------------------");

				graph.pprint();

				System.out.println("* Get all vertices");
				graph.getAllVertices();

				break;
			}

			case "logout":
				logout = true;
				break;
			}  // End switch
		}
	}

	/**
	 * Commandline interface for a social networking application.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Usage: java SocialNetworkingApp <filename>");
			return;
		}
		try {
			graph = loadConnections(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		enterMainMenu();

	}

}
