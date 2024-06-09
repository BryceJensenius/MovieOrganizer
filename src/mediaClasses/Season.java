package mediaClasses;

public class Season extends MediaItem implements Reviewable{
	
	/*
	 * Media Items rating out of 10
	 */
	protected int rating;
	
	/*
	 * Optional Review of Media Item
	 */
	protected String review;
	
	/*
	 * number of episodes in this season
	 */
	protected int episodes;
	
	/*
	 * Makes a Season for a show
	 */
	public Season(String name) {
		super(name);
	}
	
	/*
	 * Name of season and date they finished watching
	 */
	public Season(String name, String date) {
		super(name, date);
	}
	
	/*
	 * @return episodes - number of episodes in this season
	 */
	public int getEpisodes() {
		return episodes;
	}
	
	/*
	 * @param episodes - number of episodes in this season
	 */
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	/*
	 * sets rating in range of 0 to 10
	 * @param rating
	 */
	public void giveRating(int rating) {
		this.rating = Math.max(0, Math.min(rating, 10));
	}
	
	/*
	 * Sets this media items review
	 * @param review
	 */
	public void giveReview(String review) {
		this.review = review;
	}
	
	/*
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}
	
	/*
	 * @return review
	 */
	public String getReview() {
		return review;
	}
}
