import java.util.Random;
import java.util.Scanner;


class MusicAssistant extends AIAssistant {
    // need to put in actual songs, just filler spots for now
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
    protected void greetUser() {
        System.out.println("\nGreat day for some tunes, " + activeUser.getName() + "!");
    }

    
    /**************************
     * function: recommendSong
     * 
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
        if(!this.getCurrentUser().getPreferences("Genres").contains(choice) && !choice.equals("SURPRISE ME")) {
            this.getCurrentUser().getPreferences("Genres").add(choice);
        }

        if(choice.equals("SURPRISE ME")) {
            choice = this.getGenreByPreference();
        }

        String pickedSong;
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
                int randomIndex1 = (int) r.nextInt(categorizedSongs.length);
                pickedSong = this.getSong(randomIndex1);
                System.out.println("Since you have no previous preferences, we recommend you " + pickedSong);
            }
            default -> {
                pickedSong = "NA";
            }
        }

        if(pickedSong.equals("NA")) {
            System.out.println("Sorry, I didn't understand your answer. Please try again.");
        }
        else if(!choice.equals("No previous preferences found")){
            System.out.println("Based on your previous preferences, I think you will like " + pickedSong);
        }
    }

    /**************************
     * function: getSong
     * 
     *************************/
    private String getSong(int indexOfGenre) {
        Random r = new Random();
        int randomIndex = (int) r.nextInt(categorizedSongs[indexOfGenre].length);
        return categorizedSongs[indexOfGenre][randomIndex];
    }

    /**************************
     * function: getGenreByPreference
     * 
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