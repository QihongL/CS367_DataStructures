
public class GeneralTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String temp1 = "ASDASasd_";
//		String temp2 = "A1";
//		System.out.println(nameValidation(temp1));
//		System.out.println(nameValidation(temp2));

		System.out.println(extensionIsValid("doc"));
		
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
