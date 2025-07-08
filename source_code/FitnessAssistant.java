/***********************************************************************
* Caoilainn Johnsson
* BroncoID: 017558918
* CS 4080, Summer 1 2025
* Assignment 2/3 : Design and Implement an AI Assistant
* FitnessAssistant Class: Contains the Constructor for a FitnessAssistant object,
                          as well as the necessary methods to read and manipualte
                          UserProfile data and inputs; also is the main force in
                          keeping track of and recording UserProfile preferences of workouts
                          based on the choices / requests they make in their methods
************************************************************************/

import java.util.Random;
import java.util.Scanner;

class FitnessAssistant extends AIAssistant {
    // categorized workout list based on part of body, represented by 2-D String Array 
    final static String[][] categorizedWorkouts = {
        // upper body workouts
        {"bicep curls", "lateral raises", "lower back rows", "chest press", "tricep pulldowns"},
        // lower body workouts
        {"bulgarain split squats", "calf raises", "romanian deadlifts", "lunges", "goblet squats"},
        // abdominal workouts
        {"sit-ups", "planks", "leg raises", "russian twists", "dead bugs"},
        // full body workouts
        {"deadlifts", "mountain climbers", "burpees", "jumping jacks", "hanging leg raises"},
        // cardio workouts
        {"the Stairmaster", "the Elliptical", "1 mile run", "sprints", "12 - 3 - 30 incline"}
    };

    // constructor for FitnessAssistant Object
    public FitnessAssistant() {
        assistantActive = true;
    }

    @Override
    // same documenation purposes as in AIAssistant, simply meant to greet the user in a unique way (hence the override)
    protected void greetUser() {
        System.out.println("\nGreat day for a workout, " + activeUser.getName());
    }

    /**************************
     * function: recommendWorkout
     * purpose: will recommend a workout from an existing String[][] variable that can depend on user request and/or user
     *          preferences (if they exist); is called by the AIAssistant class when a user requests that they want a
     *          workout recommended
     * parameters: none
     * return type: void; prints the workout recommended based on user input / preferences
     *************************/
    protected void recommendWorkout() {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        System.out.println("""
        Here is a list of workout types to choose from:
            - Upper Body
            - Lower Body
            - Abdominal
            - Full Body
            - Cardio
        """);
        System.out.print("What are you wanting to work out today? (Or say surprise me): ");
        String choice = sc.nextLine();
        choice = choice.toUpperCase();

        // check to see if this is a new choice that user hasn't made before, and if it is new add it to preferences
        if (!this.getCurrentUser().getPreferences("Workout Types").contains(choice) && !choice.equals("SURPRISE ME")) {
            this.getCurrentUser().getPreferences("Workout Types").add(choice);
        }

        // pick a random workout based on previous choices, or a completely random workout from all options if no preferences are stored
        if (choice.equals("SURPRISE ME")) {
            choice = this.getWorkoutCategoryByPreference();
        }

        String pickedWorkout;
        // filters through each possible choice the user can make for workout type
        switch (choice) {
            case "UPPER BODY" -> {
                pickedWorkout = this.getWorkout(0);
            }
            case "LOWER BODY" -> {
                pickedWorkout = this.getWorkout(1);
            }
            case "ABDOMINAL" -> {
                pickedWorkout = this.getWorkout(2);
            }
            case "FULL BODY" -> {
                pickedWorkout = this.getWorkout(3);
            }
            case "CARDIO" -> {
                pickedWorkout = this.getWorkout(4);
            }
            case "No previous preferences found" -> {
                int randomIndex1 = (int) r.nextInt(categorizedWorkouts.length);
                pickedWorkout = this.getWorkout(randomIndex1);
                System.out.println("Since you have no previous preferences, we recommend you do " + pickedWorkout);
            }
            default -> {
                pickedWorkout = "NA";
            }
        }

        if(pickedWorkout.equals("NA")) {
            System.out.println("Sorry, I didn't understand your answer. Please try again.");
        }   
        else if(!choice.equals("No previous preferences found")) {
            System.out.println("Based on your previous preferences, I think you will like doing " + pickedWorkout);
        }
    }

    /**************************
     * function: getWorkout
     * purpose: fetches the workout from the String[][] categorizedWorkouts variable randomly, 
     *          utilizing the built-in java import Random()
     * parameter: int indexOfWorkoutCategory, the row in which the workout type the user picked is found (predetermined)
     * return type: String, the wokrout found within the array found at the specified index
     *************************/
    private String getWorkout(int indexOfWorkoutCategory) {
        Random r = new Random();
        int randomIndex = (int) r.nextInt(categorizedWorkouts[indexOfWorkoutCategory].length);
        return categorizedWorkouts[indexOfWorkoutCategory][randomIndex];
    }

    /**************************
     * function: getWorkoutCategoryByPreference
     * purpose: goes to the UserProfile's variable preference, searches by the key Workout Types, and picks a random
     *          workout type preference found within the ArrayList found at that key
     * parameter: none
     * return type: String, the workout type found at a random point in the UserProfile's variable preferences
     *************************/
    private String getWorkoutCategoryByPreference() {
        if (!this.getCurrentUser().getPreferences("Workout Types").isEmpty()) {
            Random r = new Random();
            int randomIndex = (int) r.nextInt(this.getCurrentUser().getPreferences("Workout Types").size());
            return this.getCurrentUser().getPreferences("Workout Types").get(randomIndex);
        }
        else {
            return "No previous preferences found";
        }
    }
}