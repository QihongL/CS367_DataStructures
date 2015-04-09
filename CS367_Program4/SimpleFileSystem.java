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
		setCurrentUser("admin");
		//		System.out.println("*" + currUser.getName());
		//		System.out.println("*" + root.getPath());
		//		System.out.println("*" + currLoc.getPath());
		//		System.out.println("*" + getPWD());
	}

	/**
	 * Resets current location to root and current user to admin. 
	 */
	public void reset(){
		//TODO
		// set the current user to admin
		setCurrentUser("admin");
		// go back to the root dir
		currLoc = root;		
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
		// split the input command into a array of folder names 
		String [] pathSeq = argument.split("/");
		
		// CASE 1: if the 1st folder is the root, it is absolute path
		if(pathSeq[0].equals(root.getName())){
			// start from the root
			currLoc = root;
			// complete all the remaining movements in the path sequence
			for(int i = 1; i < pathSeq.length; i ++){
				//			System.out.println("***<" + currLoc.getName()   + "> has the folder "
				//					+ "<" + pathSeq[i] + ">: " + containsFileFolder(pathSeq[i]) + "! ");
				if(containsFileFolder(pathSeq[i])){
					currLoc = currLoc.getSubFolder(pathSeq[i]);
				}
			}
		} else if (containsFileFolder(pathSeq[0])){
			// CASE 2: need to move down (relative path)
			for(int i = 0; i < pathSeq.length; i ++){
				System.out.println("***<" + currLoc.getName()   + "> has the folder "
						+ "<" + pathSeq[i] + ">: " + containsFileFolder(pathSeq[i]) + "! ");
				if(containsFileFolder(pathSeq[i])){
					currLoc = currLoc.getSubFolder(pathSeq[i]);
				}
			}

		} else if (pathSeq[0].equals("..")){
			// CASE 2: need to move up (relative path)

		}


		// check if the currLoc matches the last folder name in the argument
		if(currLoc.getName().equals(pathSeq[pathSeq.length-1])){
			//			System.out.println("T: The initial location <" + currLoc.getName() 
			//					+"> == <" +pathSeq[pathSeq.length-1] + ">");
			return true;
		} else {
			//			System.out.println("F: The initial location <" + currLoc.getName() 
			//					+"> != <" +pathSeq[pathSeq.length-1] + ">");
			return false;
		}
	}



	/**
	 * Return the corrent working directory
	 * @return the currentlocation.path + currentlocation.name.
	 */
	public String getPWD(){
		return ((currLoc.getPath().isEmpty()?"":(currLoc.getPath()+"/"))
				+currLoc.getName());
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
		//TODO check correctness 
		if(fname == null || username == null || 
				(permission != 'w' && permission != 'r')) 
			throw new IllegalArgumentException();
		// if there exist such a file or folder 
		if(containsFileFolder(fname) && containsUser(username)!= null ){
			// if the target is not a file, it must be a folder 
			if(currLoc.getFile(fname) == null){
				// if the current user is the owner
				if(currLoc.getSubFolder(fname).getOwner().equals(currUser)){
					// give the permission
					Access newAccess = new Access(containsUser(username), permission);
					currLoc.getSubFolder(fname).addAllowedUser(newAccess);
					//					System.out.println("In addUser:");
					//					System.out.println("*" + containsUser(username).getName() + 
					//							" WAS GIVEN PERMIT TO " + fname + " !!!");	//TODO
					return true;
				}
				// ... otherwise the target is a file 
			} else {
				// if the current user is the owner
				if(currLoc.getFile(fname).getOwner().equals(currUser)){
					// give the permission
					Access newAccess = new Access(containsUser(username), permission);
					currLoc.getFile(fname).addAllowedUser(newAccess);
					//					System.out.println("*" + containsUser(username).getName() + 
					//							" WAS GIVEN PERMIT TO " + fname + " !!!");	//TODO
					return true;
				}	
			}
		}// if the code reaches here simply return false
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
			// TODO we need recursive alg to print folders 
			//			System.out.println(root.getSubFolders().size() + " FOLDERS FOUND!");
			//			for(int i = 0; i < root.getSubFolders().size(); i ++){
			//				System.out.println(root.getSubFolders().get(i).getName());
			//			}
			System.out.println("------------------");
			for(int i = 1; i < users.size(); i ++){
				System.out.println(users.get(i));
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
			currUser.addFolder(newfolder);
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
				currUser.addFile(newFile);

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
