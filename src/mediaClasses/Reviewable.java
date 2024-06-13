package mediaClasses;

/*
 * Requirements for an object to get a review
 */
public interface Reviewable {
	public void giveRating(int rating);
	
	public void giveRating(int rating, String review);
	
	public void giveReview(String review);
	
	public int getRating();
	
	public String getReview();
}
