import java.util.Scanner;

public class DriverClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AIAssistant newAI = new AIAssistant();
        System.out.println("Hello! I'm a virtual assistant designed to help you out.");
        // implements basic command line parsing
        while(newAI.getAIActivity()) {
            // introduction and creation of a profile (if necessary)
            if(newAI.getCurrentUser() == null) {
                System.out.println("\nLet's start with creating a profile for you!");
                newAI.createNewAccount();
            }
            
            System.out.print("\n\nWhat can I do for you today " + newAI.getCurrentUser().getName() + "? ");
            String command = sc.nextLine();
            command = command.toUpperCase();

            newAI.handleRequest(newAI.getCurrentUser(), command);
        }
    }
}