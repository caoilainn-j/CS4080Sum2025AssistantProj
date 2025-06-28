import java.util.HashMap;

public class UserProfile {
    private String name;
    @SuppressWarnings("unused")
    private final int age;
    @SuppressWarnings("FieldMayBeFinal")
    private HashMap<String, String> preferences = new HashMap<>();
    private boolean isPremium;
    // private AIAssistant AI;

    // Constructor for a UserProfile Object with necessary details
    // **WANT TO MAKE PRIVATE, LOOK AT PACKAGES IN JAVA**
    public UserProfile(String n, int a) {
        this.name = n;
        this.age = a;
        this.isPremium = false;
        // this.AI = new AIAssistant(this);
    }

    /**************************
     * function: generatePreferences
     * 
     *************************/
    public void generatePreferences() {
        preferences.put("Language", "English");
        preferences.put("Visual", "Light Mode");
        preferences.put("Time Zone", "US Pacific");

        // **implement ability for value to be an ArrayList of Strings of genres corresponding to user request and AI responses**
        preferences.put("Genres", null);
        preferences.put("Workouts", null);
        // potentially add more??
    }

    // "Getter" methods for UserProfile details

    public String getName() {
        return this.name;
    }

    public boolean getAccountDetails() {
        return this.isPremium;
    }

    public void getPreferences() {
        for(String key : preferences.keySet()) {
            System.out.println(key + ": " + preferences.get(key));
        }
    }



    // "Setter" methods for adjusting UserProfile details
    
    public void setName(String n) {
        this.name = n;
    }

    public void setPremium(boolean p) {
        this.isPremium = p;
    }

    public void setPreference(String key, String value) {
        preferences.replace(key, value);
    }

}