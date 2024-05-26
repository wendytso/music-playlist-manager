package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    private Playlist testPlaylist;
    private Playlist testPlaylistByTaylor;
    private Playlist testPlaylistOfGenre;

    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;

    @BeforeEach
    void runBefore() {
        testPlaylist = new Playlist("My Music");
        testPlaylistOfGenre = new Playlist("Korean Pop");
        testPlaylistByTaylor = new Playlist("Taylor Swift");
        song1 = new Song("The Feels", "Twice", "Korean Pop");
        song2 = new Song("Ditto", "New Jeans", "Korean Pop");
        song3 = new Song("Anti Hero", "Taylor Swift", "Pop");
        song4 = new Song("Red", "Taylor Swift", "Country");
    }

    @Test
    void testConstructor() {
        assertEquals("My Music", testPlaylist.getPlaylistName());
        assertEquals(0, testPlaylist.getNumberOfSongs());
    }

    @Test
    void testSongGetters() {
        assertEquals("The Feels", song1.getSongTitle());
        assertEquals("Twice", song1.getArtist());
        assertEquals("Korean Pop", song1.getGenre());
    }

    @Test
    void testSongSetters() {
        song1.setSongTitle("Kill Bill");
        song1.setArtist("SZA");
        song1.setGenre("Pop");

        assertEquals("Kill Bill", song1.getSongTitle());
        assertEquals("SZA", song1.getArtist());
        assertEquals("Pop", song1.getGenre());
    }

    @Test
    void testSongToString() {
        assertEquals("The Feels by Twice-Korean Pop", song1.toString());
    }


    @Test
    void testAddSongToPlaylistToEmpty() {
        testPlaylist.addSong(song1);
        assertEquals(1, testPlaylist.getNumberOfSongs());
        assertEquals("The Feels", testPlaylist.getSongAtIndex(0));
        assertEquals("Twice", testPlaylist.getArtistAtIndex(0));
        assertEquals("Korean Pop", testPlaylist.getGenreAtIndex(0));

        assertTrue(testPlaylist.containsArtist("Twice"));
        assertTrue(testPlaylist.containsGenre("Korean Pop"));
    }

    @Test
    void testAddMultipleSongToPlaylist(){
        testPlaylist.addSong(song1);
        testPlaylist.addSong(song2);
        testPlaylist.addSong(song3);
        testPlaylist.addSong(song4);

        assertEquals(4, testPlaylist.getNumberOfSongs());

        assertEquals("The Feels", testPlaylist.getSongAtIndex(0));
        assertEquals("Twice", testPlaylist.getArtistAtIndex(0));
        assertEquals("Korean Pop", testPlaylist.getGenreAtIndex(0));

        assertEquals("Ditto", testPlaylist.getSongAtIndex(1));
        assertEquals("New Jeans", testPlaylist.getArtistAtIndex(1));
        assertEquals("Korean Pop", testPlaylist.getGenreAtIndex(1));

        assertEquals("Red", testPlaylist.getSongAtIndex(3));
        assertEquals("Taylor Swift", testPlaylist.getArtistAtIndex(3));
        assertEquals("Country", testPlaylist.getGenreAtIndex(3));

        assertTrue(testPlaylist.containsArtist("Twice"));
        assertTrue(testPlaylist.containsGenre("Korean Pop"));
    }


    @Test
    void testRemoveSongFromPlaylist(){
        testPlaylist.addSong(song1);
        testPlaylist.addSong(song2);
        testPlaylist.addSong(song3);
        testPlaylist.addSong(song4);
        testPlaylist.removeSong(0);

        assertEquals("Ditto", testPlaylist.getSongAtIndex(0));
        assertEquals("New Jeans", testPlaylist.getArtistAtIndex(0));
        assertEquals("Korean Pop", testPlaylist.getGenreAtIndex(0));

        assertEquals(3, testPlaylist.getNumberOfSongs());

        assertFalse(testPlaylist.containsArtist("Twice"));
    }


    @Test
    void testListOfSongs(){
        testPlaylist.addSong(song1);
        testPlaylist.addSong(song2);
        testPlaylist.addSong(song3);
        testPlaylist.addSong(song4);

        assertEquals(song1, testPlaylist.getListOfSongs().getFirst());
        assertEquals(song4, testPlaylist.getListOfSongs().getLast());

    }

    @Test
    void testPlaylistContainsGenre(){
        testPlaylist.addSong(song1);
        testPlaylist.addSong(song2);
        testPlaylist.addSong(song3);
        testPlaylist.addSong(song4);


        assertTrue(testPlaylist.containsGenre("Korean Pop"));
        assertFalse(testPlaylist.containsGenre("Jazz"));
    }

    @Test
    void testPlaylistContainsArtist(){
        testPlaylist.addSong(song1);
        testPlaylist.addSong(song2);
        testPlaylist.addSong(song3);
        testPlaylist.addSong(song4);


        assertTrue(testPlaylist.containsArtist("Taylor Swift"));
        assertFalse(testPlaylist.containsArtist("Lady Gaga"));
    }

    @Test
    void testSongsOfGenre(){
        testPlaylist.addSong(song1);
        testPlaylist.addSong(song2);
        testPlaylist.addSong(song3);
        testPlaylist.addSong(song4);

        testPlaylistOfGenre.addSong(song1);
        testPlaylistOfGenre.addSong(song2);

        assertEquals(testPlaylistOfGenre.getListOfSongs(), testPlaylist.songsOfGenreType("Korean Pop"));

    }

    @Test
    void testSongsFromArtist(){
        testPlaylist.addSong(song1);
        testPlaylist.addSong(song2);
        testPlaylist.addSong(song3);
        testPlaylist.addSong(song4);

        testPlaylistByTaylor.addSong(song3);
        testPlaylistByTaylor.addSong(song4);

        assertEquals(testPlaylistByTaylor.getListOfSongs(), testPlaylist.songsByArtistName("Taylor Swift"));
    }

    @Test
    void testClearPlaylist(){
        testPlaylist.addSong(song1);
        testPlaylist.addSong(song2);
        testPlaylist.addSong(song3);
        testPlaylist.addSong(song4);

        testPlaylist.clearPlaylist();

        assertEquals(0, testPlaylist.getNumberOfSongs());
    }

}