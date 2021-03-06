//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            VersionControlApp
// Files:            VersionControlApp.java  Repo.java  RepoCopy.java
// 		     VersionControlDb.java  User.java  Change.java
//                   ChangeSet.java  StackADT.java  QueueADT.java
// 		     EmptyStackException.java   EmptyQueueException.java
//	 	     Document.java   ErrorType.java
// Semester:         CS367 Spring 2015
//
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION //////////////////
//
// Pair Partner:     Qianyun Ma
// Email:            qma27@wisc.edu
// CS Login:         qianyun
// Lecturer's Name:  Jim Skrentny
//
//////////////////////////// 80 columns wide /////////////////////////////////
import java.util.Scanner;


/**
 * Version control application. Implements the command line utility
 * for Version control.
 * @author
 *
 */
public class VersionControlApp {

	/* Scanner object on input stream. */
	private static final Scanner scnr = new Scanner(System.in);

	/**
	 * An enumeration of all possible commands for Version control system.
	 */
	private enum Cmd {
		AU, DU,	LI, QU, AR, DR, OR, LR, LO, SU, CO, CI, RC, VH, RE, LD, AD,
		ED, DD, VD, HE, UN
	}

	/**
	 * Displays the main menu help. 
	 */
	private static void displayMainMenu() {
		System.out.println("\t Main Menu Help \n" 
				+ "====================================\n"
				+ "au <username> : Registers as a new user \n"
				+ "du <username> : De-registers a existing user \n"
				+ "li <username> : To login \n"
				+ "qu : To exit \n"
				+"====================================\n");
	}

	/**
	 * Displays the user menu help. 
	 */
	private static void displayUserMenu() {
		System.out.println("\t User Menu Help \n" 
				+ "====================================\n"
				+ "ar <reponame> : To add a new repo \n"
				+ "dr <reponame> : To delete a repo \n"
				+ "or <reponame> : To open repo \n"
				+ "lr : To list repo \n"
				+ "lo : To logout \n"
				+ "====================================\n");
	}

	/**
	 * Displays the repo menu help. 
	 */
	private static void displayRepoMenu() {
		System.out.println("\t Repo Menu Help \n" 
				+ "====================================\n"
				+ "su <username> : To subcribe users to repo \n"
				+ "ci: To check in changes \n"
				+ "co: To check out changes \n"
				+ "rc: To review change \n"
				+ "vh: To get revision history \n"
				+ "re: To revert to previous version \n"
				+ "ld : To list documents \n"
				+ "ed <docname>: To edit doc \n"
				+ "ad <docname>: To add doc \n"
				+ "dd <docname>: To delete doc \n"
				+ "vd <docname>: To view doc \n"
				+ "qu : To quit \n" 
				+ "====================================\n");
	}

	/**
	 * Displays the user prompt for command.  
	 * @param prompt The prompt to be displayed.
	 * @return The user entered command (Max: 2 words).
	 */
	private static String[] prompt(String prompt) {
		System.out.print(prompt);
		String line = scnr.nextLine();
		String []words = line.trim().split(" ", 2);
		return words;
	}

	/**
	 * Displays the prompt for file content.  
	 * @param prompt The prompt to be displayed.
	 * @return The user entered content.
	 */
	private static String promptFileContent(String prompt) {
		System.out.println(prompt);
		String line = null;
		String content = "";
		while (!(line = scnr.nextLine()).equals("q")) {
			content += line + "\n";
		}
		return content;
	}

	/**
	 * Validates if the input has exactly 2 elements. 
	 * @param input The user input.
	 * @return True, if the input is valid, false otherwise.
	 */
	private static boolean validateInput2(String[] input) {
		if (input.length != 2) {
			System.out.println(ErrorType.UNKNOWN_COMMAND);
			return false;
		}
		return true;
	}

	/**
	 * Validates if the input has exactly 1 element. 
	 * @param input The user input.
	 * @return True, if the input is valid, false otherwise.
	 */
	private static boolean validateInput1(String[] input) {
		if (input.length != 1) {
			System.out.println(ErrorType.UNKNOWN_COMMAND);
			return false;
		}
		return true;
	}

	/**
	 * Returns the Cmd equivalent for a string command. 
	 * @param strCmd The string command.
	 * @return The Cmd equivalent.
	 */
	private static Cmd stringToCmd(String strCmd) {
		try {
			return Cmd.valueOf(strCmd.toUpperCase().trim());
		}
		catch (IllegalArgumentException e){
			return Cmd.UN;
		}
	}

	/**
	 * Handles add user. Checks if a user with name "username" already exists; 
	 * if exists the user is not registered. 
	 * @param username The user name.
	 * @return USER_ALREADY_EXISTS if the user already exists, 
	 * SUCCESS otherwise.
	 */
	private static ErrorType handleAddUser(String username) {
		if (VersionControlDb.addUser(username) != null) {
			return ErrorType.SUCCESS;
		}
		else {
			return ErrorType.USERNAME_ALREADY_EXISTS;
		}
	}

	/**
	 * Handles delete user. Checks if a user with name "username" exists; if 
	 * does not exist nothing is done. 
	 * @param username The user name.
	 * @return USER_NOT_FOUND if the user does not exists, SUCCESS otherwise.
	 */
	private static ErrorType handleDelUser(String username) {
		User user = VersionControlDb.findUser(username); 
		if (user == null) {
			return ErrorType.USER_NOT_FOUND;
		}
		else {
			VersionControlDb.delUser(user);
			return ErrorType.SUCCESS;
		}
	}

	/**
	 * Handles a user login. Checks if a user with name "username" exists; 
	 * if does not exist nothing is done; else the user is taken to the 
	 * user menu. 
	 * @param username The user name.
	 * @return USER_NOT_FOUND if the user does not exists, SUCCESS otherwise.
	 */
	private static ErrorType handleLogin(String username) {
		User currUser = VersionControlDb.findUser(username);
		if (currUser != null) {
			System.out.println(ErrorType.SUCCESS);
			processUserMenu(currUser);
			return ErrorType.SUCCESS;
		}
		else {
			return ErrorType.USER_NOT_FOUND;
		}
	}

	/**
	 * Processes the main menu commands.
	 * 
	 */
	public static void processMainMenu() {

		String mainPrompt = "[anon@root]: ";
		boolean execute = true;

		while (execute) {
			String[] words = prompt(mainPrompt);
			Cmd cmd = stringToCmd(words[0]);

			switch (cmd) {
			case AU:
				if (validateInput2(words)) {
					System.out.println(handleAddUser(words[1].trim()));
				}
				break;
			case DU:
				if (validateInput2(words)) {
					System.out.println(handleDelUser(words[1].trim())); 
				}
				break;
			case LI:
				if (validateInput2(words)) {
					System.out.println(handleLogin(words[1].trim()));
				}
				break;
			case HE:
				if (validateInput1(words)) {
					displayMainMenu();
				}
				break;
			case QU:
				if (validateInput1(words)) {
					execute = false;
				}
				break;
			default:
				System.out.println(ErrorType.UNKNOWN_COMMAND);
			}

		}
	}

	/**
	 * Processes the user menu commands for a logged in user.
	 * @param logInUser The logged in user.
	 * @throws IllegalArgumentException in case any argument is null.
	 */
	public static void processUserMenu(User logInUser) {

		if (logInUser == null) {
			throw new IllegalArgumentException();
		}

		String userPrompt = "[" + logInUser.getName() + "@root" + "]: ";
		boolean execute = true;

		while (execute) {

			String[] words = prompt(userPrompt);
			Cmd cmd = stringToCmd(words[0]);

			switch (cmd) {
			case AR:
				if (validateInput2(words)) {
					// TODO: Implement logic to handle AR.
					if (VersionControlDb.findRepo(words[1]) == null) {
						VersionControlDb.addRepo(words[1], logInUser);
						logInUser.subscribeRepo(words[1]);
						System.out.println(ErrorType.SUCCESS);
						//System.out.println(words[1] + " added");	
					}else{
						System.out.println(ErrorType.REPONAME_ALREADY_EXISTS);
					}
				}
				break;
			case DR:
				if (validateInput2(words)) {
					// TODO: Implement logic to handle DR.
					if (VersionControlDb.findRepo(words[1])==null) {
						System.out.println(ErrorType.REPO_NOT_FOUND);
					}else{
						if(VersionControlDb.findRepo(words[1]).getAdmin()
								!=logInUser){
							System.out.println(ErrorType.ACCESS_DENIED);
						}else{
							VersionControlDb.delRepo(
									VersionControlDb.findRepo(words[1]));
							System.out.println(ErrorType.SUCCESS);
						}
					}
				}
				break;
			case LR:
				if (validateInput1(words)) 
					// TODO: Implement logic to handle LR.
					System.out.println(logInUser.toString());
				break;
			case OR: //TODO not sure about this method/////////
				if (validateInput2(words)) {
					// TODO: Implement logic to handle OR.

					// report error if there is no such repo
					if (VersionControlDb.findRepo(words[1]) == null) {
						System.out.println(ErrorType.REPO_NOT_FOUND);
						// report error if user hasn't subscribe the repo  
					} else if (!logInUser.isSubRepo(words[1])){
						System.out.println(ErrorType.REPO_NOT_SUBSCRIBED);
						// else the user should be subscribed to that repo  
					} else {
						// TODO clearly wrong
						//logInUser.getWorkingCopy(words[1]);
						logInUser.checkOut(words[1]);
						System.out.println(ErrorType.SUCCESS);
						processRepoMenu(logInUser, words[1]);
					}

				}
				break;
			case LO:
				if (validateInput1(words)) {
					execute = false;
				}
				break;
			case HE:
				if (validateInput1(words)) {
					displayUserMenu();
				}
				break;
			default:
				System.out.println(ErrorType.UNKNOWN_COMMAND);
			}	// end of switch

		}
	}

	/**
	 * Process the repo menu commands for a logged in user and current
	 * working repository.
	 * @param logInUser The logged in user. 
	 * @param currRepo The current working repo.
	 * @throws IllegalArgumentException in case any argument is null.
	 */
	public static void processRepoMenu(User logInUser, String currRepo) {

		if (logInUser  == null || currRepo == null) {
			throw new IllegalArgumentException();
		}

		String repoPrompt = "["+ logInUser.getName() + "@" + currRepo + "]: ";
		boolean execute = true;


		// find the current repo according to the repo name  
		Repo curr = VersionControlDb.findRepo(currRepo);
		// get the working copy of the current repo 
		//RepoCopy working = logInUser.getWorkingCopy(currRepo);

		while (execute) {

			String[] words = prompt(repoPrompt);
			Cmd cmd = stringToCmd(words[0]);

			switch (cmd) {
			case SU:
				if (validateInput2(words)) {
					// TODO: Implement logic to handle SU.
					// if user not found 
					if(VersionControlDb.findUser(words[1]) == null){
						System.out.println(ErrorType.USER_NOT_FOUND);
						// if the user is not admin
					}else if(curr.getAdmin()!=logInUser){
						System.out.println(ErrorType.ACCESS_DENIED);
					}else{
						//TODO not sure about this
						VersionControlDb.findUser(words[1]).
						subscribeRepo(currRepo);
						System.out.println(ErrorType.SUCCESS);
					}
				}

				break;
			case LD:
				if (validateInput1(words)) {
					// TODO: Implement logic to handle LD.
					RepoCopy working = logInUser.getWorkingCopy(currRepo);
					// System.out.println(working.getDocuments());
					//logInUser.getWorkingCopy(currRepo);
					System.out.println(working.toString());
					// System.out.println("here");
					// System.out.println(working);
				}
				break;
			case ED:
				if (validateInput2(words)) {
					// TODO: Implement logic to handle ED.
					RepoCopy working = logInUser.getWorkingCopy(currRepo);
					if(working.getDoc(words[1])==null){
						System.out.println(ErrorType.DOC_NOT_FOUND);
					} else {
						Document doc = working.getDoc(words[1]);
						String ed = promptFileContent("Enter the file "
								+ "content and press q to quit: ");
						doc.setContent(ed);
						logInUser.addToPendingCheckIn(doc, Change.Type.EDIT, 
								currRepo);
						System.out.println(ErrorType.SUCCESS);
					}
				}					
				break;
			case AD:
				if (validateInput2(words)) {
					// TODO: Implement logic to handle AD.
					RepoCopy working = logInUser.getWorkingCopy(currRepo);
					if(working.getDoc(words[1]) != null){
						System.out.println(ErrorType.DOCNAME_ALREADY_EXISTS);
					} else {
						String content =  promptFileContent("Enter the "
								+ "file content and press q to quit: ");
						Document doc = new Document(words[1], content, 
								currRepo);
						working.addDoc(doc);
						logInUser.addToPendingCheckIn(doc, Change.Type.ADD, 
								currRepo);
						//System.out.println(logInUser.getPendingCheckIn
						//(currRepo));
						System.out.println(ErrorType.SUCCESS);
					}
				}
				break;
			case DD:
				if (validateInput2(words)) {
					// TODO: Implement logic to handle DD.
					RepoCopy working = logInUser.getWorkingCopy(currRepo);
					if(working.getDoc(words[1])==null){
						System.out.println(ErrorType.DOC_NOT_FOUND);
					}else{
						Document doc = working.getDoc(words[1]);
						working.delDoc(doc);
						logInUser.addToPendingCheckIn(doc, Change.Type.DEL,
								currRepo);
						System.out.println(ErrorType.SUCCESS);
					}
				}
				break;
			case VD:
				if (validateInput2(words)) {
					// TODO: Implement logic to handle VD.
					RepoCopy working = logInUser.getWorkingCopy(currRepo);
					if(working.getDoc(words[1])==null){
						System.out.println(ErrorType.DOC_NOT_FOUND);
					}else{
						Document doc = working.getDoc(words[1]);						
						System.out.println(doc);
					}
				}
				break;
			case CI:
				if (validateInput1(words)) {
					// TODO: Implement logic to handle CI.
					System.out.println(logInUser.checkIn(currRepo));
				}
				break;
			case CO:
				if (validateInput1(words)) {
					// TODO: Implement logic to handle CO.
					System.out.println(logInUser.checkOut(currRepo));
				}
				break;
			case RC:
				if (validateInput1(words)) {
					// TODO: Implement logic to handle RC.
					//TODO Change Set
					//System.out.println(curr.checkIns.size());
					ChangeSet temp = curr.getNextCheckIn(curr.getAdmin());
					if(temp==null){
						System.out.println(ErrorType.NO_PENDING_CHECKINS);
						break;
					}
					
					if(curr.getAdmin()!=logInUser){
						//System.out.println(curr.checkIns.size());
						System.out.println(ErrorType.ACCESS_DENIED);
						curr.queueCheckIn(temp);
						//System.out.println(curr.checkIns.size());
					}else{
						//System.out.println("else");
						//ChangeSet temp = curr.getNextCheckIn(logInUser);
						//if(temp==null){
						//	System.out.println(ErrorType.NO_PENDING_CHECKINS);
						//}else {
						System.out.println(temp.toString());
						// ask for approval  
						System.out.print("Approve changes? Press y to "
								+ "accept: ");
						String line = scnr.nextLine();
						if(line.equals("y")){
							curr.approveCheckIn(logInUser, temp);
						}
						ChangeSet checkins;								
						// loop over all checkins								
						for(int i = 0; i < curr.getCheckInCount(); i ++){
							// get the next checkin
							checkins = curr.getNextCheckIn(logInUser);
							System.out.println(checkins.toString());
							// ask for approval  
							System.out.print("Approve changes? Press y to"
									+ " accept: ");
							String line2 = scnr.nextLine();
							if(line2.equals("y")){
								curr.approveCheckIn(logInUser, checkins);
							}
						}
						System.out.println(ErrorType.SUCCESS);	
					}
				}
				//System.out.println(curr.checkIns.size());
				break;
			case VH:
				if (validateInput1(words)) {
					// TODO: Implement logic to handle VH.
					System.out.println(curr.getVersionHistory().toString());
					//System.out.println("");
				}
				break;
			case RE:	
				if (validateInput1(words)) {
					// TODO: Implement logic to handle RE.
					if(curr.getAdmin()!=logInUser){
						System.out.println(ErrorType.ACCESS_DENIED);
						//if the current version of repository is the oldest
						//version
					}else if(curr.getVersion() == 0){
						System.out.println(ErrorType.NO_OLDER_VERSION);
					}else{
						curr.revert(logInUser);
						System.out.println(ErrorType.SUCCESS);
					}
				}

				break;
			case HE:
				if (validateInput1(words)) {
					displayRepoMenu();
				}
				break;
			case QU:
				if (validateInput1(words)) {
					execute = false;
					System.out.println(ErrorType.SUCCESS);
				}
				break;
			default:
				System.out.println(ErrorType.UNKNOWN_COMMAND);
			}

		}
	}

	/**
	 * The main method. Simulation starts here.
	 * @param args Unused
	 */
	public static void main(String []args) {
		try {
			processMainMenu(); 
		}
		// Any exception thrown by the simulation is caught here.
		catch (Exception e) {
			System.out.println(ErrorType.INTERNAL_ERROR);
			// Uncomment this to print the stack trace for debugging purpose.
			//e.printStackTrace();
		}
		// Any clean up code goes here.
		finally {
			System.out.println("Quitting the simulation.");
		}
	}
}
