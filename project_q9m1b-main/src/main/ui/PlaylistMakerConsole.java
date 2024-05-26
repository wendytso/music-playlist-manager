package ui;


import model.Playlist;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Playlist maker
public class PlaylistMakerConsole {
    private static final String JSON_STORE = "./data/playlist.json";
    private Playlist playlist;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the playlist application
    public PlaylistMakerConsole() throws FileNotFoundException {
        input = new Scanner(System.in);
        playlist = new Playlist("My Playlist");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPlaylist();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    //Code based on CPSC 210 Teller application

    private void runPlaylist() {
        boolean keepGoing = true;
        String command = null;


        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nFinished");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addSong();
        } else if (command.equals("r")) {
            removeSong();
        } else if (command.equals("v")) {
            viewPlaylist();
        } else if (command.equals("s")) {
            selectSong();
        } else if (command.equals("f")) {
            filterPlaylist();
        } else if (command.equals("save")) {
            savePlaylist();
        } else if (command.equals("load")) {
            loadPlaylist();
        } else {
            System.out.println("That is not a valid selection.");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes playlist
    //Code based on CPSC 210 Teller application

    private void init() {
        Song song1 = new Song("The Feels", "Twice", "Korean Pop");
        Song song2 = new Song("Ditto", "New Jeans", "Korean Pop");
        Song song3 = new Song("Attention", "New Jeans", "Korean Pop");
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add song to playlist");
        System.out.println("\tr -> remove song from playlist");
        System.out.println("\tv -> view playlist");
        System.out.println("\ts -> select song from playlist");
        System.out.println("\tf -> filter songs from playlist");
        System.out.println("\tsave -> save playlist to file");
        System.out.println("\tload -> load playlist to file");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: adds a new song to playlist with prompts of each variable
    private void addSong() {
        System.out.println("Entering song to add...");
        Song newSong = new Song(null, null, null);


        newSong.setSongTitle(input.nextLine());
        System.out.print("Enter song title: ");
        newSong.setSongTitle(input.nextLine());
        System.out.print("Press enter: ");


        newSong.setArtist(input.nextLine());
        System.out.print("Enter artist name: ");
        newSong.setArtist(input.nextLine());
        System.out.print("Press enter: ");

        newSong.setGenre(input.nextLine());
        System.out.print("Enter genre: ");
        newSong.setGenre(input.nextLine());
        System.out.print("Press enter: ");

        playlist.addSong(newSong);
        System.out.print("Song added");
    }

    //EFFECTS: remove the specified index of song from displayed playlist
    private void removeSong() {
        if (playlist.getNumberOfSongs() == 0) {
            System.out.println("\nPlaylist is empty");
        } else {
            viewPlaylist();
            System.out.println("\nSelect song to remove");
            int removeSelected = input.nextInt();

            if (removeSelected <= playlist.getNumberOfSongs()) {
                playlist.removeSong(--removeSelected);
                System.out.println("\nSong has been removed from playlist");
            } else {
                System.out.print("\nThat is not a valid selection");
            }
        }
    }

    //EFFECTS: lists and prints all the songs in the playlist
    private void viewPlaylist() {
        if (playlist.getNumberOfSongs() == 0) {
            System.out.println("\nPlaylist is empty");
        } else {
            for (int i = 0; i < playlist.getNumberOfSongs(); i++) {
                System.out.print("\n#" + (i + 1) + " ");
                System.out.print("" + playlist.getSongAtIndex(i) + "");
                System.out.print(" by " + playlist.getArtistAtIndex(i) + " - ");
                System.out.print("" + playlist.getGenreAtIndex(i));
            }
        }
    }

    //EFFECTS: prompts user to select a song in the playlist
    private void selectSong() {
        viewPlaylist();
        if (playlist.getNumberOfSongs() != 0) {
            System.out.print("\nSelect the number of the song:\n");
            int songSelected = input.nextInt();

            if (songSelected <= playlist.getNumberOfSongs()) {
                --songSelected;
                System.out.print("" + playlist.getSongAtIndex(songSelected) + "");
                System.out.print(" by " + playlist.getArtistAtIndex(songSelected) + " - ");
                System.out.print("" + playlist.getGenreAtIndex(songSelected));
            } else {
                System.out.print("\nThat is not a valid selection");
            }
        }
    }

    //EFFECTS: filters the list of songs for specified genre or artist
    //Code based on CPSC 210 Teller application

    private void filterPlaylist() {
        String selection = "";

        while (!(selection.equals("g") || selection.equals("a"))) {
            System.out.println("Do you want to filter by genre or artist?");
            System.out.println("Input g for filter by genre");
            System.out.println("Input a for filter by artist");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("g")) {
            filterPlaylistByGenre();
        }
        if (selection.equals("a")) {
            filterPlaylistByArtist();
        }
    }

    //EFFECTS: filters the list of songs for specified genre
    private void filterPlaylistByGenre() {
        if (playlist.getNumberOfSongs() == 0) {
            System.out.println("\nPlaylist is empty");
        } else {
            System.out.println("Enter genre to filter");
            String genreName = input.next();
            if (playlist.containsGenre(genreName)) {
                System.out.print("" + playlist.songsOfGenreType(genreName) + "");
            } else {
                System.out.println("There are no songs in playlist with this genre");
            }
        }
    }


    //EFFECTS: filters the list of songs for specified artist
    private void filterPlaylistByArtist() {
        if (playlist.getNumberOfSongs() == 0) {
            System.out.println("\nPlaylist is empty");
        } else {
            System.out.println("Enter artist name to filter");
            String artistName = input.next();
            if (playlist.containsArtist(artistName)) {
                System.out.print("" + playlist.songsByArtistName(artistName) + "");
            } else {
                System.out.print("There are no songs in playlist by this artist");
            }
        }
    }

    private void savePlaylist() {
        try {
            jsonWriter.open();
            jsonWriter.write(playlist);
            jsonWriter.close();
            System.out.println("Saved " + playlist.getPlaylistName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file:" + JSON_STORE);
        }
    }

    private void loadPlaylist() {
        try {
            playlist = jsonReader.read();
            System.out.println("Loaded " + playlist.getPlaylistName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}


