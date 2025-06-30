import java.util.Random;
import java.util.Scanner;

class FitnessAssistant extends AIAssistant {
    // categorized workout list based on part of body, represented by 2-D String Array 
    final static String[][] categorizedWorkouts = {
        {"Work1", "Work2", "Work3", "Work4", "Work5"},
        {"Work6", "Work7", "Work8", "Work9", "Work10"},
        {"Work11", "Work12", "Work13", "Work14", "Work15"},
        {"Work16", "Work17", "Work18", "Work19", "Work20"},
        {"Work21", "Work22", "Work23", "Work24", "Work25"}
    };

    // constructor for FitnessAssistant Object
    public FitnessAssistant() {
        assistantActive = true;
    }

    @Override
    protected void greetUser() {
        System.out.println("\nGreat day for a workout, " + activeUser.getName());
    }

    /**************************
     * function: recommendWorkout
     * 
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
     * 
     *************************/
    private String getWorkout(int indexOfWorkoutCategory) {
        Random r = new Random();
        int randomIndex = (int) r.nextInt(categorizedWorkouts[indexOfWorkoutCategory].length);
        return categorizedWorkouts[indexOfWorkoutCategory][randomIndex];
    }

    /**************************
     * function: getWorkoutCategoryByPreference
     * 
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