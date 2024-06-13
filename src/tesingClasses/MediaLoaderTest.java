package tesingClasses;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import mediaClasses.MediaItem;
import mediaClasses.Movie;
import mediaClasses.TVShow;
import mediaOrganizers.MediaLoader;
import mediaClasses.PersonHistory;

public class MediaLoaderTest {
	public static void main(String[] args) {
		PersonHistory p;
		try {
			p = MediaLoader.loadHistory("Bryce");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<MediaItem> m = p.getMedia();
		for(MediaItem media : m) {
			System.out.println(media);
		}
		ArrayList<Movie> m2 = p.getMovies();
		for(MediaItem media : m2) {
			System.out.println(media);
		}
		ArrayList<TVShow> m3 = p.getShows();
		for(MediaItem media : m3) {
			System.out.println(media);
		}
	}
}
