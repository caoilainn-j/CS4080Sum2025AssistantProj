/***********************************************************************
* Caoilainn Johnsson
* BroncoID: 017558918
* CS 4080, Summer 1 2025
* Assignment 2/3 : Design and Implement an AI Assistant
* MusicAssistant Class: Contains the Constructor for a MusicAssistant object,
                        as well as the necessary methods to read and manipualte
                        UserProfile data and inputs; also is the main force in
                        keeping track of and recording UserProfile preferences of songs
                        based on the choices / requests they make in their methods
************************************************************************/

import java.util.Random;
import java.util.Scanner;

class MusicAssistant extends AIAssistant {
    // categorized song list based on genre, represented by 2-D String Array
    final static private String [][] categorizedSongs = {
        // "pop" category
        {"Song1", "Song2", "Song3", "Song4", "Song5"},
        // "rock" category
        {"Song6", "Song7", "Song8", "Song9", "Song10"},
        // "country" category
        {"Song11", "Song12", "Song13", "Song14", "Song15"},
        // "rap" category
        {"Song16", "Song17", "Song18", "Song19", "Song20"},
        // "electronic" category
        {"Song21", "Song22", "Song23", "Song24", "Song25"}
    };

    // constructor for MusicAssistant Object
    public MusicAssistant() {
        assistantActive = true;
    }

    @Override
    // same documenation purposes as in AIAssistant, simply meant to greet the user in a unique way (hence the override)
    protected void greetUser() {
        System.out.println("\nGreat day for some tunes, " + activeUser.getName() + "!");
    }

    
    /**************************
     * function: recommendSong
     * purpose: will recommend a song from an existing String[][] variable that can depend on user request and/or user
     *          preferences (if they exist); is called by the AIAssistant class when a user requests that they want a
     *          song recommended
     * parameters: none
     * return type: void; prints the song recommended based on user input / preferences
     *************************/
    protected void recommendSong() {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        System.out.println("""
        \nHere is a list of Genres to choose from:
            - Pop
            - Rock
            - Country
            - Rap
            - Electronic
        """);
        System.out.print("What kind of genre would you like to hear from? (Or say surprise me): ");
        String choice = sc.nextLine();
        choice = choice.toUpperCase();

        // check to see if this is a new choice that user hasn't made before, and if it is new add it to preferences
        if(!this.getCurrentUser().getPreferences("Genres").contains(choice) && !choice.equals("SURPRISE ME")) {
            this.getCurrentUser().getPreferences("Genres").add(choice);
        }

        // pick a random song based on previous choices, or a completely random song from all songs if no preferences are stored
        if(choice.equals("SURPRISE ME")) {
            choice = this.getGenreByPreference();
        }

        String pickedSong;
        // filters through each possible choice the user can make for song category
        switch (choice) {
            case "POP" -> {
                pickedSong = this.getSong(0);
            }
            case "ROCK" -> {
                pickedSong = this.getSong(1);
            }
            case "COUNTRY" -> {
                pickedSong = this.getSong(2);
            }
            case "RAP" -> {
                pickedSong = this.getSong(3);
            }
            case "ELECTRONIC" -> {
                pickedSong = this.getSong(4);
            }
            case "No previous preferences found" -> {
                // recommends a completely random song if a user has no recorded preferences in its variable
                int randomIndex1 = (int) r.nextInt(categorizedSongs.length);
                pickedSong = this.getSong(randomIndex1);
                System.out.println("Since you have no previous preferences, we recommend you " + pickedSong);
            }
            default -> {
                pickedSong = "NA";
            }
        }

        // MusicAssistant read in a value that did not match any currently existing genres
        if(pickedSong.equals("NA")) {
            System.out.println("Sorry, I didn't understand your answer. Please try again.");
        }
        // a song was able to be picked based on the user's current request, or a "surprise me" request was made
        // and the user had existing preferences stored in its variable
        else if(!choice.equals("No previous preferences found")){
            System.out.println("Based on your previous preferences, I think you will like " + pickedSong);
        }
    }

    /**************************
     * function: getSong
     * purpose: fetches the song from the String[][] categorizedSongs variable randomly, 
     *          utilizing the built-in java import Random()
     * parameter: int indexOfGenre, the row in which the genre the user picked is found (predetermined)
     * return type: String, the song found within the array found at the specified index
     *************************/
    private String getSong(int indexOfGenre) {
        Random r = new Random();
        int randomIndex = (int) r.nextInt(categorizedSongs[indexOfGenre].length);
        return categorizedSongs[indexOfGenre][randomIndex];
    }

    /**************************
     * function: getGenreByPreference
     * purpose: goes to the UserProfile's variable preference, searches by the key Genre, and picks a random
     *          genre preference found within the ArrayList found at that key
     * parameter: none
     * return type: String, the genre found at a random point in the UserProfile's variable preferences
     *************************/
    private String getGenreByPreference() {
        if (!this.getCurrentUser().getPreferences("Genres").isEmpty()) {
            Random r = new Random();
            int randomIndex = (int) r.nextInt(this.getCurrentUser().getPreferences("Genres").size());
            return this.getCurrentUser().getPreferences("Genres").get(randomIndex);
        }
        else {
            return "No previous preferences found";
        }
    }
}