import java.util.ArrayList;


public class GeneralTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
//		String temp = "testing.txt";
//		
//		User admin = new User ("admin");
//		User qihong = new User ("qihong");
//		ArrayList<User> users = new ArrayList<User>();
//		users.add(admin); users.add(qihong);
//		
//		SimpleFolder root = new SimpleFolder("test", "", null, admin);
//		
//		SimpleFileSystem system = new SimpleFileSystem(root, users);
//		
//		SimpleFile file = new SimpleFile("testing", Extension.valueOf("txt"), 
//				"/test", "asd", root, admin);
//		SimpleFolder subF = new SimpleFolder("cs", "/test", root, admin);
//		
//		
//		root.addFile(file);
//		root.addSubFolder(subF);
//		
//		System.out.println(root.getFile("testing").getName());
//		System.out.println(root.getSubFolder("cs").getName());
//		System.out.println(system.containsFileFolder("testing.txt"));
//		
//		String path = "../../...";
//		String [] paths = path.split("\\.");
//		System.out.println(paths.length);
//		System.out.println();
//		System.out.println(paths[4]);
//		System.out.println();
//		System.out.println(system.containsFileFolder(paths[5]));
		System.out.println("hello");
		int x = Integer.MAX_VALUE;
		System.out.println(x);
	}
	
	

	/**
	 * Check if the extension is valid. 
	 * @param extension
	 * @return true if valid, false otherwise 
	 */
	private static boolean extensionIsValid(String extension){
		// check all values in the Extension class
		for(Extension ext : Extension.values()){
			if(ext.toString().equals(extension))
				return true;
		}
		return false;
	} 


	private static boolean nameValidation(String name){
		if(name == null) throw new IllegalArgumentException();

		// if the first element is not a letter, invalid
		char firstLetter = name.charAt(0);
		if(!Character.isLetter(firstLetter)) return false;

		// check the rest of the element
		char [] chars = name.toCharArray();
		for(int i = 1; i < chars.length; i ++){
			// if not number or letter, then it is invalid
			if(!Character.isLetter(chars[i]) && !Character.isDigit(chars[i])){
				return false;
			}
		}
		return true;
	}
}
