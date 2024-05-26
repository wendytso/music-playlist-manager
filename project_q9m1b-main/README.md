# My Personal Project


## What will the application do?
The application is intended to be used as a **music playlist tracker**. 
It lets users input information about the music that they enjoy listening to.

*Some possible features include:*
- listing recently played/ favourite songs and artists playlists 
- get statistics about the most frequently played songs 
- group songs according to the artist/ genre
- displaying pictures of the artists that are featured in the song 

## Who will use it?
Users include artists, musicians, and people who enjoy listening to music. 

## Why is this project of interest to you?
This project is of great significance to me as a musician and music-enjoyer.
There is not a single day that goes by without me listening to at least one song. 
Often, I'll be sporting a pair of headphones listening to my current favourite playlist on repeat. 
Not only is it something that I enjoy, it helps ground me, lift my mood and improve my motivation.
Because of the diversity in music genres, language and culture, music can connect audiences from around the world.
Thus, I believe the application can be used by a wide range of people. 


## User Stories
- As a user, I want to be able to add a song to a playlist
- As a user, I want to be able to view the list of the songs in my playlists
- As a user, I want to be able to filter the songs according to genre or artist
- As a user, I want to be able to remove a song from my list of songs in my playlist
- As a user, I want to be able to select a song from my playlist
- As a user, I want to be able to save my playlist to file (if I so choose)
- As a user, I want to be able to load my playlist from file (if I so choose)

//future stretch: As a user, I want to be able to select a song in my playlist and see a photo of the artist
//future stretch: As a user, I want to be able to be able to see statistics on how frequently the song is played


## Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by typing in the song, artist and genre text fields then clicking on the "Add Song" button, followed by the "View Playlist" button
- You can generate the first related action by clicking on the "Remove Song" button which will prompt you to remove a song option
- You can generate the second related action by clicking on the "Shuffle Playlist" or "Clear Playlist" buttons
- You can generate the second required action related to loading and saving the state of the application by clicking on the "Save Playlist" and "Load Playlist" buttons
- You can locate my visual component at the splash background upon running Main, icons under the "File" tab located on the menu bar and by clicking the "Like Button"
- You can save the state of my application by clicking on the "Save Playlist button
- You can reload the state of my application by clicking on the "Load Playlist" button


## Phase 4: Task 2
Sample of events that are logged and printed to the console after the user quits the application:
Tue Apr 11 16:54:22 PDT 2023Added song: Hello to Playlist
Tue Apr 11 16:54:34 PDT 2023Added song: Hold On Tight to Playlist
Tue Apr 11 16:54:42 PDT 2023Added song: Wallflower to Playlist
Tue Apr 11 16:55:04 PDT 2023Added song: Night Dancer to Playlist
Tue Apr 11 16:55:48 PDT 2023Added song: Void to Playlist
Tue Apr 11 16:56:11 PDT 2023Added song: Drunk to Playlist
Tue Apr 11 16:56:21 PDT 2023Removed song: Hello from Playlist
Tue Apr 11 16:56:26 PDT 2023Cleared playlist

## Phase 4: Task 3
Upon reflecting on the design of this project , there are a couple places where improvements can be made.
Making the Playlist class abstract would allow me to create different types of playlists with some shared/ common functionality and implementations
as opposed to making the Playlist class an interface where it would only have defined functionality and set methods but completely different implementations.
By doing so, I would also be able to avoid duplication and redundant code, and it would be easier to maintain multiple different playlists 
Another change that can be done is to encapsulate the fields and methods as many of the methods are currently set to public.
This public view of methods is unnecessary for users of the app, such as in the case of the getListOfSongs() in the Playlist class which exposes the entire LinkedList<Song> field 
or in the getter and setter methods in the Song class. Users do not need to know the details of the implementation for these methods.
In the Playlist class, it may be better to utilize the list's index. This is done in the removeSong() method, but not in the addSong() method.
Having a specified method that retrieves songs according to the specified index in the Playlist class would also reduce redundancy  during the implementation of the GUI. 
It would also help when making modifications to the implementations of songPlaylist field, as the details of the implementation are exposed and currently modifications would affect the Playlist class.
For improved flexibility, instead of LinkedList<Song>, it may be better to use the List<Song> interface as it would make it easier to switch to different implementations of the interface in the future. 
The design would also have been improved if there were enumbs of the genre field. 
Unlike titles and artists of the songs which are highly variable, there are a set number of genres in music that could have been defined as constants to limit the possible values and user input errors that may occur. 
Making some of the fields final and immutable would also improve the design of the code and make it less prone to being affected as the application is run.
This includes the title, artist, and genre fields as they are not changed once added to the playlist.
Finally, there are a few exceptions that should be thrown to improve the application to prevent crashing of tha pplication. 
This includes the IndexOutOfBounds Exception in the removeSong() method
(and in the addSong() method in the future if these design modifications are to be made) as well as the NullPointerException.
Although I have a "REQUIRES" clause specifying no empty playlists, it would be better to catch this so that when one of the filtering playlist methods are used such as
songsByArtistName() and songsOfGenreType(), it won't be called upon an empty playlist. 