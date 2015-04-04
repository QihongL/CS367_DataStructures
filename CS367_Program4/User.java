import java.util.ArrayList;

public class User {

	private String name; //name of the user.
	private ArrayList<SimpleFile> files;//list of files owned/created by user
	private ArrayList<SimpleFolder> folders;//list of folder owned by user.

	public User(String name) {
		//TODO
		// init associated private fields
		if(name == null) throw new IllegalArgumentException();
		this.name = name;
		this.files = new ArrayList<SimpleFile>();
		this.folders = new ArrayList<SimpleFolder>();
	}

	/**
	 * Override the equals method of the object to compare two users on 
	 * name field. 
	 * @return true if names are the same, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		//TODO
		if(obj == null) throw new IllegalArgumentException();
		// convert the object to a user type
		User tempUser = (User) obj;	// TODO casting might be impossible!  
		// compare the name
		if(tempUser.getName() == this.name)
			return true;
		return false;
	}

	/**
	 * returns the name of the user.
	 * @return name
	 */
	public String getName() {
		//TODO
		return this.name;
	}


	/**
	 * returns the list of files owned by the user.
	 * @return files
	 */
	public ArrayList<SimpleFile> getFiles() {
		//TODO
		return this.files;
	}

	/**
	 * adds the file to the list of files owned by the user.
	 * @param newfile - the file you want to add
	 */
	public void addFile(SimpleFile newfile) {
		//TODO do we need to handle dupilication?
		if(newfile == null) throw new IllegalArgumentException();
		// if file does not exist
		//		if(!files.contains(newfile))	// TODO handle duplication
		this.files.add(newfile);
	} 

	/**
	 * Removes the file from the list of owned files of the user. 
	 * @param rmFile the file you want to remove 
	 * @return true if the delete was successful, false otherwise. 
	 */
	public boolean removeFile(SimpleFile rmFile){
		//TODO
		if(rmFile == null) throw new IllegalArgumentException();
		// if rmFile exists in the arraylist of files  
		if(files.contains(rmFile)){
			// remove it 
			files.remove(rmFile);
			return true;
		}
		return false;
	}

	/**
	 * returns the list of folders owned by the user.
	 * @return the array list of folders
	 */
	public ArrayList<SimpleFolder> getFolders() {
		//TODO
		return this.folders;
	}

	/**
	 * adds the folder to the list of folders owned by the user.
	 * @param newFolder - the folder you want to add
	 */
	public void addFolder(SimpleFolder newFolder) {
		//TODO do we need to handle dupilication?
		if(newFolder == null) throw new IllegalArgumentException();
		// if folder does not exist
		//		if(!folders.contains(newFolder))	// TODO handle duplication 
		this.folders.add(newFolder);
	}

	/**
	 * removes the folder from the list of folders owned by the user.
	 * @param rmFolder
	 * @return true is the removal was successful, false otherwise
	 */
	public boolean removeFolder(SimpleFolder rmFolder){
		//TODO
		if(rmFolder == null) throw new IllegalArgumentException();
		// if rmFolder exists in the arraylist of folders  
		if(folders.contains(rmFolder)){
			// remove it 
			folders.remove(rmFolder);
			return true;
		}
		return false;
	}

	/**
	 * returns the string representation of the user object.
	 * @return retType
	 */
	@Override
	public String toString() {
		String retType = name + "\n";
		retType = retType + "Folders owned :\n";
		for(SimpleFolder preFolder : folders){
			retType = retType + "\t" + preFolder.getPath() + "/" + preFolder.getName() + "\n";
		}
		retType = retType + "\nFiles owned :\n"; 
		for(SimpleFile preFile : files){
			retType = retType + "\t" + preFile.getPath() + "/" + preFile.getName() + "." + preFile.getExtension().toString() + "\n";
		}
		return retType;
	}



}
