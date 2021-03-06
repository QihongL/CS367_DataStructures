import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a repository which stores and tracks changes to a collection of 
 * documents.
 * @author
 *
 */
public class Repo {

	/* The current version of the repo. */
	private int version;

	/* The name of the repo. It's a unique identifier for a repository. */
	private final String repoName;

	/* The user who is the administrator of the repo. */
	private final User admin;

	/* The collection(list) of documents in the repo. */
	private final List<Document> docs;

	/* The check-ins queued by different users for admin approval. */
	private final QueueADT<ChangeSet> checkIns;

	/* The stack of copies of the repo at points when any check-in was applied. */
	private final StackADT<RepoCopy> versionRecords; 

	/**
	 * Constructs a repo object.
	 * @param admin The administrator for the repo.
	 * @param reponame The name of the repo.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public Repo(User admin, String repoName) {
		// TODO: Implement this contructor. The following lines 
		// are just meant for the method to compile. You should 
		// remove or edit it in whatever way you like.
		this.admin = admin;
		this.repoName =  repoName;
		this.docs = new ArrayList<Document>();
		this.checkIns =  new SimpleQueue<ChangeSet>();
		this.versionRecords =  new SimpleStack<RepoCopy>();
	}

	/**
	 * Return the name of the repo.
	 * @return The name of the repository.
	 */
	public String getName() {
		return this.repoName;
	}

	/**
	 * Returns the user who is administrator for this repository.
	 * @return The admin user.
	 */
	public User getAdmin() {
		return this.admin;
	}

	/**
	 * Returns a copy of list of all documents in the repository.
	 * @return A list of documents.
	 */
	public List<Document> getDocuments() {
		return new ArrayList<Document>(this.docs);
	}

	/**
	 * Returns a document with a particular name within the repository.
	 * @param searchName The name of document to be searched.
	 * @return The document if found, null otherwise.
	 * @throws IllegalArgumentException if any argument is null.
	 */
	public Document getDocument(String searchName) {
		if (searchName == null) {
			throw new IllegalArgumentException();
		}

		for (Document d : this.docs) {
			if (d.getName().equals(searchName)) {
				return d;
			}
		}

		return null;
	}

	/**
	 * Returns the current version of the repository.
	 * @return The version of the repository.
	 */
	public int getVersion() {
		return this.version;
	}

	/**
	 * Returns the number of versions (or changes made) for this repository.
	 * @return The version count.
	 */
	public int getVersionCount() {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it in whatever way you like.
		return versionRecords.size();
	}

	/**
	 * Returns the history of changes made to the repository. 
	 * @return The string containing the history of changes.
	 */
	public String getVersionHistory() {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it in whatever way you like.
		String history = "";
		SimpleStack<RepoCopy> tempStack = new SimpleStack<RepoCopy>();
		while(!versionRecords.isEmpty()){
			try {
				// get the historical record
				history += versionRecords.peek();
				// push the current item to the temporary stack
				tempStack.push(versionRecords.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			} 
		}
		// restore the versionRecord stack
		while(!tempStack.isEmpty())	versionRecords.push(tempStack.pop());
		return history;
	}

	/**
	 * Returns the number of pending check-ins queued for approval.
	 * @return The count of changes.
	 */
	public int getCheckInCount() {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it in whatever way you like.
		return checkIns.size();
	}


	/**
	 * Queue a new check-in for admin approval.
	 * @param checkIn The check-in to be queued.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public void queueCheckIn(ChangeSet checkIn) {
		// TODO: Implement this method.
		if (checkIn == null) throw new IllegalArgumentException();
		checkIns.enqueue(checkIn);
	}

	/**
	 * Returns and removes the next check-in in the queue 
	 * if the requesting user is the administrator.
	 * @param requestingUser The user requesting for the change set.
	 * @return The checkin if the requestingUser is the admin and a checkin
	 * exists, null otherwise.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public ChangeSet getNextCheckIn(User requestingUser) {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it in whatever way you like.
		if(requestingUser == null) throw new IllegalArgumentException();
		ChangeSet nextItem = null;
		// if the requester is the admin
		if(requestingUser.equals(admin)){
			try {
				// get the next item in the queue
				nextItem = checkIns.dequeue();
			} catch (EmptyQueueException e) {
				e.printStackTrace();
			}
		}
		return nextItem;
	}

	/**
	 * Applies the changes contained in a particular checkIn and adds
	 * it to the repository if the requesting user is the administrator.
	 * Also saves a copy of changed repository in the versionRecords.
	 * @param requestingUser The user requesting the approval.
	 * @param checkIn The checkIn to approve.
	 * @return ACCESS_DENIED if requestingUser is not the admin, SUCCESS 
	 * otherwise.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public ErrorType approveCheckIn(User requestingUser, ChangeSet checkIn) {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it whatever way you like.
		if (requestingUser == null || checkIn == null)
			throw new IllegalArgumentException();
		// if the requester is the admin
		if(requestingUser.equals(admin)){
			// TODO not sure about the procedure
			// apply the changes in the "checkIn"
			// add it to the repo
			// save a copy 
			// asdasasd
		}
		return null;
	}

	/**
	 * Reverts the repository to the previous version if present version is
	 * not the oldest version and the requesting user is the administrator.
	 * @param requestingUser The user requesting the revert.
	 * @return ACCESS_DENIED if requestingUser is not the admin, 
	 * NO_OLDER_VERSION if the present version is the oldest version, SUCCESS 
	 * otherwise.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public ErrorType revert(User requestingUser) {
		// TODO: Implement this method. The following lines 
		// are just meant for the method to compile. You can 
		// remove or edit it whatever way you like.
		if(requestingUser == null) throw new IllegalArgumentException();
		// if the requester is the admin
		if(!requestingUser.equals(admin)){
			return ErrorType.ACCESS_DENIED;
		} else {
			// if the current version if the oldest version...
			if(getVersionCount() == 0) return ErrorType.NO_OLDER_VERSION;
			// return success otherwise!
			else return ErrorType.SUCCESS;
			
		}
	}
}
