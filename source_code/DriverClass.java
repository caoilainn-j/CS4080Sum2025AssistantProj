/***********************************************************************
* Caoilainn Johnsson
* BroncoID: 017558918
* CS 4080, Summer 1 2025
* Assignment 2/3 : Design and Implement an AI Assistant
* DriverClass Class: Contains the main method for the program that will
                     "activate" the AI Assistant and start / end the 
                     program via specific user inputs (is the intended
                     program file to be run via the 'java' command)
************************************************************************/

import java.util.Scanner;

public class DriverClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AIAssistant newAI = new AIAssistant();
        System.out.println("Hello! I'm a virtual assistant designed to help you out.");
        // implements basic command line parsing via a loop that "lsitens" for user input to either
        // continue looping (asking for commands) or terminate the program session
        while(newAI.getAIActivity()) {
            // introduction and creation of a profile (if necessary)
            if(newAI.getCurrentUser() == null) {
                System.out.println("\nLet's start with creating a profile for you!");
                newAI.createNewAccount();
            }
            
            System.out.print("\n\nWhat can I do for you today " + newAI.getCurrentUser().getName() + "? ");
            String command = sc.nextLine();
            command = command.toUpperCase();
            
            // transfers the request of a user to the AIAssistant object
            newAI.handleRequest(newAI.getCurrentUser(), command);
        }
    }
}