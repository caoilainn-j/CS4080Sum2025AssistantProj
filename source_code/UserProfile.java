import java.util.ArrayList;
import java.util.HashMap;

class UserProfile {
    private String name;
    @SuppressWarnings("unused")
    private final int age;
    private final HashMap<String, ArrayList<String>> preferences = new HashMap<>();
    private boolean isPremium;
    private AIAssistant AI;
    private MusicAssistant MusicAI;
    private FitnessAssistant FitnessAI;

    // Constructor for a UserProfile Object with necessary details
    public UserProfile(String name, int age) {
        this.name = name;
        this.age = age;
        this.isPremium = false;
    }


    /**************************
     * function: generatePreferences
     * 
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