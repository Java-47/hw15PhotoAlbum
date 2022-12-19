package telran.album.model;
import java.time.LocalDateTime;

public class Photo implements Comparable<Photo> {

	private int albumId;
	private int photoId;
	private String title;
	private String url;
	LocalDateTime date;

	public Photo(int albumId, int photoId, String title, String url, LocalDateTime date) {
		super();
		this.albumId = albumId;
		this.photoId = photoId;
		this.title = title;
		this.url = url;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getAlbumId() {
		return albumId;
	}

	public int getPhotoId() {
		return photoId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	@Override
	public int compareTo(Photo o) {
		return date.compareTo(o.date);
	}

	@Override
	public String toString() {
		return "Photo [albumId=" + albumId + ", photoId=" + photoId + ", title=" + title + ", url=" + url + ", date="
				+ date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + albumId;
		result = prime * result + photoId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Photo)) {
			return false;
		}
		Photo other = (Photo) obj;
		if (albumId != other.albumId) {
			return false;
		}
		if (photoId != other.photoId) {
			return false;
		}
		return true;
	}
	

}
