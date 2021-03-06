///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 1 - Reddit
// Files:            Post.java
// Semester:         CS367 Spring 2015
//
// Author:           Qihong Lu
// Email:            qlu36@wisc.edu
// CS Login:         qihong
// Lecturer's Name:  Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

public class Post {
	final private User user;		// the user if this post
	final private String subreddit;	// the subreddit of this post 
	final private PostType type;	// the type of this post 
	final private String title;		// the title of this post 
	private int karma;				// the karma of this post 
	
	/**
	 * Constructs a post with the specified attributes as applicable. 
	 */
	public Post(User user, String subreddit, PostType type, String title) {
		// initialize all associated fields
		this.user = user;
		this.subreddit = subreddit;
		this.type = type;
		this.title = title;		
		this.karma = 0;
	}

	/**
	 * Increases the karma of this post and the relevant karma of the 
	 * user who created the post by two each.    
	 */
	public void upvote() {
		karma += 2;
		user.getKarma().upvote(type);		
	}
	
	/**
	 * Decreases the karma of this post and the relevant karma of the 
	 * user who created the post by one each.    
	 */
	public void downvote() {
		karma --;
		user.getKarma().downvote(type);
	}

	/**
	 * Returns the user who created this post.
	 * 
	 * @return user 
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * Returns the subreddit this was posted to.
	 * 
	 * @return subreddit 
	 */
	public String getSubreddit() {
		return this.subreddit;
	}
	
	/**
	 * Returns the type of the post.
	 * 
	 * @return type
	 */
	public PostType getType() {
		return this.type;
	}

	/**
	 * Returns the title of the post.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Returns the karma aggregated by the post.
	 * 
	 * @return karma
	 */
	public int getKarma() {
		return this.karma;
	}
	
}
