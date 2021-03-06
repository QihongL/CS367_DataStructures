//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS

// Main Class File:  VersionControlApp.java
// File:             User.java
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
import java.util.*;

/**
 * Represents a user. Maintains the list of subscribed repositories, working
 * copy of the subscribed repositories and their changelist. 
 * @author
 *
 */
public class User {

	/* The name of the user. It's a unique identifier for a user. */
	private final String userName;

	/*  The list of names of the repositories to which the user is
	 *  subscribed. */
	private final List<String> subRepos;

	/* The list of all pending check-ins not yet made by the user. */
	private final List<ChangeSet> pendingCheckIns;

	/* The list of all local working copies of the user. */
	private final List<RepoCopy> workingCopies;

	/**
	 * Constructor for User. 
	 * @param username The user name.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public User(String userName) {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it in whatever way you like.
		if (userName == null) throw new IllegalArgumentException();
		this.userName = userName;
		this.subRepos = new ArrayList<String>();
		this.pendingCheckIns = new ArrayList<ChangeSet>();
		this.workingCopies = new ArrayList<RepoCopy>();
	}

	/**
	 * Returns the name of the user.
	 * @return the user name.
	 */
	public String getName() {
		return this.userName;
	}

	/**
	 * Returns a copy of list of subscribed repositories.
	 * @return The subscribed repo list.
	 */
	public List<String> getAllSubRepos() {
		return new ArrayList<String>(this.subRepos);
	}

	/**
	 * Returns the working copy for a repository.
	 * @param repoName The name of the repository.
	 * @return The working copy if exists, null otherwise.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public RepoCopy getWorkingCopy(String repoName) {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it in whatever way you like.
		if (repoName == null) throw new IllegalArgumentException();
		for (int i = workingCopies.size()-1; i >=0 ; i -- ){
			// if the reponame matches
			if(workingCopies.get(i).getReponame().equals(repoName)){
				// return that copy
				//System.out.println("get");
				//System.out.println(workingCopies.get(i).getVersion());
				return workingCopies.get(i);
			}
		}
		return null;
	}

	/**
	 * Subscribes the user to a repository. Adds a new repository to the 
	 * subscribed list. 
	 * @param repoName The name of the repository to subscribe.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public void subscribeRepo(String repoName) {

		if (repoName == null) {
			throw new IllegalArgumentException();
		}

		if (!this.subRepos.contains(repoName)) {
			this.subRepos.add(repoName); 
		}
	}

	/**
	 * Un-subscribes the user from a repository. Deletes a repository from 
	 * the subscribed list.
	 * @param repoName The name of the repository to unsubscribe. 
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public void unsubscribeRepo(String repoName) {
		if (repoName == null) {
			throw new IllegalArgumentException();
		}
		this.subRepos.remove(repoName);
	}

	/**
	 * Checks if the user is subscribed to a particular repository.
	 * @param repoName The name of the repository to subscribe.
	 * @return True if the repository is subscribed, false otherwise.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public boolean isSubRepo(String repoName) {
		if (repoName == null) {
			throw new IllegalArgumentException();
		}
		return subRepos.contains(repoName);
	}

	/**
	 * Adds a new change (add, edit or delete) to the pending checkIn for the 
	 * repository. If a checkIn does not exits, a new checkIn is
	 * created.
	 * @param doc The document added, deleted or edited.
	 * @param type The type of change.
	 * @param repoName The name of the repository on which the change is done.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public void addToPendingCheckIn(Document doc, 
			Change.Type type, String repoName) {
		// TODO: Implement this method. 
		//Change newChange = new Change(doc,type);
		if (doc == null || type == null || repoName == null) 
			throw new IllegalArgumentException();

		boolean changeAdded = false;
		for (int i = 0; i < pendingCheckIns.size(); i ++ ){
			// if the reponame matches
			if(pendingCheckIns.get(i).getReponame().equals(repoName)){
				// return that copy
				pendingCheckIns.get(i).addChange(doc, type);
				changeAdded = true;
				break;
			}
		}
		// if failed to find the corresponding repo...
		if(!changeAdded){
			ChangeSet tempPendingCheckIn = new ChangeSet(repoName);
			tempPendingCheckIn.addChange(doc, type);
			pendingCheckIns.add(tempPendingCheckIn);
		}
	}

	/**
	 * Returns the pending check-in for a repository.
	 * @param repoName The name of the repository.
	 * @return The pending check-in for the repository if exists, 
	 * null otherwise.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public ChangeSet getPendingCheckIn(String repoName) {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it in whatever way you like.
		if (repoName == null) throw new IllegalArgumentException();
		for (int i = 0; i < pendingCheckIns.size(); i ++ ){
			// if the reponame matches
			if(pendingCheckIns.get(i).getReponame().equals(repoName)){
				// return that copy
				return pendingCheckIns.get(i);
			}
		}
		return null;
	}

	/**
	 * Checks in or queues a pending checkIn into a repository and removes it
	 * from the local pending CheckIns list.   
	 * @param repoName The name of repository.
	 * @return NO_LOCAL_CHANGES, if there are no pending changes for the
	 * repository, SUCCESS otherwise.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public ErrorType checkIn(String repoName) {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it in whatever way you like.
		if (repoName == null) throw new IllegalArgumentException();
		if(getPendingCheckIn(repoName) == null) 
			return ErrorType.NO_LOCAL_CHANGES;
		ChangeSet tempSet = getPendingCheckIn(repoName);
		Repo temp = VersionControlDb.findRepo(repoName);
		temp.queueCheckIn(tempSet);
		pendingCheckIns.remove(tempSet);
		return ErrorType.SUCCESS;
	}

	/**
	 * Gets a latest version of the documents from the repository and puts
	 * them onto a working copy, if the user is currently subscribed to the
	 * repository. When the latest version is checked out, a new working copy 
	 * is created and existing one is deleted.
	 * @param repoName The name of the repository to check out.
	 * @return REPO_NOT_SUBSCRIBED if the repository is not subscribed, 
	 * SUCCESS otherwise. 
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public ErrorType checkOut (String repoName) {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it in whatever way you like.
		if (repoName == null) throw new IllegalArgumentException();
		Repo temp = VersionControlDb.findRepo(repoName);
		if (!isSubRepo(repoName)) return ErrorType.REPO_NOT_SUBSCRIBED;

		//1.deleting any old working copy for repoName in the workingCopies 
		//list,
		for(int i = 0; i<workingCopies.size(); i++){
			if(workingCopies.get(i).getReponame()==repoName)
				workingCopies.remove(i);
		}

		//2.deleting any pending check-in for repoName in the pendingCheckIns 
		//list,
		for(int i = 0; i<pendingCheckIns.size(); i++){
			if(pendingCheckIns.get(i).getReponame()==repoName)
				pendingCheckIns.remove(i);
		}
		//3.creating a new working copy (or RepoCopy object) for the current 		
		//version of repoName containing all its current documents and 
		//System.out.println(temp.getVersion());
		RepoCopy newOne = new RepoCopy(repoName, temp.getVersion(),
				temp.getDocuments());
		//4.adding the new working copy to the workingCopies list.

		workingCopies.add(newOne);

		return ErrorType.SUCCESS;
	}

	@Override
	public String toString() {
		String str = "=================================== \n";
		str += "Username: " + this.userName + "\n"
				+ "-----------Repos------------------ \n";
		int count = 0;
		for (String r : this.subRepos) {
			str += ++count + ". " + r + "\n";
		}
		str += this.subRepos.size() + " repos(s) subscribed.\n"
				+ "===================================";
		return str;
	}
}
