
public class Access {
	
	private User user;
	private char accessType;
	
	/**
	 * Constructor
	 * @param user
	 * @param accessType
	 */
	public Access(User user, char accessType) {
		//TODO
		this.user = user;
		this.accessType = accessType;
	}

	/**
	 * Accessor for the user object
	 * @return user 
	 */
	public User getUser() {
		//TODO
		return this.user;
	}

	/**
	 * Get the type of the access. It can be 'r' or 'w'
	 * @return
	 */
	public char getAccessType() {
		//TODO
		return this.accessType;
	}

	/**
	 * Mutator for the access type 
	 * @param accessType
	 */
	public void setAccessType(char accessType) {
		//TODO
		if(accessType != 'r' || accessType != 'w')
			throw new IllegalArgumentException();
		this.accessType = accessType;
	}
	
	/**
	 * Return a string representation of an access 
	 */
	@Override
	public String toString() {
		return (user.getName() + ":" + accessType);
	}
	
}