///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 1 - Reddit
// Files:            Reddit.java
// Semester:         CS367 Spring 2015
//
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * This class controls the entire reddit program.   
 * */
public class Reddit {

	private static ArrayList<String> fileNamesList;	// list for files names
	private static RedditDB reddit;					// the reddit database
	private static String currentUser = "";			// current logged in user
	private static boolean noUserLoggedIn = true;	// flag 
	private static Post currentPost;				// the current post

	/**
	 * The main class that controls the execution of the Reddit program 
	 * */
	public static void main(String[] args) {
		// initialize all fields  
		reddit = new RedditDB();
		fileNamesList = new ArrayList<String>();
		reddit.addUser(Util.ADMIN_NAME);

		// store the command-line arguments into a fileNamesList  
		if (args.length >= 1){
			// obtain the list for files names 
			for (int i = 0; i < args.length; i ++) 
				fileNamesList.add(args[i]);
		} else {
			// exit the program if no arg 
			System.out.println(Util.NO_INPUT_FILE);
			Util.scnr.close();
			System.exit(0);
		}

		// read though the fileNameList, create corresponding files 
		Iterator<String> itr = fileNamesList.iterator();
		while (itr.hasNext()) {
			String filename = itr.next();	// store the filename 
			File tempFile = new File(filename);	// create a file 
			// trim the ".txt" part 
			String userName = filename.split(".txt")[0];
			// and create an user
			reddit.addUser(userName);
			try {
				// load the reddit files with associated user name 
				loadRedditFile(tempFile, reddit.findUser(userName));
			} catch (FileNotFoundException e) {
				System.out.println("File " + filename + " not found.");
				Util.scnr.close();
				System.exit(0);
			}
		} 
		// enter the main loop for the program 
		while(true){
			MenuIOHandler();
		}
	} // end of the main method


	/**
	 * This method handles controls the menu input and output 
	 * */
	private static void MenuIOHandler(){
		// print the current user name
		displayLogin(currentUser);
		// get command(s) 
		String input = Util.scnr.nextLine();
		// split the command into sub-pieces
		String [] tempCommand = input.split(" "); 
		// if there are more than 2 arguments, it's invalid
		if (tempCommand.length > 2){
			System.out.println(Util.INVALID_COMMAND_MSG);
			// if there are two input arguments
		} else if(tempCommand.length == 1){
			switch (tempCommand[0]) {
			case Util.LOGIN:	// when user typed "l"
				loginControl();	
				break;
			case Util.SUMMARY:	// when user typed "s"
				summaryControl();
				break;
			case Util.FRONT_PAGE:// when user typed "f"
				System.out.println("Displaying the front page...");
				frontPageControl(tempCommand);
				break;
			case Util.EXIT:		// when user typed "x"
				System.out.println("Exiting to the real world...");
				Util.scnr.close();
				System.exit(0);
				break;
			default:
				System.out.println(Util.INVALID_COMMAND_MSG);
				break;
			}
		} else if (tempCommand.length == 2){
			switch (tempCommand[0]) {
			case Util.LOGIN:		// when user typed "l" + "userName"
				loginControl(tempCommand[1]);
				break;
			case Util.DELETE:		// when user typed "d" + "userName"
				deleteControl(tempCommand);
				break;
			case Util.SUBREDDIT_MENU:// when user typed "r" + "subredditName"
				frontPageControl(tempCommand);
				break;
			case Util.USER_NAME_MENU:// when user typed "u" + "userName"
				frontPageControl(tempCommand);
				break;
			default:
				System.out.println(Util.INVALID_COMMAND_MSG);
				break;
			}	// end of the switch statement
		} else {
			System.out.println("An unhandled condition! Check it!");
		}	// end if if-else clase: input.length == 2

	}	// end of MenuIOHandler method

	/**
	 * This method display the user login name to the console  
	 * */
	private static void displayLogin(String currentUser){
		if(noUserLoggedIn)
			System.out.print("["+ Util.INITIAL_LOGIN_NAME +"@reddit]$ ");
		else{
			System.out.print("[" + currentUser + "@reddit]$ ");
		}
	}

	/**
	 * This method logs in a new user and replace the current user
	 * Used when the input command has length of 2
	 * Namely, the input is "l" + "new user's name"
	 * 
	 * @param userName - the name of the user with the "l" command  
	 * */
	private static void loginControl(String userName){
		// check if the user is already logged in 
		if (!noUserLoggedIn){
			System.out.println("User "+ currentUser +" already logged in.");
			//check if no such user
		} else if (reddit.findUser(userName) == null)
			System.out.println("User " + userName + " not found.");
		// otherwise...  
		else{
			// re-set the current user 
			currentUser = userName;
			noUserLoggedIn = false;
			System.out.println("User " + currentUser + " logged in.");
		}
	}

	/**
	 * This method logs out current user. 
	 * It is used when the input command has length of 1.
	 * Namely, the input is exactly "l".
	 * */
	private static void loginControl(){
		if (noUserLoggedIn){
			System.out.println("No user logged in.");
		} else {
			System.out.println("User " + currentUser + " logged out.");
			noUserLoggedIn = true;
			currentUser = "";
		}
	}

	/**
	 * This method prints the summary of all user
	 * It can be used only when the current user is admin
	 * */
	private static void summaryControl(){
		if (currentUser.equals(Util.ADMIN_NAME)){
			Iterator <User> usersItr = reddit.getUsers().iterator();
			while(usersItr.hasNext()){
				User tempUser = usersItr.next();
				System.out.print(tempUser.getName() + "\t" + 
						tempUser.getKarma().getLinkKarma() + "\t" + 
						tempUser.getKarma().getCommentKarma() + "\n");
			}
		} else {
			System.out.println(Util.INVALID_COMMAND_MSG);
		}
	}

	/**
	 * This method deletes a existing user
	 * It assume the input commands has length of 2
	 * Only admin can user it 
	 * And it cannot delete admin
	 * 
	 * @param commands: "d" or "d" + "username" 
	 * */
	private static void deleteControl(String [] commands){
		// if the current user is admin and there is a name to delete... 
		if(currentUser.equals(Util.ADMIN_NAME) && commands.length == 2
				// and the name to be deleted is not be "admin"
				&& !commands[1].equals(Util.ADMIN_NAME)){
			// see if the second part refers to an user
			boolean userDeleted = reddit.delUser(commands[1]);
			if (userDeleted){
				System.out.println("User " + commands[1] + " deleted.");
			} else {
				System.out.println("User " + commands[1] + " not found.");
			}
			// return error msg if no user detected 
		} else {
			System.out.println(Util.INVALID_COMMAND_MSG);
		}
	}

	/**
	 * This method display the front page, it takes input commands and perform 
	 * corresponding actions.  
	 * 
	 * @param commands - the input commands
	 * 
	 * */
	private static void frontPageControl(String [] commands) {
		// the list of posts that the frontpage is going to display 
		List<Post> posts = new ArrayList<Post>();
		// whether continue displaying the next post
		boolean getNextPost = true;
		
		// if the user input is "f"
		if (commands.length == 1){
			if(commands[0].equals(Util.FRONT_PAGE))
				// get the list of posts
				posts = reddit.getFrontpage(reddit.findUser(currentUser));
		} else if (commands.length == 2){
			// if the user input is "r" + "subredditname"
			if(commands[0].equals(Util.SUBREDDIT_MENU)){
				System.out.println("Displaying /r/" + commands[1] + "...");
				// get all posts belongs to the subreddit
				posts = reddit.getFrontpage(reddit.findUser(currentUser), 
											commands[1]);
				// if the user input is "u" + "userName"
			} else if (commands[0].equals(Util.USER_NAME_MENU)){
				String tempName = commands[1];
				// if the input user name does not exist 
				if(reddit.findUser(tempName) == null){
					// do add any post to the posts list  
					System.out.println("Displaying /u/" + commands[1] + "...");
					// if user name found 
				} else {
					System.out.println("Displaying /u/" + commands[1] + "...");									
					// get all posts of a user
					posts = reddit.findUser(commands[1]).getPosted();					
				}
			}
		}		
		// loop over all posts 
		Iterator<Post> postsItr = posts.iterator();
		while(true){
			// check point 
			if(! postsItr.hasNext()){
				System.out.println("No posts left to display.");
				System.out.println("Exiting to the main menu...");
				break;
			}
			
			// advance the post if getNextPost
			if(getNextPost){
				currentPost = postsItr.next();
			} 
			// display a post 
			System.out.println(currentPost.getKarma() 
					+ "\t" + currentPost.getTitle());
			// display the logged-in user name
			displayLogin(currentUser);

			// get a user input command for sub-menu option
			String userInput = Util.scnr.next();
			
			// 1. when user typed like - "a"
			if (userInput.equals(Util.LIKE)) {	
				// if no user logged in, remind the user to log in
				if(noUserLoggedIn){
					System.out.println("Login to like post.");
					getNextPost = false;
					// if there is a user logged in, up-vote the current post 
				} else {
					reddit.findUser(currentUser).like(currentPost);
					getNextPost = true;
				}
				if(noNextPost(postsItr)) break;
				// 2. when user typed dislike - "z"
			} else if (userInput.equals(Util.DISLIKE)){ 
				// if no user logged in, remind the user to log in
				if (noUserLoggedIn){
					System.out.println("Login to dislike post.");	
					getNextPost = false;
				} else {
					// if there is a user logged in, down-vote the current post
					reddit.findUser(currentUser).dislike(currentPost);
					getNextPost = true;
				}
				if (noNextPost(postsItr)) break;
				// 3. when user typed next - "j"
			} else if (userInput.equals(Util.NEXT)){
				if(noNextPost(postsItr)) break;
				getNextPost = true;
				// 4. when user typed exit - "x"
			} else if (userInput.equals(Util.EXIT)){	
				System.out.println("Exiting to the main menu...");
				Util.scnr.nextLine();	// buffer for cleaning all inputs
				break;
				// 5. user type something else -> invalid 
			} else {
				getNextPost = false;
				System.out.println(Util.INVALID_COMMAND_MSG);
			}	// end of the if-else for handling sub_menu input 
		}	// end of the while loop - iteratively displaying posts 
	}
	
	
	/**
	 * Test if the list has next post with iterator, and print the 
	 * corresponding information 
	 * 
	 * @param the itrator of the post list
	 * @return true if there is no next post, false if there is a next post
	 * */
	private static boolean noNextPost(Iterator<Post> itr) {
		if(itr.hasNext()){
			return false;
		} else {
			System.out.println("No posts left to display.\n"
					+ "Exiting to the main menu...");
			Util.scnr.nextLine();	// clean all input before exit 
			return true;
		} 
	}
	
	/**
	 * Load the .txt format subreddit file. The name of the file should be the 
	 * user's name. Inside the file, it contains all subreddits that user 
	 * subscribed, and all posts the user posted.
	 * 
	 * @param file - the .txt file 
	 * @param user - the user 
	 * */
	public static void loadRedditFile(File file, User user) 
			throws FileNotFoundException{
		boolean isFirstLine = true; 
		Scanner input = new Scanner(file);
		while (input.hasNext()) {
			// read all lines
			String textLine = input.nextLine();
			// treat the first line as a list of subreddits 
			if (isFirstLine){
				loadSubreddits(textLine, user);
				isFirstLine = false;
			} else {
				// treat other lines as the posts
				loadPost(textLine, user);
			}
		}
		input.close();	// close the scanner
	} 
	
	/**
	 * Read all subreddits for a user
	 * 
	 * @param subredditLine - a text line contains all subreddits for a user
	 * @param user - the owner of those subreddits
	 * */
	private static void loadSubreddits(String subredditLine, 
			User user){
		// split the text line intro subreddit
		String[] temp = subredditLine.split(", ");
		for(int i = 0; i < temp.length; i ++) {
			// read all subreddits into an string array
			String subredditName = temp[i].toLowerCase();
			// subscribe it for the user
			user.subscribe(subredditName);
		}
	}
	
	/**
	 * Load a post from the text line and add it to the user's post list
	 * 
	 *@param textLine - a text line contains the subreddit, type and title
	 *@param User - the owner of the post 
	 * */
	private static void loadPost(String textLine, User user){
		// read the entire line
		Scanner input = new Scanner(textLine);
		// load subreddit, type, title in order
		String subreddit = input.next().split(",")[0].toLowerCase();
		String type = input.next().split(",")[0].toLowerCase();
		String title = input.nextLine();
		// add post with corresponding information 
		switch (type) {
		case "comment":
			user.addPost(subreddit, PostType.COMMENT, title);
			break;
		case "link":
			user.addPost(subreddit, PostType.LINK, title);
			break;
		case "self":
			user.addPost(subreddit, PostType.SELF, title);
			break;
		default:
			System.out.println("Invalid PostType detected");
			break;
		}
		input.close();
	}
	
}
