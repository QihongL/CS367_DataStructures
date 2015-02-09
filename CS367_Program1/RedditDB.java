///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 1 - Reddit
// Files:            RedditDB.java
// Semester:         CS367 Spring 2015
//
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class RedditDB {
	private List<User> users;	// a list of users 

	/**
	 * Constructs an empty database. 
	 */
	public RedditDB() {
		// initialize a list of users associated with this Reddit data base
		this.users = new ArrayList<User>();
	}

	/**
	 * Returns a copy of the list of users; the list itself should 
	 * not be exposed.
	 * 
	 * @return users - a copy of the list of users
	 */
	public List<User> getUsers() {
		// create a copy for the List of users 
		List<User> copy = new ArrayList<User>();
		// loop over all users and copy them to the list
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()){
			copy.add(itr.next());
		}
		return copy;
	}

	/**
	 * Add a user with the given name to the database and return it if a 
	 * user with the given name does not already exist; else return null.
	 * 
	 * @param name
	 */
	public User addUser(String name) {
		//TODO
		// if user already exist, return null
		if(findUser(name)!= null){
			return null;
			// if the user doesn't exist, then add this new user
		} else {
			User newUser = new User(name);
			users.add(newUser);
			return newUser;
		}
	}

	/**
	 * Search the database for a user with the given name and return the 
	 * information if found; else return null.
	 * 
	 * @param name
	 */
	public User findUser(String name) {
		//TODO
		// loop over all users
		Iterator<User> userItr = users.iterator();	// TODO use iterator
		while(userItr.hasNext()){
			User tempUser = userItr.next();
			// return the user if find a name match
			if (tempUser.getName().equals(name)){
				return tempUser;
			}
		}
		return null;
	}

	/**
	 * Search the database for a user with the given name and delete the 
	 * information and return true if found; else return false. Deletion 
	 * also involves undoing all information related to the user: 
	 * (i) For each posted post, remove it from the liked and disliked 
	 * information of all users. (ii) Undo all likes. (iii) Undo all dislikes.
	 * 
	 * @param name
	 */
	public boolean delUser(String name) {
		//TODO)
		// create an iterator in order to traverse though the users list 
		Iterator<User> itr = users.iterator();
		while (itr.hasNext()){
			User tempUser = itr.next();
			// if user can be found 
			if (tempUser.getName().equals(name)){
				// TODO i, ii, iii
				itr.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Get all the posts to be displayed on the front page of a particular 
	 * user and return them. 
	 * i) If the user is null this is simply all posts from all users. 
	 * ii) If a user is specified, return all the posts from the 
	 * user's subscribed subreddits only; posts which have previously been 
	 * liked or disliked by the user should not be returned, except when the 
	 * post was created by the user themselves.
	 * 
	 * assume we need to display all posts
	 * 
	 * @param user
	 */
	public List<Post> getFrontpage(User user) {
		//TODO
		// get all posts
		List<Post> allPosts = getAllPosts();
		// if user is null -> display all posts
		if (user == null){
			return allPosts;
			// if user is not null -> only display subscribed title
		} else {
			List<Post> subscribedPosts = getSubscribedPosts(allPosts, user);
			return subscribedPosts;
		}
	}

	/**
	 * Given a list of posts, this method select posts that are subscribed
	 * by the user. It also eliminates posts that are liked or disliked by 
	 * the user.
	 * 
	 *  @param allPosts - an List of posts
	 *  @param user - the user 
	 *  @return subscribedList - a list of posts that are subscribed by 
	 *  		the user
	 * */
	private List<Post> getSubscribedPosts(List<Post> allPosts, User user) {
		// create a new list that only stores subscribed posts
		List<Post> subscribedPosts = new ArrayList<Post>();
		// loop over all posts 
		Iterator<Post> postItr = allPosts.iterator();
		while(postItr.hasNext()){
			// store the current post 
			Post currentPost = postItr.next();
			// skip this post if the user already liked or disliked it 
			if(!user.getLiked().contains(currentPost) 
					&& !user.getDisliked().contains(currentPost) 
					|| user.getPosted().contains(currentPost)){ 
				// get the subreddit name of the current post
				String subreddit = currentPost.getSubreddit();
				// loop over all subscribed list
				Iterator<String> subscribedItr = user.getSubscribed().iterator();
				while(subscribedItr.hasNext()){
					// if a match is found, it is subscribed 
					String tempSubscribedName = subscribedItr.next();
					if(subreddit.equals(tempSubscribedName)){
						// then add it to the subscribed list 
						subscribedPosts.add(currentPost);
					}
				}
			}
		}
		return subscribedPosts;
	}

	/**
	 * This method get all posts from all users
	 * 
	 * @return allPosts - an ArrayList of Post that stores all posts   
	 * */
	private List<Post> getAllPosts() {
		// create a list that stores all posts
		List<Post> allPosts = new ArrayList<Post>();
		// loop over all users
		Iterator<User> usersItr = users.iterator();
		while(usersItr.hasNext()){
			User tempUser = usersItr.next();
			// loop over all posts for a user 
			Iterator<Post> postsItr = tempUser.getPosted().iterator();
			while(postsItr.hasNext()){
				Post post = postsItr.next();
				// add the post to the copy list
				allPosts.add(post);
			}
		}
		return allPosts;
	}

	/**
	 * Get all the posts from the specified subreddit to be displayed on the 
	 * front page of a particular user and return them. 
	 * 
	 * If the user is null this is simply all relevant posts from all users. 
	 * If a user is specified, return all the posts from the subreddit which 
	 * have not previously been liked or disliked by the user ,except when 
	 * the post was created by the user themselves.
	 * 
	 * 
	 * @param user
	 * @param subreddit
	 */
	public List<Post> getFrontpage(User user, String subreddit) {
		//TODO
		// get all posts
		List<Post> allPosts = getAllPosts();
		// if the user is null, return all posts 
		if (user == null){
			return allPosts;
			// otherwise, return the posts belong to the target subreddit
		} else {
			List<Post> subredditPosts = getSubredditPosts(allPosts, subreddit);
			return subredditPosts;
		}

	}

	private List<Post>getSubredditPosts(List<Post> allPosts,String subreddit){
		// create a list of hold all posts belongs to the target subreddit 
		List<Post> subredditPosts = new ArrayList<Post>();
		// loop over all posts 
		Iterator<Post> allPostsItr = allPosts.iterator();
		while(allPostsItr.hasNext()){
			Post currentPost = allPostsItr.next();
			// if the current post belongs to the target subreddit
			if (currentPost.getSubreddit().equals(subreddit)){
				// add it to the list
				subredditPosts.add(currentPost);
			}
		}
		return subredditPosts;
	}
	
}	// end of the class