package model;


import org.json.JSONObject;
import persistence.Writable;

//Represents a song with a title, artist and genre
public class Song implements Writable {
    private String title;
    private String artist;
    private String genre;

    //REQUIRES: title, artist, and album parameters have a non-zero length
    //EFFECTS: constructs a song element with the given parameters
    public Song(String title, String artist, String genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    //EFFECTS: returns song title
    public String getSongTitle() {
        return title;
    }

    //EFFECTS: sets song title
    public void setSongTitle(String title) {
        this.title = title;
    }

    //EFFECTS: returns artist of song
    public String getArtist() {
        return artist;
    }

    //EFFECTS: sets artist of song
    public void setArtist(String artist) {
        this.artist = artist;
    }

    //EFFECTS: returns genre
    public String getGenre() {
        return genre;
    }

    //EFFECTS: sets genre of song
    public void setGenre(String genre) {
        this.genre = genre;
    }

    //EFFECTS: returns string representation of this song
    public String toString() {
        return "" + this.title + " by "
                + this.artist + "-"
                + "" + this.genre;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("artist", artist);
        json.put("genre", genre);
        return json;
    }
}
