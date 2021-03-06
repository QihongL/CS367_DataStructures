///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 1 - Reddit
// Files:            User.java
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

/**
 * This User class creates user and keep track of the user's information,
 * including the name, karma, list of subscribed subreddits, posts and so on.  
 */
public class User {
	final private String name;			// the user name 
	final private Karma karma;			// the karma of the user  
	private List<String> subscribed;	// the subscribed list 
	private List<Post> posted;			// the list of posted reddit  
	private List<Post> liked;			// the list of liked reddit
	private List<Post> disliked;		// the list of disliked reddit

	/**
	 * Creates an instance of the User class with the specified name and a 
	 * newly initialized instance of Karma. The Lists will be initialized to 
	 * new ArrayLists of the relevant types as applicable.
	 * 
	 * @param name
	 */
	public User(String name) {
		// initialize all fileds associated with the user 
		this.name = name;	
		karma = new Karma();
		subscribed = new ArrayList<String>();
		posted = new ArrayList<Post>();
		liked = new ArrayList<Post>();
		disliked = new ArrayList<Post>();
	}

	/**
	 * Return the name of the current user.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the karma of the current user.
	 * 
	 * @return karma
	 */
	public Karma getKarma() {
		return this.karma;
	}
	
	/**
	 * Returns a copy of the list of subreddits the user is subscribed to; 
	 * the list itself should not be exposed.
	 * 
	 * @return copy - a copy of subscribed subreddit list 
	 */
	public List<String> getSubscribed() {
		// create a copy
		List<String> copy = new ArrayList<String>();
		// create a iterator in order to traverse though the list
		Iterator<String>itr = subscribed.iterator();
		while (itr.hasNext()) {
			copy.add(itr.next());
		}
		return copy;
	}

	/**
	 * Returns a copy of the list of posts posted by the user; 
	 * the list itself should not be exposed.
	 * 
	 * @return copy - a copy of posts posted by the user
	 */
	public List<Post> getPosted() {
		List<Post> copy = new ArrayList<Post>();
		Iterator<Post> itr = posted.iterator();
		while(itr.hasNext()){
			copy.add(itr.next());
		}
		return copy;
	}

	/**
	 * Returns a copy of the list of posts liked by the user;
	 * the list itself should not be exposed.
	 * 
	 * @return copy - a copy the list liked by the user 
	 */
	public List<Post> getLiked() {
		List<Post> copy = new ArrayList<Post>();
		Iterator<Post> itr = liked.iterator();
		while(itr.hasNext()){
			copy.add(itr.next());
		}
		return copy;
	}

	/**
	 * Returns a copy of the list of posts disliked by the user
	 * the list itself should not be exposed.
	 * 
	 * @return copy - a copy the list disliked by the user
	 */
	public List<Post> getDisliked() {
		List<Post> copy = new ArrayList<Post>();
		Iterator<Post> itr = disliked.iterator();
		while(itr.hasNext()){
			copy.add(itr.next());
		}
		return copy;
	}

	/**
	 * Add the specified subreddit to the List of subscribed subreddits 
	 * if the user is not already subscribed. If the user is already 
	 * subscribed, unsubscribe from the subreddit.
	 * 
	 * @param subreddit
	 */
	public void subscribe(String subreddit) {
		// if the user already subscribed the post, remove the subscription
		if(subscribed.contains(subreddit)){
			unsubscribe(subreddit);
		// if the user haven't subscribe the post, add it to subscribed list
		} else {
			subscribed.add(subreddit);
		}
	}

	/**
	 * Remove the specified subreddit from the List of subscribed subreddits 
	 * if present; if not, do nothing.
	 * 
	 * @param subreddit
	 */
	public void unsubscribe(String subreddit) {
		// remove post only if the post is in the subscribed list
		if(subscribed.contains(subreddit))
			subscribed.remove(subreddit);
	}

	/**
	 * Instantiate a new post with the appropriate parameters and add it to 
	 * the list of posts posted by the user.
	 * 
	 * @param subreddit
	 * @param type
	 * @param title
	 */
	public Post addPost(String subreddit, PostType type, String title) {
		// create a new post
		Post newPost = new Post(this, subreddit, type, title);
		// add it to user's posted list
		posted.add(newPost);
		// user should automatically like their own post 
		this.like(newPost);
		return newPost;
	}

	/**
	 * Upvote the post and add it to the List of liked posts if not already 
	 * liked; else undo the like.If the post is currently disliked by the 
	 * user, the dislike should be undone.
	 * 
	 * @param post
	 */
	public void like(Post post) {
		// if already liked...
		if(liked.contains(post)){
			// undo the like
			undoLike(post);	
		// if not liked
		} else {
			// upvote and add the post to liked list
			post.upvote();
			liked.add(post);
		}
		// moreover, it the user disliked it, undo the dislike 
		if(disliked.contains(post)){
			undoDislike(post);
		}
	}

	/**
	 * Remove the post from the list of disliked posts and update its karma 
	 * appropriately.
	 * 
	 * @param post
	 */
	public void undoLike(Post post) {
		liked.remove(post);
		post.downvote();
		post.downvote();
	}

	/**
	 * Downvote the post and add it to the List of disliked posts if not 
	 * already disliked; else undo the dislike. If the post is currently 
	 * liked by the user, the like should be undone.
	 * 
	 * @param post
	 */
	public void dislike(Post post) {
		// if already disliked 
		if(disliked.contains(post)){
			// undo the dislike 
			undoDislike(post);
		// if not in the disliked list 
		} else {
			// downvote and add the post to the disliked list
			post.downvote();
			disliked.add(post);
		}
		// moreover, if the post is in the liked list, undo it 
		if(liked.contains(post)){
			undoLike(post);
		}
	}

	/**
	 * Remove the post from the list of disliked posts and update its karma 
	 * appropriately.
	 * 
	 * @param post
	 */
	public void undoDislike(Post post) {
		disliked.remove(post);
		post.upvote();
		post.downvote();
		
	}
}
