/***********************************************************************
* Caoilainn Johnsson
* BroncoID: 017558918
* CS 4080, Summer 1 2025
* Assignment 2/3 : Design and Implement an AI Assistant
* AIAssistant Class: Contains the Constructor for an AIAssistant object,
                     as well as necessary functions to handle specific user
                     requests taken in from the DriverClass.java file
************************************************************************/

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class AIAssistant {
    protected ArrayList<UserProfile> users = new ArrayList<>(); // contains the list of all UserProfile objects created in the session
    protected UserProfile activeUser; // keeps track of the UserProfile object the AIAssistant is currently taking commands from
    protected boolean assistantActive; // ensures the AIAssistant object will keep taking requests ntil "turned off" by specific user inputs / requests
    // list of "jokes" that AIAssistant can return when requested by user
    private final String[] jokes = {
        "Why don't skeletons fight each other? Because they don't have the guts!",
        "What's brown and sticky? A stick!",
        "I invented a new word: plagiarism.",
        "I don't trust stairs. They're always up to something.",
        "What do you call a cow with no legs? Ground beef.",
        "Why did the man get hit by a bike every day? He was stuck in a vicious cycle.",
    };

    // constructor for AIAssistant Object
    public AIAssistant() {
        assistantActive = true;
    }

    /**************************
     * function: createProfile
     * purpose: creates a new user basedon the given specifications and creates a MusicAssistant and a 
     *          FitnessAssistant AI objects to help with all possible user inputs / requests; also 
     *          adds the new UserProfile bject to the list of UserProfile objects the AIAssistant 
     *          keeps track of
     * parameters: String n, the name of the User given
     * return type: the UserProfile object that was created with the given specifications
     *************************/
    private UserProfile createProfile(String n) {
        UserProfile currentUser = new UserProfile(n);
        currentUser.setMainAI(this);

        // create a musicAssistant "personality"
        MusicAssistant newM = new MusicAssistant();
        currentUser.setMusicAI(newM);

        // create a fitnessAssistant "personality"
        FitnessAssistant newF = new FitnessAssistant();
        currentUser.setFitnessAI(newF);

        this.users.add(currentUser);
        return currentUser;
    }

    // "getter" methods for AIAssistant variables

    public UserProfile getCurrentUser() {
        return this.activeUser;
    }

    public boolean getAIActivity() {
        return this.assistantActive;
    }

    
    // "setter" methods for AIAssistant variables

    public void setCurrentUser(UserProfile user) {
        this.activeUser = user;
    }

    public void setAIActivity(boolean arg) {
        this.assistantActive = arg;
    }

    
    
    // all available functions for AI to perform for a user

    /**************************
     * function: greetUser
     * purpose: prints a statement "greeting" the user
     * parameters: none
     * return type: void
     *************************/
    protected void greetUser() {
        System.out.println("Good day, " + activeUser.getName() + "!");
    }

    /**************************
     * function: getDate
     * purpose: utilizes built-in Java packages to grab the current local date, as well as the
     *          available objects (LocalDate, DateTimeFormatter) to dispaly the current date.
     * parameters: none
     * return type: String; the cuurrent date formatted properly for easy understanding by user
     *************************/
    protected String getDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        String formattedDate = currentDate.format(pattern);
        return formattedDate;
    }

    /**************************
     * function: getJoke
     * purpose: accesses the static variable jokes[][] to grab a joke, grabs a joke "randomly" via
     *          the Random() object utlilizing built-in Java packages
     * parameters: none
     * return type: String, the "joke" found in String[][] jokes by utlizing Random() object
     *************************/
    protected String getJoke() {
        Random r = new Random();
        int jokeIndex = (int) r.nextInt(jokes.length);
        return jokes[jokeIndex];    
    }

    /**************************
     * function: upgradeAccount
     * purpose: accesses the UserProfile variable "isPremium," checks to see if they are a premium member,
     *          and re-confirms that the user woudl liek to upgrade if eh user is not already a premium
     *          member
     * parameters: none
     * return type: void
     *************************/
    protected void upgradeAccount() {
        Scanner sc = new Scanner(System.in);
        if(!this.activeUser.getAccountDetails()) {
            System.out.print("Are you sure you want to upgrade your account? The card on file will be charged. ");
            String ans = sc.nextLine();
            // if "yes" is given, the user's isPremium varibale will be updated to true
            if(ans.toUpperCase().equals("YES")) {
                this.activeUser.setPremium(true);
                if(this.activeUser.getAccountDetails()) {
                    System.out.println("Your account was successfully upgraded!");
                }
            }
            // if "no" or any other answer is given, it will not update UserProfile's isPremium variable
            else {
                System.out.println("Your account was not upgraded.");
            }
        }
        else {
            System.out.println("You are already a Premium member!");
        }
    }

    /**************************
     * function: createNewAccount
     * purpose: acts as the intermediary step to create a profile for a given UserProfile object, and sets the newly-created
     *          user as a the current "active" user (i.e. the one whose name will be referred to by the Assistant)
     * parameters: none
     * return type: void
     *************************/
    public void createNewAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the name to be associated with the account: ");
        String name = sc.nextLine();
        // calls createProfile() with proper parameters and creates a new UserProfile object based on data from createObject()
        UserProfile currentUser = this.createProfile(name);
        // "log profile in"
        this.setCurrentUser(currentUser);
        System.out.println("\nAmazing, I'll go ahead and create your account for you, " + this.getCurrentUser().getName());
        // calls generatePreferences() from UserProfile to establish a place to store their music and fitness preferences
        this.getCurrentUser().generatePreferences();
    }

    /**************************
     * function: switchAccounts
     * purpose: changes the currentUser variable for the currently-active AIAssiatnt object by offering teh liost of all
     *          users the AI has knwoeldge of, prompting the user to type one of the names, and either changing to the requested account or 
     *          saying it couldn't (occurs when user puts in a "name" that isn't in the ArrayList of UserProfiles variable)
     *          **NOTE: is case-sensitive, anems will not be the same unless typed exactly as dispalyed by the Assistant when function
     *                  is first called
     * parameters: none
     * return type: void
     *************************/
    protected void switchAccounts() {
        Scanner sc = new Scanner(System.in);
        if (users.size() > 1) {
            // shows all avaible UserProfile objects to switch into
            System.out.println("Here is a list of all user accounts available to switch into: ");
            for(UserProfile user : this.users) {
                System.out.println(user.getName());
            }

            System.out.print("\nWhich account were you wanting to change into? ");
            String swapUser = sc.nextLine();

            // checks to see if user input matches the currently-active UserProfile
            if (swapUser.equals(this.getCurrentUser().getName())) {
                System.out.println("You are already logged in under " + swapUser + "!");
            }
            // else, goes to see if user input matches any of the UserProfile objects stored in AIAssistant's variable users
            else {
                for(UserProfile user : this.users) {
                    if(swapUser.equals(user.getName())) {
                        this.setCurrentUser(user);
                        break;
                    }
                }

                // if a matching name is found, switch and make user-inputted name the new currentUser
                if(this.getCurrentUser().getName().equals(swapUser)) {
                    System.out.println("Alright, I've logged you in under " + this.getCurrentUser().getName() + "!");
                }
                // else, don't change currentUser and tell user they coudl not find that name
                else {
                    System.out.println("Sorry, I was unable to find an account associated with that name. Maybe try a different name or create a new account?");
                }
            }
        }
        else {
            System.out.println("Sorry, you're logged into the only existing user account! Maybe try creating a new one?");
        }
        
    }

    /**************************
     * function: handleRequest
     * purpose: handles the user input and calls the corresponding function to the request via a switch; also creates a loop
     *          at bottom to ask if the user needs help with anything else
     * parameters: Userprofile u, the currently-active user; String command, the request typed in by the user
     * return type: void
     *************************/
    public void handleRequest(UserProfile u, String command) {
        Scanner sc = new Scanner(System.in);
        // use a switch to parse through user input to validate a command
            switch (command) {
                // potential user requests that AI should be able to handle
                case "OPTIONS" -> {
                    System.out.println("""
                    Here are a list of options of what I can currently do for you!
                         - Greet me
                         - Upgrade my Account
                         - Create a New Account
                         - Switch Accounts
                         - Tell Me the Date
                         - Tell Me a Joke
                         - Recommend a Song
                         - Recommend a Workout
                    """);
                }
                case "GREET ME" -> this.greetUser();
                case "UPGRADE MY ACCOUNT" -> {
                    this.upgradeAccount();
                }
                case "CREATE A NEW ACCOUNT" -> {
                    this.createNewAccount();
                }
                case "SWITCH ACCOUNTS" -> {
                    this.switchAccounts();
                }
                case "TELL ME THE DATE" -> {
                    System.out.println("\nToday's Date is " + this.getDate());
                }
                case "TELL ME A JOKE" -> {
                    System.out.println(this.getJoke());
                }

                // handled by musicAssistant AIAssistant subclass
                case "RECOMMEND A SONG" -> {
                    // ensures that MusicAssistant is able to get access to AIAssistant's current user
                    this.getCurrentUser().getMusicAI().setCurrentUser(this.getCurrentUser());
                    this.getCurrentUser().getMusicAI().greetUser();
                    this.getCurrentUser().getMusicAI().recommendSong();
                }

                // handled by fitnessAssistant AIAssistant subclass
                case "RECOMMEND A WORKOUT" -> {
                    // ensures that FitnessAssistant is able to get access to AIAssistant's current user
                    this.getCurrentUser().getFitnessAI().setCurrentUser(this.getCurrentUser());
                    this.getCurrentUser().getFitnessAI().greetUser();
                    this.getCurrentUser().getFitnessAI().recommendWorkout();
                }

                default -> {
                    System.out.println("\nSorry, I didn't recognize any of those commands, maybe try again?");
                }
            }
            
            // initiates a loop to ask whetehr or not the user still needs any help / wants to give another command
            boolean continueAsking = true;
            while(continueAsking) {
                System.out.print("\nIs there anything else I can do for you today? ");
                String response = sc.nextLine();
                switch (response.toUpperCase()) {
                    case "YES" -> continueAsking = false;
                    case "NO"  -> {
                        // essentailly "deactivates" AI, terminating program by updating the variable that DriverClass checks to
                        // continue looping
                        this.setAIActivity(false);
                        continueAsking = false;
                        System.out.println("\nThanks so much for chatting today! Hope to see you again :)");
                    }
                    default -> System.out.println("\nSorry, I didn't recognize any of those commands, maybe try again?");
                }
            }
            
        }

    }
    