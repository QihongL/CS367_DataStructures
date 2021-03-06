
public class vowels_recur {

	public static boolean isVowels(char letter){
		String vowels = "aeiou";
		// loop over all vowels
		for (int i = 0; i < vowels.length(); i ++){
			// if matches with one vowel, return true 
			if (letter == vowels.charAt(i)) return true;
		}
		return false;
	}
	
	
	/**
	 * 
	 * PRACTICE RECURSION
	 * take a string, find all vowels and return them 
	 * 
	 * @param str
	 * @return all vowels 
	 */
	public static String vowels(String str){
		// base case
		if (str.length() == 0) return "";
		
		// recursive call 
		// if find a vowel at the 1st position 
		if(isVowels((str.charAt(0))))
			// return the 1st letter and check the rest
			return str.charAt(0) + vowels(str.substring(1));
		else
			// check the rest 
			return vowels(str.substring(1));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(vowels("hooligan"));
		
		
	}

}
