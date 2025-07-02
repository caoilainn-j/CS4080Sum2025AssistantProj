## Overview
This project was created to fufill the requirements of Assignment 2 and 3: Designing and Implementing a Final Application of Your Own AI Assistant. The program is intended to run as a "back and forth" between the user and the "AI" assistant, which is able to perform a select set of tasks per user request. While elabroated further within code commentary, the AI assistant is able to:
 - Greet the user
 - Offer up a list of its current functionalities (from user to request from)
 - Upgrade the user's account
 - Create a new user account
 - Switch between user accounts
 - Tell the current date
 - Tell the user a joke
 - Recommend a song (per the MusicAssistant subclass)
 - Recommend a workout (per the FitnessAssistant subclass)



## Compiling and Running
This program is intended to run within any given computer terminal, and was tested within my own Windows Command Prompt. No additional imports are necessary as long as the given user has downloaded the Java Runtime Environment (JRE) and the Java Development Kit (JDK).

### Compilation
Once files are copied / downloaded, go to your computer's terminal and navigate towards the correct file path into where the "source_code" folder is located. After that, the only file that should need to be compiled is the DriverClass.java file via the command:
> `javac DriverClass.java`

Then, you can start / activate the Virtual Assistant via the command in tat same file path:
> `java DriverClass`

If for some reason the program does not run, each file within source_code may have to be compiled individually, in which case you can follow the same pattern as done for compiling DriverClass, just for all the other file names. 

### Running
Once the progam is running, the Assistant will first prompt you to create an account by asking you for your name (note that this must be done before any commands can be given to the Assistant, and data is not saved anywhere after the program stops running.)

The Assistant is programmed to take any user input, and can identify commands case-insensitive, but is still sensitive to whitespace. The Assistant should be relatively straightforward to "communicate" with as it asks you questions, but as a general reference here are some commands in proper syntax you can give it once you have filled in your "user information" and it asks "What can I do for you today?" :
- Greet me
- Upgrade my Account
- Create a New Account
- Switch Accounts
- Tell Me the Date
- Tell Me a Joke
- Recommend a Song
- Recommend a Workout

You can also give it the command `options` for the AI itself to show you the above options.

After completing each command, the AI will ask "Is there anything else I can do for you today?" in which the user can respond either yes or no, case-insensitive. If the user types yes, it will again prompt the question "What can I do for you?" to the user to type another command, and if the user types no the program will stop ("removing" the user and any saved preferences from the program's memory.) If the user types anything else, the AI will say it "doesn't understand" and will continue to prompt until a "yes" or "no" response is given (the same is true for the "What can I do for you?" prompt.)



## Concept Applications
This program is a general implemenation of Objected-Oriented Programming (OOP) principles, namely through the concepts of encapsulation, inheritance, and polymorphism; all classes are related to each other, primarily through a UserProfile object containing an AIAssistant object, a MusicAssistant object, and a FitnessAssistant object. On top of this, the MusicAssistant and FitnessAssistant classes are subclasses of the AIAssistant class, which allows it access to its variables and methods. Below such details are explained more in-depth:

### Encapsulation
Encapsulation is the OOP principle that data and methods that are interacting / requiring access to one another should be held in the same "bundle," more specifically a class here, to prevent access by other classes or methods without the proper permissions. This principle is essentailly the framework that is this project:
- User data is only held in the UserProfile class by creating an instance of the class as an object, and is only visible via methods within the UserProfile class that can be used by the AIAssistant class
- data on specific AIAssistant, MusicAssistant, and FitnessAssistant iinsatnces is only stored within their respective class, and if the Driver class / UserProfile class needed access to its data, it must use the public methods provided in order to access.

### Inheritance
Inheritance is the OOP principle that a base class can have serveral "derived" or "subclasses" that inherit its same properties while still being considered a separate "Object" class. This property is shown between the the AIAssistant class (the "superclass") and the MusicAssistant and FitnessAssistant classes (the "subclasses.") 
- Both subclasses are able to perform all the actions that the AIAssistant is able to, as well as access necessary variables from the UserProfile class (as AIAssistant Object has access to them.)
- Creates extensibility of the base class by allwoing specialized functions to be handled by a different "AI" personality that differs from the base (in this case, the MusicAssistant and the FitnessAssistant) -> allows for more functions to be present in subclasses that may not be relevant for a genral AIAssistant to use.
- Allows code reusability when prompting useer requests.

### Polymorphism
Polymorphism is the OOP principle that, simialr to inheritance, a superclass's subclasses have access to its methiods, but also have the ability to overwrite relevant functiosn to better suit the needs of the specialized subclass. 
- In this case, polymorphism is applied via method overloading, as the MusicAssiastnt and the Fitness Assistnat have different greetings for the user dependent on their personality.
- FitnessAssiatnt will have a different greeting compared to the MusicAssistant and to the AIAssistant