import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class AIAssistant {
    private static ArrayList<UserProfile> users;
    private UserProfile activeUser;
    private boolean assistantActive;
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
        users = new ArrayList<>();
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

    private UserProfile createProfile(String n, int a) {
        UserProfile currentUser = new UserProfile(n, a);
        users.add(currentUser);
        return currentUser;
    }

    private void greetUser() {
        System.out.println("Good day, " + activeUser.getName() + "!");
    }

    private String getDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        String formattedDate = currentDate.format(pattern);
        return formattedDate;
    }

    private String getJoke() {
        Random r = new Random();
        int jokeIndex = (int) r.nextInt(jokes.length);
        return jokes[jokeIndex];    
    }

    private void upgradeAccount() {
        Scanner sc = new Scanner(System.in);
        if(!this.activeUser.getAccountDetails()) {
            System.out.print("Are you sure you want to upgrade your account? The card on file will be charged. ");
            String ans = sc.nextLine();
            if(ans.toUpperCase().equals("YES")) {
                this.activeUser.setPremium(true);
                if(this.activeUser.getAccountDetails()) {
                    System.out.println("Your account was successfully upgraded!");
                }
            }
            else {
                System.out.println("Your account was not upgraded.");
            }
        }
        else {
            System.out.println("You are already a Premium member!");
        }
    }

    public void createNewAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the name to be associated with the account: ");
        String name = sc.nextLine();

        System.out.print("\nAwesome! Can I also get the age of the account user for verificiation purposes? ");
        int age = sc.nextInt();
        sc.nextLine();

        UserProfile currentUser = this.createProfile(name, age);
        // "log profile in"
        this.setCurrentUser(currentUser);
        System.out.println("\nAmazing, I'll go ahead and create your account for you, " + this.getCurrentUser().getName());
    }

    private void switchAccounts() {
        Scanner sc = new Scanner(System.in);
        if (users.size() > 1) {
            System.out.println("Here is a list of all user accounts available to switch into: ");
            for(UserProfile user : users) {
                System.out.println(user.getName());
            }

            System.out.print("\nWhich account were you wanting to change into? ");
            String swapUser = sc.nextLine();
        
            for(UserProfile user : users) {
                if(swapUser.equals(user.getName())) {
                    this.setCurrentUser(user);
                    break;
                }
            }

            if(this.getCurrentUser().getName().equals(swapUser)) {
                System.out.println("Alright, I've logged you in under " + this.getCurrentUser().getName() + "!");
            }
            else {
                System.out.println("Sorry, I was unable to find an account associated with that name. Maybe try a different name or create a new account?");
            }
        }
        else {
            System.out.println("Sorry, you're logged into the only existing user account! Maybe try creating a new one?");
        }
        
    }

    
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
                         - Tell Me the Date
                         - Tell Me a Joke
                         - Recommend an Artist
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

                // handled by musicAssistant AIAssistant subclass (to-do)
                case "RECOMMEND AN ARTIST" -> {
                    // AIAssistant newMusicAI = new MusicAssistant();
                }

                // handled by fitnessAssistant AIAssistant subclass (to-do)
                case "RECOMMEND A WORKOUT" -> {
                    // AI Assistant newFitnessAI = new FitnessAssistant();
                }

                default -> {
                    System.out.println("\nSorry, I didn't recognize any of those commands, maybe try again?");
                }
            }
            
            boolean continueAsking = true;
            while(continueAsking) {
                System.out.print("\nIs there anything else I can do for you today? ");
                String response = sc.nextLine();
                switch (response.toUpperCase()) {
                    case "YES" -> continueAsking = false;
                    case "NO"  -> {
                        this.setAIActivity(false);
                        continueAsking = false;
                        System.out.println("\nThanks so much for chatting today! Hope to see you again :)");
                    }
                    default -> System.out.println("\nSorry, I didn't recognize any of those commands, maybe try again?");
                }
            }
            
        }

    }
    
    /*
    public void generateResponse(UserProfile u) {

    }
    */
    