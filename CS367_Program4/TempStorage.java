
public class TempStorage {

	/** 4/8 rewrite the moveLoc method! */
//	/**
//	 * Changes the current location as per the argument. 
//	 * Argument can be absolute/relative path. 
//	 * @param argument
//	 * @return true if successful, false otherwise.
//	 */
//	public boolean moveLoc(String argument){
//		//TODO
//		if(argument == null) throw new IllegalArgumentException();
//		// memorize the location before movement
//		SimpleFolder tempCurrLoc = currLoc;// TODO move TF judgment to the last!
//		
//		// handle absolute path - incomplete
//		if(argument.equals("")){
////			System.out.println("ABSOLUTE PATH <"+ argument +">");	//TODO
//			// start from the root 
//			currLoc = root;
//			// split the path into folders
//			String [] folderSeq = argument.split("/"); 
//			// move to the target folder
//			for (int i = 1; i < folderSeq.length; i ++){
//				// if there is a match in the subFolders of the currLoc
//				if(containsFileFolder(folderSeq[i]))
//					// update the current location by going down the tree
//					currLoc = currLoc.getSubFolder(folderSeq[i]);
//			}
//			return true;
//
//			// handle relative path - incomplete
//		} else if (containsFileFolder(argument)){
//			String [] folderSeq = argument.split("/");
////			System.out.println("RELATIVE PATH <"+ argument +">");//TODO
//
//			currLoc = currLoc.getSubFolder(argument);
//
//			//System.out.println("*" + currLoc); //TODO
//
////			System.out.println(currLoc.getName() +" HAS PATH:" + currLoc.getPath());//TODO
//
//			return true;
//		}
//		System.out.println("Invalid location passed");
//		return false;
//	}
	
	
	
	
//	
//	private boolean folderExist(String foldername){
//		// check if the folder exist
//		for(int i = 0; i < users.size(); i ++){
//			ArrayList<SimpleFolder> tempFolders = users.get(i).getFolders(); 
//			for(int j = 0; j < tempFolders.size(); j ++){
//				if(tempFolders.get(j).getName().equals(foldername))
//					return true;
//			}
//		}
//		return false;
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
