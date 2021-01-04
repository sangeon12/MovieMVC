package movie;

import java.awt.Image;

public class MovieVO {
	int movieNo; 
	String movieName; 
	int category; 
	int runtime; 
	String img; 
	String info;
	
	public MovieVO() {
		
	}
	
	public MovieVO(int movieNo, String movieName, int category, int runtime, String img, String info) {
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.category = category;
		this.runtime = runtime;
		this.img = img;
		this.info = info;
	}

	@Override
	public String toString() {
		return "MovieVO [movieNo=" + movieNo + ", movieName=" + movieName + ", category=" + category + ", runtime="
				+ runtime + ", img=" + img + ", info=" + info + "]";
	}
	
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
