package telran.album.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlbumImplTest {

	Album album;
	LocalDateTime dateNow = LocalDateTime.now();
	Photo[] photos = new Photo[5];
	
	Comparator<Photo> comp = (p1, p2) -> {
		int res = Integer.compare(p1.getAlbumId(), p2.getAlbumId());
		return res != 0 ? res : Integer.compare(p1.getPhotoId(), p2.getPhotoId());
	};
	
	@BeforeEach
	void setUp() throws Exception {
		album = new AlbumImpl(6);
		photos[0] = new Photo(1, 1, "photo1", "u1", dateNow.minusDays(1));
		photos[1] = new Photo(1, 2, "photo2", "u2", dateNow.minusDays(2));
		photos[2] = new Photo(3, 2, "photo3", "u3", dateNow.minusDays(5));
		photos[3] = new Photo(4, 1, "photo4", "u4", dateNow.minusDays(4));
		
		photos[4] = new Photo(5, 3, "photo5", "u5", dateNow.minusDays(3));
		
		for (int i = 0; i < photos.length - 1; i++) {
			album.addPhoto(photos[i]);
		}
	}

	@Test
	void testAddPhoto() {
		assertTrue(album.addPhoto(photos[4]));
		assertEquals(5, album.size());
		assertFalse(album.addPhoto(photos[4]));
		assertEquals(5, album.size());
	}

	@Test
	void testRemovePhoto() {
		assertTrue(album.removePhoto(4, 1));
		assertEquals(3, album.size());
		assertFalse(album.removePhoto(5, 1));
		assertNull(album.getPhotoFromAlbum(4, 1));
		
	}

	@Test
	void testUpdatePhoto() {
		assertTrue(album.updatePhoto(1, 4, "urlUpdated"));
		assertEquals("urlUpdated", album.getPhotoFromAlbum(1, 4).getUrl());
	}

	@Test
	void testGetPhotoFromAlbum() {
		assertEquals(photos[3], album.getPhotoFromAlbum(1, 4));
		assertNull(album.getPhotoFromAlbum(5, 1));
	}

	@Test
	void testGetAllPhotoFromAlbum() {
		Photo[] actual = album.getAllPhotoFromAlbum(1);
		Arrays.sort(actual, comp);
		Photo[] expected = { photos[0], photos[1] };
		assertArrayEquals(expected, actual);
	}

	@Test
	void testGetPhotoBeetwenDate() {
		LocalDate localDate = dateNow.toLocalDate();
		Photo[] actual = album.getPhotoBeetwenDate(localDate.minusDays(6), localDate.minusDays(3));
		Arrays.sort(actual, comp);
		Photo[] expected = { photos[2], photos[3] };
		assertArrayEquals(expected, actual);
	}

	@Test
	void testSize() {
		assertEquals(4, album.size());
	}

}
