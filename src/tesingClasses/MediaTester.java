package tesingClasses;

import mediaClasses.MediaItem;
import mediaClasses.Movie;

public class MediaTester {
	public static void main(String[] args) {
		Movie m = new Movie("Cars 2", 100);
		MediaItem m2 = new MediaItem("Cars 3", "12-12-2012");
		MediaItem m3 = new MediaItem("Cars 3");
		m2.giveRating(5);
		
		System.out.println(m);
		System.out.println(m2);
		MediaItem.setDateFormatter("yyyy-MM-dd");
		System.out.println(m3.getFinishDateString());
		
		
	}
}
