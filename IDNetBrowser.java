//chris dale - data structures final project - this is IDNetBrowser.java 
//this is the Idea Network Browser aka user interface

//requires SocialNetwork class
//required nodes: Student node class, Idea node class

// need this tokenizer to parse string into pieces
import java.util.StringTokenizer;

// need this scanner to take input from terminal (command line)
import java.util.Scanner;

// needed to fix the strange print problem ~~~~idk maybe not needed, remnant~~~~
import java.io.PrintStream;

public class IDNetBrowser {
	public static void main(String[] args) {
		//ON START-UP:
		SocialNetwork theNet = new SocialNetwork(); 
		
		//add check for most recent data file, IDK how to do this...
		
		//get next line --> dataIn.nextLine()
		
		//while there is still more file to parse
			//accesses a user's data from current position in file
			//creates a Student node, fills Student's array with Idea nodes
			//populates hash table with email as key and Student Node as value
			//first two records will be award holders, email: award1 and award2
		
		// create scanner object to take user input
		Scanner scanner = new Scanner(System.in);
		
		// boolean governs while loop, takes input until 'quit' command
		boolean running=true;
		
		
		
		// setting up booleans & values that govern current user
		boolean admin = false;
		String adminPass = "password";
		boolean login = false;
		boolean quitting = false;
		Student user = null; 
		String inStr = "";
		
		// introduction to program printed just before user interaction
		System.out.println("Welcome to the Idea Network Browser!");
		System.out.println("A request for information can be rendered via the phrase: \"info\"");
		//MAIN LOOP: a while loop to perform user interaction
		while(running) {
			//checks that a user is logged in
			while(login==false && quitting == false){
				System.out.print("You are not currently logged in");
				System.out.print("Enter User Name or type \"new\" to make a new user :");
				inStr = scanner.nextLine();
				if (inStr.compareTo("quit")==0) {
					running = false; // this will terminate running
					quitting = true;
				}
				else if(inStr.compareTo("admin")==0){
					if(theNet.password(adminPass)){
						admin=true;
						login=true;
					}
					else{
						admin=false;
					}
				}
				else if(inStr.compareTo("login")==0){
					//dialogue for student to login
					//studentLogin
					//enter student's id to log in
					//find Student associated with that key value
					//Set that student to user
					/* NEEDS DOING */
				}
				else if(inStr.compareTo("new")==0){
					//dialogue for student to create account
					//createNewUser
					/* NEEDS DOING */
				}
				else {
					System.out.print("Try to enter you User Name again:");
					inStr = scanner.nextLine();
				}
			}
			
			//gets user input (if not quitting from login)
			if(quitting==false){
				System.out.print("What would you ask of me?");
				inStr = scanner.nextLine();
			}
			
			//quit command - terminates the loop
			if (inStr.compareTo("quit")==0 || quitting==true) { //may not need the "or" conditional
				running = false;	// this will terminate while loop
				System.out.println("It has been a pleasure; perhaps we shall meet again! \n");
				/* NEEDS WORK */
				//insert functionality here that writes a new user record file
				//
				//OUTPUT FILE WRITER NEEDED
				//
			}
			
			//log out command
			else if (inStr.compareTo("log out")==0){
				//double checks that you meant [whatever input text]
				if(theNet.doubleCheck(" you want to log out")){
					login=false;
					admin=false; //resets admin status
					System.out.println("Logging out... \n... \n... \n... \nLogged Out!");
				}
				else{
					System.out.println("Log Out Cancelled");
				}
				
			}
			
			//info command
			else if (inStr.compareTo("info")==0){
				System.out.println("Commands at one's disposal: info, quit, log out, search, SA (show awards), STI (show top ideas), SAU (show all users), SAD (show all data), SAI (show all ideas)");
				if(admin){System.out.println("Admin Commands: CNU (create new user), CUI (change user info)");}
			}

			
			//showTopIdeas - loop with options for user and admin
			else if (inStr.compareTo("STI")==0){
				//asks for number of ideas to show first
				//takes from priority queue
				//admin can award (removes idea from student, adds to awarded)
				//User can vote on ideas, iterates votes +1/-1
				System.out.println("How many ideas should be shown?");
				//int totalIdeas = theNet. //gets total number of ideas
				//int showNum = getNumber(totalIdeas); //gets number of ideas
				/* NEEDS WORK */
			}
			
			
			//showAllUsers - loop with options for user and admin
			else if (inStr.compareTo("SAU")==0) {
				//student can vote on ideas
				//admin can remove user or change user's info
				Student[] allUsers = theNet.showAllUsers();
				//length of the user list
				int theSize = allUsers.length();
				System.out.println("All users in database");
				if(!admin){System.out.println("As user, one may \"vote\" on a specific user's info, referenced by number");}
				if(admin){System.out.println("As Admin, one may \"remove\" or \"change\" the user's info, referenced by number");}
				String curStr = scanner.nextLine();
				while(curStr.compareTo("exit")!=0 || curStr.compareTo("cancel")!=0){
					if(admin){System.out.println("Admin Commands: \"remove\" to remove user, \"change\" to change user");}
					if(!admin){System.out.println("User Commands: type a number to select an idea to vote on, type cancel to exit");}
					if(curStr.compareTo("remove")==0 && admin){
						//remove interface
						int toRemove = theNet.getNumber(theSize);
						/* NEEDS DOING */
					}
					else if(curStr.compareTo("change")==0 && admin){
						//change interface
						int toRemove = theNet.getNumber(theSize);
						/* NEEDS DOING */
					}
					else if(curStr.compareTo("vote")==0 && !admin){
						int studNum = theNet.getNumber(theSize);
						//get the student object via it's # like Student[#]
						//then asks for the idea #, get Idea[] then Idea from Idea[]
						//call the "vote" method (from Idea class) on the Idea here for voting interface
						/* NEEDS DOING */
					}
					else{
						System.out.println("Remember you can exit by typing exit or cancel");
						curStr = scanner.nextLine();
					}
				}	
				
			}
			
				
			//showAllData - loop with options for user and admin
			else if (inStr.compareTo("SAD")==0){
				//getKeys, for each showUserInfo(key)
				//order is alphabetical through students
				//ideas are listed after student name
				//admin and user can showUserInfo for further options 
				//showUserInfo - loop with options for user and admin
					//showUserBase (prints "userName | userEmail")
					//then prints each user's ideas, users can vote on ideas
					//admin can remove user or change user's info
				System.out.println(" ~~ Showing All Data ~~ ");
				if(admin){System.out.println("Admin Commands: CNU (create new user), RU (remove user), CUI (change user info)");}
				if(!admin){System.out.println("User Commands: type a number to view & vote on a specific user's ideas, type cancel to exit");}
				//put single while loop here that performs all of the above
				/* NEEDS WORK */
			}
			
				
			//showAllIdeas
			else if (inStr.compareTo("SAI")==0){
				//accesses priority queue of Ideas and prints in reverse numberical order
				//admin can award (removes idea from student, adds to awarded)
				//User can vote on ideas, iterates votes +1/-1
				System.out.println(" ~~ Showing All Ideas ~~ ");
				if(admin){System.out.println("Admin Commands: \"award\" to award an idea");}
				if(!admin){System.out.println("User Commands: type a number to select an idea to vote on, type cancel to exit");}
				//put single while loop here that performs all of the above
				/* NEEDS WORK */
			}
				
			
			//changeUserInfo
			else if (inStr.compareTo("CUI")==0 && admin){
				//dialogue to change a student file
				//if admin, choose student file from showAllUsers
				//otherwise student file belongs to the user
				//prints: "fieldName | fieldValue" for each field
				//asks which field to change
				System.out.println(" ~~ Showing All Ideas ~~ ");
				if(admin){System.out.println("Admin Commands: \"award\" to award an idea");}
				if(!admin){System.out.println("User Commands: type a number to select an idea to vote on, type cancel to exit");}
				//put single while loop here that performs all of the above
				/* NEEDS WORK */
			}
			
			
			//createNewUser - calls dialogue 
			else if (inStr.compareTo("CNU")==0 && admin){ 
				//users can only access this dialogue before they log in
				System.out.println("Administrator Creating a New User");
				/* NEEDS WORK */
			}
			
			
			//search - loop with options for user and admin
			else if (inStr.compareTo("search")==0){
				//targetType = "first" or "last" or "email"
				//targetStr is search target
				//search(targetType, targetStr)
				/* NEEDS WORK */
			}
			
			
			//showAwards
			else if (inStr.compareTo("SA")==0){
				//loop with options for admin: can remove award
				//User can only view
				/* NEEDS WORK */
			}
			
			
			else {
				System.out.println("A thousand pardons, I didn't catch that...");
				System.out.println("Perchance one did not speak in lower case or use the correct uppercase initals...");
			}
		}
		
	}
}
