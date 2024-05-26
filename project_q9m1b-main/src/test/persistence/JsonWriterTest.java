package persistence;

import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Playlist pl = new Playlist("My playlist");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlaylist() {
        try {
            Playlist pl = new Playlist ("My playlist");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlaylist.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlaylist.json");
            pl = reader.read();
            assertEquals("My playlist", pl.getPlaylistName());
            assertEquals(0, pl.getNumberOfSongs());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPlaylist() {
        try {
            Playlist pl = new Playlist ("My playlist");
            pl.addSong(new Song("Umbrella", "Rihanna", "Pop"));
            pl.addSong(new Song("Russian Roulette", "Red Velvet", "Korean Pop"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlaylist.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlaylist.json");
            pl = reader.read();
            assertEquals("My playlist", pl.getPlaylistName());
            List<Song> songs = pl.getListOfSongs();
            assertEquals(2, songs.size());
            checkSong("Umbrella", "Rihanna", "Pop", songs.get(0));
            checkSong("Russian Roulette", "Red Velvet", "Korean Pop", songs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
