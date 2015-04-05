import java.util.ArrayList;
import java.util.Iterator;

public class SimpleFile {
	private String name;
	private Extension extension;
	private String content;
	private User owner;
	private ArrayList<Access> allowedUsers;
	private String path;
	private SimpleFolder parent;

	public SimpleFile(String name, Extension extension, String path, 
			String content, SimpleFolder parent, User owner) {
		//TODO
		if(name == null || extension == null || path == null || 
				content == null || parent == null || owner == null)
			throw new IllegalArgumentException();
		this.name = name;
		this.extension = extension;
		this.path = path;
		this.content = content;
		this.parent = parent;
		this.owner = owner;

		this.allowedUsers = new ArrayList<Access>();
		// give the owner w access
		Access newAccess = new Access(owner, 'w');
		addAllowedUser(newAccess);
	}

	/**
	 * returns the path variable.
	 * @return path
	 */
	public String getPath(){
		//TODO
		return this.path;
	}

	/**
	 * return the parent folder of this file.
	 * @return parent 
	 */
	public SimpleFolder getParent() {
		//TODO
		return this.parent;
	}

	/**
	 * returns the name of the file.
	 * @return name - the name of the file  
	 */
	public String getName() {
		//TODO
		return this.name;
	}

	/**
	 * returns the extension of the file.
	 * @return extension 
	 */
	public Extension getExtension() {
		//TODO
		return this.extension;
	}

	/**
	 * returns the content of the file.
	 * @return content - the content of the file 
	 */
	public String getContent() {
		//TODO
		return this.content;
	}

	/**
	 * returns the owner user of this file.
	 * @return owner
	 */
	public User getOwner() {
		//TODO
		return this.owner;
	}

	/**
	 * returns the list of allowed user of this file.
	 * @return allowedUsers
	 */
	public ArrayList<Access> getAllowedUsers() {
		//TODO
		return this.allowedUsers;
	}

	/**
	 * adds a new user to the list of allowed user.
	 * @param newAllowedUser
	 */
	public void addAllowedUser(Access newAllowedUser) {
		//TODO
		if(newAllowedUser == null) throw new IllegalArgumentException();
		// add if the user is not in the list yet 
		if(!containsAllowedUser(newAllowedUser.getUser().getName())){
			allowedUsers.add(newAllowedUser);
		}
	}

	/**
	 * adds a list of the users to the list of allowed users.
	 * @param newAllowedUser
	 */
	public void addAllowedUsers(ArrayList<Access> newAllowedUser) {
		//TODO
		if(newAllowedUser == null) throw new IllegalArgumentException();
		// loop over all allowed users
		Iterator<Access> itr = newAllowedUser.iterator();
		while(itr.hasNext()){
			// add the user
			addAllowedUser(itr.next());
		}
	}


	/**
	 * Determine if the user in the allowed-list 
	 * 
	 * @param name - the name of the user
	 * @return true if the user name is in allowedUsers, false otherwise
	 */
	public boolean containsAllowedUser(String name){
		//TODO
		if(name == null) throw new IllegalArgumentException();		
		// traverse through all allowedUsers
		Iterator<Access> itr = allowedUsers.iterator();
		while(itr.hasNext()){
			Access curAccess = itr.next();
			// return true if a name match is found
			if(curAccess.getUser().getName().equals(name))
				return true;
		}
		// return false otherwise 
		return false;
	}


	/**
	 * removes the file for all users.
	 * If the user is owner of the file or the admin or the user has 'w' 
	 * access, then it is removed for everybody.
	 * @param removeUsr
	 * @return true if the removal was successful, false otherwise
	 */
	public boolean removeFile(User removeUsr){
		if(removeUsr == null) throw new IllegalArgumentException();

		boolean removeFromAll = false;
		if(removeUsr.equals(owner) || removeUsr.getName().equals("admin")){
			removeFromAll = true;
		} else {
			Iterator<Access> itr = allowedUsers.iterator();
			// traverse through all allowedUsers
			while(itr.hasNext()){
				Access curAccess = itr.next();
				// if the removeUsr is in allowed-list && the removeUsr has W access
				if(curAccess.getUser().equals(removeUsr) 
						&& curAccess.getAccessType() == 'w'){
					removeFromAll = true;
				}
			} // end of while
		}

		if(removeFromAll){
			//TODO remove the file from everybody 
			//TODO how? and what if the removeUsr is not admin?
			
			return true;
		} 
		return false;
	}


	/**
	 * returns the string representation of the file.
	 * @return retString  
	 */
	@Override
	public String toString() {
		String retString = "";
		retString = name + "." + extension.name() + "\t" + owner.getName() + "\t" ;
		for(Access preAccess : allowedUsers){
			retString = retString + preAccess + " ";
		}
		retString = retString + "\t\"" + content + "\"";
		return retString;
	}

}