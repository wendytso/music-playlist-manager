package persistence;

import model.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class JsonTest {
    protected void checkSong (String title, String artist, String genre, Song song) {
        assertEquals(title, song.getSongTitle());
        assertEquals(artist, song.getArtist());
        assertEquals(genre, song.getGenre());
    }
}
