/***********************************************************************
* Caoilainn Johnsson
* BroncoID: 017558918
* CS 4080, Summer 1 2025
* Assignment 2/3 : Design and Implement an AI Assistant
* UserProfile Class: Contains the Constructor for a UserProfile object, as 
                     well as the necessary functions to access its variables/
                     data used in other classes and their functions
************************************************************************/

import java.util.ArrayList;
import java.util.HashMap;

class UserProfile {
    private String name;
    private final HashMap<String, ArrayList<String>> preferences = new HashMap<>();
    private boolean isPremium;
    private AIAssistant AI;
    private MusicAssistant MusicAI;
    private FitnessAssistant FitnessAI;

    // Constructor for a UserProfile Object with necessary details
    public UserProfile(String name) {
        this.name = name;
        this.isPremium = false;
    }


    /**************************
     * function: generatePreferences
     * purpose: creates the ArrayLists meant to store the respective genres / workout types that a user requests to see / hear from
     *          (implemented in the MusicAssistant / FitnessAssistant classes) in a HashMap mapping the key (either "Genres" or 
     *          "Workout Types") to an ArrayList conatining the requested types of music / workouts (se MusicAssistant and FitnessAssistant
     *          for further documentation)
     * parameters: none
     * return type: void; edits the preference variable for a UserProfile object
     *************************/
    public void generatePreferences() {
        ArrayList<String> userGenres = new ArrayList<>();
        ArrayList<String> userWorkouts = new ArrayList<>();
        // **implement ability for value to be an ArrayList of Strings of genres corresponding to user request and AI responses**
        preferences.put("Genres", userGenres);
        preferences.put("Workout Types", userWorkouts);
    }

    // "Getter" methods for UserProfile details

    public String getName() {
        return this.name;
    }

    public boolean getAccountDetails() {
        return this.isPremium;
    }

    public ArrayList<String> getPreferences(String key) {
        return preferences.get(key);
    }

    public AIAssistant getMainAI() {
        return this.AI;
    }

    public MusicAssistant getMusicAI() {
        return this.MusicAI;
    }

    public FitnessAssistant getFitnessAI() {
        return this.FitnessAI;
    }



    // "Setter" methods for adjusting UserProfile details
    
    public void setName(String n) {
        this.name = n;
    }

    public void setPremium(boolean p) {
        this.isPremium = p;
    }

    public void setPreference(String key, String value) {
        preferences.get(key).add(value);
    }

    public void setMainAI(AIAssistant a) {
        this.AI = a;
    }

    public void setMusicAI(MusicAssistant m) {
        this.MusicAI = m;
    }

    public void setFitnessAI(FitnessAssistant f) {
        this.FitnessAI = f;
    }

}