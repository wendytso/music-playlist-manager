package ui;


import model.Playlist;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

import javax.swing.*;


//Playlist maker GUI
//Code based on: CPSC 210 AlarmSystem
public class PlaylistMakerGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/playlist.json";

    private Playlist playlist;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private JPanel desktop;
    private JPanel topPanel;
    private JPanel bottomPanel;

    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private MyTextArea textArea;
    private JLabel likeImage;

    private JTextField nameTextField;
    private JTextField artistTextField;
    private JTextField genreTextField;

    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;

    private ImageIcon newIcon;
    private ImageIcon volumeIcon;
    private ImageIcon searchIcon;
    private ImageIcon likeIcon;

    private JMenuItem newItem;
    private JMenuItem volumeItem;
    private JMenuItem searchItem;
    private JMenuItem undoItem;
    private JMenuItem copyItem;
    private JMenuItem selectItem;
    private JMenuItem contactItem;
    private JMenuItem aboutItem;


    //EFFECTS: initialize and set up the desktop with added top and bottom panels
    //initialize JsonWriter and JsonReader
    public PlaylistMakerGUI() throws FileNotFoundException {
        super("PlaylistMaker");
        playlist = new Playlist("My Playlist");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        desktop = new JPanel(new BorderLayout());
        desktop.setPreferredSize(new Dimension(1280, 720));
        topPanel = new JPanel(new BorderLayout());
        makeTopPanelComponents();
        bottomPanel = new JPanel(new GridLayout(1, 6));
        makeBottomPanelComponents();
        makeButtons();

        menuBar = new JMenuBar();
        makeMenuBar();

        setJMenuBar(menuBar);

        desktop.add(topPanel, BorderLayout.CENTER);
        desktop.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(desktop);
        setTitle("Playlist Maker");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    //EFFECTS: returns playlist
    public Playlist getPlaylist() {
        return playlist;
    }


    //EFFECTS: helper method to create menu bar elements and initialize menu bar items
    //Visual purposes only, no function has been implemented
    private void makeMenuBar() {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        newItem = new JMenuItem("New");
        volumeItem = new JMenuItem("Volume");
        searchItem = new JMenuItem("Search");

        undoItem = new JMenuItem("Undo");
        copyItem = new JMenuItem("Copy");
        selectItem = new JMenuItem("Select");

        contactItem = new JMenuItem("Contact Support");
        aboutItem = new JMenuItem("About");

        addMenuBarItems();
        addMenuBarIcons();
    }

    //EFFECTS: helper method to create menu bar items
    //Visual purposes only, no function has been implemented
    private void addMenuBarItems() {

        fileMenu.add(newItem);
        fileMenu.add(volumeItem);
        fileMenu.add(searchItem);

        editMenu.add(undoItem);
        editMenu.add(copyItem);
        editMenu.add(selectItem);

        helpMenu.add(contactItem);
        helpMenu.add(aboutItem);
    }

    //EFFECTS: helper method to create menu bar item icons and set them to the menu bar items
    //Visual purposes only, no function has been implemented
    private void addMenuBarIcons() {
        newIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/newIcon.png"));
        volumeIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/volumeIcon.png"));
        searchIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/searchIcon.png"));

        newItem.setIcon(newIcon);
        volumeItem.setIcon(volumeIcon);
        searchItem.setIcon(searchIcon);
    }


    // EFFECTS: make a non-editable text area and scroll bar to be added
    // to the top panel of the desktop to view songs in playlist
    // add graphical component to background of textArea
    private void makeTopPanelComponents() {
        textArea = new MyTextArea(10, 400);
        textArea.setEditable(false);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/playlistImage.jpg"));
        textArea.setImage(icon);

        scrollPane = new JScrollPane(textArea);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        JButton likeButton = new JButton("Like");
        likeButton.addActionListener(this);
        topPanel.add(likeButton, BorderLayout.WEST);

    }

    // EFFECTS: make the text fields and labels for user text input to be added to the bottom panel of the desktop
    private void makeBottomPanelComponents() {
        nameTextField = new JTextField();
        artistTextField = new JTextField();
        genreTextField = new JTextField();
        bottomPanel.add(new JLabel("Song Name:"));
        bottomPanel.add(nameTextField);
        bottomPanel.add(new JLabel("Artist:"));
        bottomPanel.add(artistTextField);
        bottomPanel.add(new JLabel("Genre:"));
        bottomPanel.add(genreTextField);
    }


    //EFFECTS: helper method to make the buttons with ActionListener to be added to the bottom panel of the desktop
    private void makeButtons() {
        JButton addSongButton = new JButton("Add");
        JButton removeSongButton = new JButton("Remove");
        JButton viewPlaylistButton = new JButton("View");
        JButton shufflePlaylistButton = new JButton("Shuffle");
        JButton clearPlaylistButton = new JButton("Clear");
        JButton savePlaylistButton = new JButton("Save");
        JButton loadPlaylistButton = new JButton("Load");

        addSongButton.addActionListener(this);
        removeSongButton.addActionListener(this);
        viewPlaylistButton.addActionListener(this);
        shufflePlaylistButton.addActionListener(this);
        clearPlaylistButton.addActionListener(this);
        savePlaylistButton.addActionListener(this);
        loadPlaylistButton.addActionListener(this);

        bottomPanel.add(addSongButton);
        bottomPanel.add(removeSongButton);
        bottomPanel.add(viewPlaylistButton);
        bottomPanel.add(shufflePlaylistButton);
        bottomPanel.add(clearPlaylistButton);
        bottomPanel.add(savePlaylistButton);
        bottomPanel.add(loadPlaylistButton);
    }


    // EFFECTS: make a call to different method depending on the button that is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("Add")) {
            addSong();
        } else if (command.equalsIgnoreCase("Remove")) {
            removeSong();
        } else if (command.equalsIgnoreCase("View")) {
            viewPlaylist();
        } else if (command.equalsIgnoreCase("Shuffle")) {
            shufflePlaylist();
        } else if (command.equalsIgnoreCase("Clear")) {
            clearPlaylist();
        } else if (command.equalsIgnoreCase("Save")) {
            savePlaylist();
        } else if (command.equalsIgnoreCase("Load")) {
            loadPlaylist();
        } else if (command.equalsIgnoreCase("Like")) {
            likeSong();
        }
    }

    //EFFECT: displays a heart image upon clicking the like button
    private void likeSong() {
        likeIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/likeIcon.png"));
        likeImage = new JLabel(likeIcon);
        topPanel.add(likeImage, BorderLayout.NORTH);
        setVisible(true);
    }


    //MODIFIES: playlist
    //EFFECTS: adds a song with a name, artist, and genre to the playlist.
    //Show an error message if any of the three input fields are left blank
    private void addSong() {
        Song newSong = new Song(null, null, null);
        String name = nameTextField.getText();
        String artist = artistTextField.getText();
        String genre = genreTextField.getText();


        if (name.isEmpty() || genre.isEmpty() || artist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can't leave name, genre or artist empty");
        } else {
            newSong.setSongTitle(name);
            newSong.setArtist(artist);
            newSong.setGenre(genre);

            playlist.addSong(newSong);
            System.out.print("Song added");

            JOptionPane.showMessageDialog(null, "Song added");
        }
    }

    //MODIFIES: playlist
    //EFFECTS: removes one of the songs from the playlist
    private void removeSong() {
        if (playlist.getNumberOfSongs() == 0) {
            JOptionPane.showMessageDialog(null, "Playlist is empty");
        } else {
            Object [] options = new Object[playlist.getNumberOfSongs()];
            for (int i = 0; i < playlist.getNumberOfSongs(); i++) {
                options[i] = playlist.getSongAtIndex(i);
            }
            int removeSelected = JOptionPane.showOptionDialog(
                    this,
                    "Select song to remove","Remove Song",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (removeSelected >= 0 && removeSelected < playlist.getNumberOfSongs()) {
                playlist.removeSong(removeSelected);
                JOptionPane.showMessageDialog(this, "Song has been removed from playlist");
            } else {
                JOptionPane.showMessageDialog(this, "That is not a valid selection");
            }
        }
    }

    //EFFECTS: displays all the songs that were inputted into the playlist
    private void viewPlaylist() {
        if (playlist.getNumberOfSongs() == 0) {
            textArea.setText("\n Playlist is empty");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < playlist.getNumberOfSongs(); i++) {
                stringBuilder.append("\n#" + (i + 1) + " ");
                stringBuilder.append("" + playlist.getSongAtIndex(i) + "");
                stringBuilder.append(" by " + playlist.getArtistAtIndex(i) + " - ");
                stringBuilder.append("" + playlist.getGenreAtIndex(i));
            }
            textArea.setText(stringBuilder.toString());
        }
    }

    //MODIFIES: playlist
    //EFFECTS: rearranges all the songs in the playlist, clears the textArea panel
    //then rebuilds the strings of the songs with the new arrangement
    private void shufflePlaylist() {
        if (playlist.getNumberOfSongs() == 0) {
            textArea.setText("\n Playlist is empty");
        } else {
            Collections.shuffle(playlist.getListOfSongs());
            textArea.setText("");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < playlist.getNumberOfSongs(); i++) {
                stringBuilder.append("\n#" + (i + 1) + " ");
                stringBuilder.append("" + playlist.getSongAtIndex(i) + "");
                stringBuilder.append(" by " + playlist.getArtistAtIndex(i) + " - ");
                stringBuilder.append("" + playlist.getGenreAtIndex(i));
            }

        }

    }

    //MODIFIES: playlist
    //EFFECTS: clears all the songs in the playlist
    private void clearPlaylist() {
        if (playlist.getNumberOfSongs() == 0) {
            textArea.setText("\n Playlist is empty");
        } else {
            playlist.clearPlaylist();
            textArea.setText("\n Playlist has been cleared");
        }
    }

    //EFFECTS: saves the playlist into the JSON_STORE
    private void savePlaylist() {
        try {
            jsonWriter.open();
            jsonWriter.write(playlist);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved " + playlist.getPlaylistName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file:" + JSON_STORE);
        }
    }

    //EFFECTS: loads the playlist last saved from the JSON_STORE
    private void loadPlaylist() {
        try {
            playlist = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Loaded " + playlist.getPlaylistName() + " from " + JSON_STORE);
            viewPlaylist();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }
}


