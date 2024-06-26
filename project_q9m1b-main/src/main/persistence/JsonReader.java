package persistence;

import model.Playlist;
import model.Song;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// Represents a reader that reads the playlist from JSON data stored in file
// Code based on CPSC 210 Teller application
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads playlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Playlist read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlaylist(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses playlist from JSON object and returns it
    private Playlist parsePlaylist(JSONObject jsonObject) {
        String playlistName = jsonObject.getString("playlistName");
        Playlist pl = new Playlist(playlistName);
        addSongs(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: parses songs from JSON object and adds them to playlist
    private void addSongs(Playlist pl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(pl, nextSong);
        }
    }

    // MODIFIES: pl
    // EFFECTS: parses song from JSON object and adds it to playlist
    private void addSong(Playlist pl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String artist = jsonObject.getString("artist");
        String genre = jsonObject.getString("genre");

        Song song = new Song(title, artist, genre);
        pl.addSong(song);
    }
}
