import java.util.ArrayList;
import java.util.Iterator;


public class SimpleFileSystem {

	SimpleFolder root;
	ArrayList<User> users;
	SimpleFolder currLoc;
	User currUser;

	/**
	 * Constructor - Initializes the class variables appropriately.
	 * @param _root
	 * @param _users
	 */
	public SimpleFileSystem(SimpleFolder _root, ArrayList<User> _users) {
		//TODO
		if(_root == null || _users == null) 
			throw new IllegalArgumentException();
		this.root = _root;
		this.users = _users;
		reset();
	}

	/**
	 * Resets current location to root and current user to admin. 
	 */
	public void reset(){
		//TODO
		// set the current user to admin
		setCurrentUser("admin");
		// go back to the root dir
		moveLoc(root.getPath());
	}


	/**
	 * gets currUser.
	 * @return currUser
	 */
	public User getCurrUser() {
		//TODO
		return currUser;
	}

	/**
	 * Sets the current user to the user with name passed in the argument. 
	 * Also, note that when a user is set, current location points to root.
	 * @param name
	 * @return If no such user found, return false, otherwise return true.  
	 */
	public boolean setCurrentUser(String name){
		//TODO 
		if(name == null) throw new IllegalArgumentException();
		// if a user with the input name cannot be found 
		if(containsUser(name) != null){
			// set the current user 
			currUser = containsUser(name);
			currLoc = root;
			return true;
		}
		return false;
	}


	//
	/**
	 * checks if the user is contained in the existing users list or not.
	 * @param name
	 * @return the user object if a match is found; null otherwise 
	 */
	public User containsUser(String name){
		//TODO
		if(name == null) throw new IllegalArgumentException();
		// loop over all users
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()){
			User curUser = itr.next();
			// if a name match is found, return that user
			if(curUser.getName().equals(name))
				return curUser; 
		}
		// return null if user with the input name does not exist 
		return null;
	}


	/**
	 * checks whether curr location contains 
	 * any file/folder with name name = fname
	 * @param fname
	 * @return true if contains, false otherwise
	 */
	public boolean containsFileFolder(String fname){
		//TODO
		if(fname == null) throw new IllegalArgumentException();
		// if there is no file or folders with this name in the curreLoc...
		if(currLoc.getSubFolder(fname) == null 
				&& currLoc.getFile(fname) == null ){
			// ... implies doesn't contains
			return false;
		}
		return true;
	}



	/**
	 * Changes the current location as per the argument. 
	 * Argument can be absolute/relative path. 
	 * @param argument
	 * @return true if successful, false otherwise.
	 */
	public boolean moveLoc(String argument){
		//TODO
		if(argument == null) throw new IllegalArgumentException();
		// memorize the location before movement
		SimpleFolder tempCurrLoc = currLoc;// TODO move TF judgment to the last!

		// handle absolute path - incomplete
		if(argument.charAt(0) == '/'){
			System.out.println("ABSOLUTE PATH <"+ argument +">");	//TODO
			// start from the root 
			currLoc = root;
			// split the path into folders
			String [] folderSeq = argument.split("/"); 
			// move to the target folder
			for (int i = 1; i < folderSeq.length; i ++){
				// if there is a match in the subFolders of the currLoc
				if(containsFileFolder(folderSeq[i]))
					// update the current location by going down the tree
					currLoc = currLoc.getSubFolder(folderSeq[i]);
			}
			return true;

			// handle relative path - incomplete
		} else if (containsFileFolder(argument)){
			String [] folderSeq = argument.split("/");
			System.out.println("RELATIVE PATH <"+ argument +">");//TODO

			currLoc = currLoc.getSubFolder(argument);

			//System.out.println("*" + currLoc); //TODO

			System.out.println(currLoc.getName() +" HAS PATH:" + currLoc.getPath());//TODO

			return true;
		}
		System.out.println("Invalid location passed");
		return false;
	}

	private boolean folderExist(String foldername){
		// check if the folder exist
		for(int i = 0; i < users.size(); i ++){
			ArrayList<SimpleFolder> tempFolders = users.get(i).getFolders(); 
			for(int j = 0; j < tempFolders.size(); j ++){
				if(tempFolders.get(j).getName().equals(foldername))
					return true;
			}
		}
		return false;
	}


	/**
	 * returns the currentlocation.path + currentlocation.name.
	 * @return the string representation of the current path
	 */
	public String getPWD(){
		//TODO
		return currLoc.getPath();
	}


	/**
	 * deletes the folder/file identified by the 'name'
	 * @param name
	 * @return true if the removal is successful, false otherwise.
	 */
	public boolean remove(String name){
		//TODO
		if(name == null) throw new IllegalArgumentException();
		// remove 

		return false;
	}


	/**
	 * Gives the access 'permission' of the file/folder fname to the user if 
	 * the current user is the owner of the fname.   
	 * @param fname
	 * @param username
	 * @param permission
	 * @return true if add is successful, false otherwise.
	 */
	public boolean addUser(String fname, String username, char permission){
		//TODO
		if(fname == null || username == null || 
				(permission != 'w' && permission != 'r')) 
			throw new IllegalArgumentException();
		// if there exist such a file or folder 
		if(containsFileFolder(fname)){
			// if the target is not a file, it must be a folder 
			if(currLoc.getFile(fname) == null){
				// if the current user is the owner
				if(currLoc.getSubFolder(fname).getOwner().equals(currUser)){
					// give the permission
					// TODO
				}
				// ... otherwise the target is a file 
			} else {
				// if the current user is the owner
				if(currLoc.getFile(fname).getOwner().equals(currUser)){
					// give the permission
					// TODO
				}	
			}
			return true;
		}
		return false;
	}


	/**
	 * Displays the user info if the current user is admin. 
	 * @return true if successful, otherwise false.
	 */
	public boolean printUsersInfo(){
		//TODO
		if(currUser.getName().equals("admin")){
			// print user info 
			System.out.println("admin");
			System.out.println("Folders owned :");


			// TODO TESTING PRINT
			System.out.println(root.getSubFolders().size() + " FOLDERS FOUND!");
			for(int i = 0; i < root.getSubFolders().size(); i ++){
				System.out.println(root.getSubFolders().get(i).getName());
			}
			
			
			return true;
		} 
		// return msg if the current user is not admin 
		System.out.println("Insufficient privileges");
		return false;
	}
	
	


	/**
	 * makes a new folder under the current folder with owner = current user.
	 * @param name
	 */
	public void mkdir(String name){
		//TODO
		if(name == null) throw new IllegalArgumentException();
		// if the name doesn't conflict with any files or folders
		if(!containsFileFolder(name)){
			// create and add the folder 
			SimpleFolder newfolder = new SimpleFolder(name, getPWD(), 
					currLoc, currUser);
			currLoc.addSubFolder(newfolder);
		}

	}


	/**
	 * Makes a new file with name=filename and content=filecontent under 
	 * the current folder with owner = current user.
	 * @param filename
	 * @param fileContent
	 */
	public void addFile(String filename, String fileContent){
		//TODO
		if(filename == null || fileContent == null)  
			throw new IllegalArgumentException();
		// read the information 
		String [] filenames = filename.split("\\.");
		if(filenames.length == 2){
			String fname = filenames[0];
			Extension extension = Extension.valueOf(filenames[1]);
			// if the filename doesn't conflict with other folder or file name
			if(!containsFileFolder(fname)){	// TODO not sure if we need withExtName
				// create and add the file  
				SimpleFile newFile = new SimpleFile(fname, extension, getPWD(), 
						fileContent, currLoc, currUser);
				currLoc.addFile(newFile);

				// TODO testing print
				//				System.out.println("FILENAME:" + fname);
				//				System.out.println("EXTENSION:" + extension);
				//				System.out.println("PATH:" + getPWD());
				//				System.out.println("CONTENT:" + fileContent);
				//				System.out.println("PARENT:" + currLoc.getName());
				//				System.out.println("OWNER:" + currUser.getName());
				//				System.out.println();

			}
		}
	}


	/**
	 * prints all the folders and files under the current user for which user 
	 * has access.
	 */
	public void printAll(){
		for(SimpleFile f : currLoc.getFiles()){
			if(f.containsAllowedUser(currUser.getName()))
			{
				System.out.print(f.getName() + "." + f.getExtension().toString() + " : " + f.getOwner().getName() + " : ");
				for(int i =0; i<f.getAllowedUsers().size(); i++){
					Access a = f.getAllowedUsers().get(i);
					System.out.print("("+a.getUser().getName() + "," + a.getAccessType() + ")");
					if(i<f.getAllowedUsers().size()-1){
						System.out.print(",");
					}
				}
				System.out.println();
			}
		}
		for(SimpleFolder f: currLoc.getSubFolders()){
			if(f.containsAllowedUser(currUser.getName()))
			{
				System.out.print(f.getName() + " : " + f.getOwner().getName() + " : ");
				for(int i =0; i<f.getAllowedUsers().size(); i++){
					Access a = f.getAllowedUsers().get(i);
					System.out.print("("+a.getUser().getName() + "," + a.getAccessType() + ")");
					if(i<f.getAllowedUsers().size()-1){
						System.out.print(",");
					}
				}
				System.out.println();
			}
		}
	}

}