package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// Represents a playlist having a collection of songs
public class Playlist implements Writable {

    private LinkedList<Song> songPlaylist;
    private String playlistName;
    private EventLog eventLog;


    //EFFECTS: creates an empty playlist as a linked list
    public Playlist(String name) {
        songPlaylist = new LinkedList<Song>();
        this.playlistName = name;
        eventLog = EventLog.getInstance();

    }

    //EFFECTS: return the playlist name
    public String getPlaylistName() {
        return playlistName;
    }


    //EFFECTS: return the total number of songs in playlist
    public int getNumberOfSongs() {
        return songPlaylist.size();
    }

    //MODIFIES: this
    //EFFECTS: add the song to playlist
    public void addSong(Song song) {
        eventLog.logEvent(new Event("Added song: " + song.getSongTitle() + " to Playlist"));
        songPlaylist.add(song);
    }

    //REQUIRES: playlist is not empty
    //MODIFIES: this
    //EFFECTS: remove the song of specified index from playlist
    public void removeSong(int index) {
        eventLog.logEvent(new Event("Removed song: " + songPlaylist.get(index).getSongTitle() + " from Playlist"));
        songPlaylist.remove(index);
    }



    //REQUIRES: playlist is not empty
    //EFFECTS: return list of songs in playlist
    public LinkedList<Song> getListOfSongs() {
        return songPlaylist;
    }

    //EFFECTS: returns the song title at the specified index
    public String getSongAtIndex(int index) {
        return songPlaylist.get(index).getSongTitle();
    }

    //EFFECTS: returns the artist of the song at the specified index
    public String getArtistAtIndex(int index) {
        return songPlaylist.get(index).getArtist();
    }

    //EFFECTS: returns the genre at the specified index
    public String getGenreAtIndex(int index) {
        return songPlaylist.get(index).getGenre();
    }

    //EFFECTS: return true if playlist contains the genre, otherwise return false
    public boolean containsGenre(String genreType) {
        for (int i = 0; i < songPlaylist.size(); i++) {
            if (songPlaylist.get(i).getGenre().equalsIgnoreCase(genreType)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: return true if playlist contains the artist, otherwise return false
    public boolean containsArtist(String artistName) {
        for (int i = 0; i < songPlaylist.size(); i++) {
            if (songPlaylist.get(i).getArtist().equalsIgnoreCase(artistName)) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: playlist is not empty
    //EFFECTS: return list of songs in playlist of the specified genre
    public LinkedList songsOfGenreType(String genreType) {
        LinkedList<Song> songsOfGenre = new LinkedList<Song>();
        for (int i = 0; i < songPlaylist.size(); i++) {
            if (songPlaylist.get(i).getGenre().equalsIgnoreCase(genreType)) {
                songsOfGenre.add(songPlaylist.get(i));
            }
        }
        eventLog.logEvent(new Event("Filtered Playlist by genre"));
        return songsOfGenre;

    }


    //REQUIRES: playlist is not empty
    //EFFECTS: return list of songs in playlist of specified artist
    public LinkedList songsByArtistName(String artistName) {
        LinkedList<Song> songsByArtist = new LinkedList<Song>();
        for (Song song : songPlaylist) {
            if (song.getArtist().equalsIgnoreCase(artistName)) {
                songsByArtist.add(song);
            }
        }
        eventLog.logEvent(new Event("Filtered Playlist by artist"));
        return songsByArtist;
    }

    //MODIFIES: playlist
    //EFFECTS: removes all songs in the playlist
    public void clearPlaylist() {
        eventLog.logEvent(new Event("Cleared playlist"));
        songPlaylist.clear();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("playlistName", playlistName);
        json.put("songs", songsToJson());
        return json;
    }

    // EFFECTS: returns songs in this playlist as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song song : songPlaylist) {
            jsonArray.put(song.toJson());
        }

        return jsonArray;
    }


    //EFFECTS: prints the eventLog of all the key events when the user quits the application
    public void printEventLog() {
        System.out.println("Event log for Playlist Maker");
        for (Event event : eventLog) {
            System.out.println(event.getDate() + event.getDescription());
        }
    }
}

