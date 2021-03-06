//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FileSystemMain.java
// File:             SimpleFolder.java
// Semester:         CS367 Spring 2015
//
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//
//////////////////////////// 80 columns wide /////////////////////////////////
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The SimpleFolder class represents a single folder in the file system having 
 * a name (as a String), path (as a String), parent (as a SimpleFolder), 
 * owner (as a User), subFolders (as a list of SimpleFolder), files 
 * (as a list of SimpleFile) and accesses (as a list of Access).
 * @author Qihong
 */
public class SimpleFolder {

	private String name;
	private String path;//absolute path of the folder.
	private SimpleFolder parent;
	private User owner;
	private ArrayList<SimpleFolder> subFolders;
	private ArrayList<SimpleFile> files;
	private ArrayList<Access> allowedUsers;

	/**
	 * The constructor of the SimpleFolder class. It makes an instance of 
	 * SampleFolder type.
	 * @param name
	 * @param path
	 * @param parent
	 * @param owner
	 */
	public SimpleFolder(String name, String path, SimpleFolder parent, 
			User owner) {
		// input validation 
		if(name == null || path == null || owner == null)
			throw new IllegalArgumentException();
		// initialize the constructor fields
		this.name = name;
		this.path = path;
		this.parent = parent;
		this.owner = owner;
		this.subFolders = new ArrayList<SimpleFolder>();
		this.files = new ArrayList<SimpleFile>();
		this.allowedUsers = new ArrayList<Access>();
		// give the owner w access
		Access newAccess = new Access(owner, 'w');
		addAllowedUser(newAccess);
	}
	
	
	/**
	 * checks if user - "name" is allowed to access this folder or not.
	 * 
	 * @param name
	 * @return return true if the user is allowed, false otherwise.
	 */
	public boolean containsAllowedUser(String name){
		if(name == null) throw new IllegalArgumentException();
		// loop over all allowed users
		Iterator<Access> itr = allowedUsers.iterator();
		while(itr.hasNext()){
			Access curAccess = itr.next();
			// if a match is found
			if(curAccess.getUser().getName().equals(name))
				return true;
		}
		return false;
	}

	/**
	 * checks if this folder contains the child folder identified by 'name'.
	 * @param name - the name of the folder
	 * @return the folder in accordance to the name if exist, null otherwise.
	 */
	public SimpleFolder getSubFolder(String name){
		if(name == null) throw new IllegalArgumentException();
		// loop over subFolders
		Iterator<SimpleFolder> itr = subFolders.iterator();
		while(itr.hasNext()){
			SimpleFolder tempFolder = itr.next();
			// if a match on name is found 
			if(tempFolder.getName().equals(name)){
				// return that folder 
				return tempFolder;
			}
		}
		return null;
	}


	/**
	 * checks if this folder contains the child file identified by "name".
	 * @param fname - the name of the file 
	 * @return return File if it contain, otherwise null. 
	 */
	public SimpleFile getFile(String fname){
		if(fname == null) throw new IllegalArgumentException();
		// loop over all files
		Iterator<SimpleFile> itr = files.iterator();
		while(itr.hasNext()){
			SimpleFile tempFile = itr.next();
			// if a match on name is found 
			if(tempFile.getName().equals(fname)){
				// return that file 
				return tempFile;
			}
		}
		return null;
	}


	/**
	 * returns the owner of the folder.
	 * @return owner 
	 */
	public User getOwner() {
		return this.owner;
	}

	/**
	 * returns the name of the folder.
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * returns the path of this folder.
	 * @return path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * returns the Parent folder of this folder.
	 * @return parent
	 */
	public SimpleFolder getParent() {
		return this.parent;
	}

	/**
	 * returns the list of all folders contained in this folder.
	 * @return subFolders
	 */
	public ArrayList<SimpleFolder> getSubFolders() {
		return this.subFolders;
	}

	/**
	 * adds a folder into this folder.
	 * @param subFolder
	 */
	public void addSubFolder(SimpleFolder subFolder) {
		if(subFolder == null) throw new IllegalArgumentException();
		// if the folder doesn't exists already 
		if(getSubFolder(subFolder.getName()) == null)
			subFolders.add(subFolder);
	}

	/**
	 * returns the list of files contained in this folder.
	 * @return files - the list of files 
	 */
	public ArrayList<SimpleFile> getFiles() {
		return files;
	}

	/**
	 * add the file to the list of files contained in this folder.
	 * @param file
	 */
	public void addFile(SimpleFile file) {
		if(file == null) throw new IllegalArgumentException();
		// add the file
		files.add(file);
	}

	/**
	 * returns the list of allowed user to this folder.
	 * @return allowedUsers
	 */
	public ArrayList<Access> getAllowedUsers() {
		return allowedUsers;
	}

	/**
	 * adds another user to the list of allowed user of this folder.
	 * @param allowedUser
	 */
	public void addAllowedUser(Access allowedUser) {
		if(allowedUser == null) throw new IllegalArgumentException();
		// add if the user in not in the list yet
		if(!containsAllowedUser(allowedUser.getUser().getName())){
			allowedUsers.add(allowedUser);
		}
	}

	/**
	 * adds a list of allowed user to this folder.
	 * @param allowedUser
	 */
	public void addAllowedUser(ArrayList<Access> allowedUser) {
		if(allowedUser == null) throw new IllegalArgumentException();
		// loop over all allowedUsers that you want to add
		Iterator<Access> itr = allowedUser.iterator();
		while(itr.hasNext()){
			// add user 
			addAllowedUser(itr.next());
		}
	}

	/**
	 * If the user is owner of this folder or the user is admin or the user 
	 * has 'w' privilege, then delete this folder along with all its content.
	 * @param removeUsr
	 * @return true if deleted, false otherwise.
	 */
	public boolean removeFolder(User removeUsr){
		if(removeUsr == null) throw new IllegalArgumentException();
		// if the user has the privilege
		if(removeUsr.equals(owner) || removeUsr.getName().equals("admin") || 
				hasPermission(removeUsr, 'w')){			
			// remove the file
			parent.getSubFolders().remove(this);
			return true;
		} 
		return false;
	}

	/**
	 * This method check if the user has a particular type of permission
	 * @param user
	 * @param accessType
	 * @return true if the user has the permission, false otherwise.
	 */
	private boolean hasPermission(User user, char accessType){
		Iterator<Access> itr = allowedUsers.iterator();
		// traverse through all allowedUsers
		while(itr.hasNext()){
			Access cur = itr.next();
			// if user is in the allowed list 
			if(cur.getUser().equals(user)){
				// if the user also has the right access type
				if( cur.getAccessType() == accessType){
					return true;
				}
			}
		}// end of the while
		return false;
	}
	

	/**
	 * returns the string representation of the Folder object.
	 */
	@Override
	public String toString() {
		String retString = "";
		retString = path + "/" + name + "\t" + owner.getName() + "\t";
		for(Access preAccess: allowedUsers){
			retString = retString + preAccess + " ";
		}

		retString = retString + "\nFILES:\n";

		for(int i=0;i<files.size();i++){
			retString = retString + files.get(i);
			if(i != files.size()-1)
				retString = retString + "\n";

		}				
		return retString;
	}


}
