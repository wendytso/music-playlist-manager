package ui;

import java.io.FileNotFoundException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//EFFECTS: runs new playlist maker
public class Main {
    public static void main(String[] args) {
        try {
            PlaylistMakerGUI playlistMakerGUI = new PlaylistMakerGUI();
            playlistMakerGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    playlistMakerGUI.getPlaylist().printEventLog();
                    super.windowClosing(e);
                }
            });
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
