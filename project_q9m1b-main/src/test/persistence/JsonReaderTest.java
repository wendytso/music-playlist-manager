package persistence;

import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Playlist playlist = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlaylist.json");
        try {
            Playlist playlist = reader.read();
            assertEquals("My playlist", playlist.getPlaylistName());
            assertEquals(0, playlist.getNumberOfSongs());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlaylist.json");
        try {
            Playlist playlist = reader.read();
            assertEquals("My playlist", playlist.getPlaylistName());
            List<Song> songs = playlist.getListOfSongs();
            assertEquals(2, songs.size());
            checkSong("Umbrella", "Rihanna", "Pop", songs.get(0));
            checkSong("Russian Roulette", "Red Velvet", "Korean Pop", songs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}