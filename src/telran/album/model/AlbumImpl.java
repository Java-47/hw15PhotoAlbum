package telran.album.model;

import java.time.LocalDate;
import java.util.Arrays;

public class AlbumImpl implements Album {

	private Photo[] photos;
	private int size;

	public AlbumImpl(int capacity) {
		photos = new Photo[capacity];
	}

	@Override
	public boolean addPhoto(Photo photo) {
		if (size == photos.length || photo == null
				|| getPhotoFromAlbum(photo.getPhotoId(), photo.getAlbumId()) != null) {
			return false;
		}
		photos[size++] = photo;
		return true;
	}
	

	@Override
	public boolean removePhoto(int albumId, int photoId ) {
		for (int i = 0; i < size; i++) {
			if (photos[i].getAlbumId() == albumId && photos[i].getPhotoId() == photoId) {
				Photo[] result = new Photo[photos.length - 1];		 
		        System.arraycopy(photos, 0, result, 0, i);
		        System.arraycopy(photos, i + 1, result, i, photos.length - i - 1);
		        size--;
		        return true;
			}
		}
		return false;
	}

	@Override
	public boolean updatePhoto(int photoId, int albumId, String url) {
		if(url != null && getPhotoFromAlbum(photoId, albumId) != null  ) 
		{
		getPhotoFromAlbum(photoId, albumId).setUrl(url);
		return true;
		}
		
		return false;
	}

	@Override
	public Photo getPhotoFromAlbum(int photoId, int albumId) {
		for (int i = 0; i < size; i++) {
			if (photos[i].getAlbumId() == albumId && photos[i].getPhotoId() == photoId) {
				return photos[i];
			}
		}
		return null;
	}

	@Override
	public Photo[] getAllPhotoFromAlbum(int albumId) {
		Photo[] result = new Photo[0];
		for (int i = 0; i < size; i++) {
			if (photos[i].getAlbumId() == albumId) {
				result = Arrays.copyOf(result, result.length+1);
				result[result.length-1] = photos[i];
			}
		}
		return result;
	}

	@Override
	public Photo[] getPhotoBeetwenDate(LocalDate dateFrom, LocalDate dateTo) {
		Photo[] result = new Photo[0];
		for (int i = 0; i < size; i++) {
			int dateCompareFrom = photos[i].getDate().toLocalDate().compareTo(dateFrom);
			int dateCompareTo = photos[i].getDate().toLocalDate().compareTo(dateTo);
			if (dateCompareFrom>0 && dateCompareTo<0) {
				result = Arrays.copyOf(result, result.length+1);
				result[result.length-1] = photos[i];
			}
		}
		return result;
	}

	@Override
	public int size() {
		return size;
	}

}
