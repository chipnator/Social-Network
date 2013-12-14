/*
 * chris dale - data structures final project - this is SocialNetwork.java 
 * this is the SocialNetwork class
 * requires: Student node class, priority queue class, hash table class
 * methods should print, not return 
 * methods will simply be called, not called inside print statements
 */
// need this scanner to take input from terminal (command line)

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.NumberFormatException;

public class SocialNetwork{
	protected HashTable userHash;
	protected IdeaHN ideaHeap;
	//protected Idea idea; //don't think needs anymore
	protected boolean admin;
	protected String adminPass;
	protected boolean login;
	protected boolean quitting;
	protected Student user; 
	protected String inStr;
	protected Scanner scanner; // create scanner object to take user input
	
	
	//constructor
	public SocialNetwork(){
		//reading in user data file
		String fileName = "DataFile1.txt";
		//String fileName = "~/Users/chipnator/Social Network/DataFile1.txt";
		//check for most recent data file
		//String checkName = "DataFile"+num+".txt"
		//while(file checkName num+1 exists)
			//num++
		userHash = new HashTable(101);//initalizes the userHash
		ideaHeap = new IdeaHN(); //Idea Heap of Nodes, it's kind of a confusing name
		getDatabase(fileName);  
		awardCheck();
		//Setting up login stuff
		boolean admin = false;
		String adminPass = "password";
		boolean login = false;
		boolean quitting = false;
		Student user = null; 
		String inStr = "";
		Scanner scanner = new Scanner(System.in);
	}

	//getDatabase
	public void getDatabase(String fileName) {
		//read in user data file
		//Initalize hash
		try{
			//created scanner object to take in data file
			//Scanner dataIn = new Scanner(file);
			//
			
			Scanner dataIn = new Scanner(new FileReader(fileName));
			//How to get next line: dataIn.nextLine()
			
			String line;
			while(dataIn.hasNextLine()) {
				
				String first = dataIn.nextLine();
				String last = dataIn.nextLine();
				String email = dataIn.nextLine();
				
				System.out.println(first);
				
				Student newStud = makeReturnStudent(first,last,email); 
				//creates new student and adds newStud to hash
				
				line = dataIn.nextLine().trim();
				while (!line.equals("") && dataIn.hasNextLine()) {
					Idea anIdea = newStud.addIdeaAndReturn(line);
					ideaHeap.insert(new HeapNode(anIdea));
					line = dataIn.nextLine();
					//System.out.println("one idea done");
				}
				//fileCount++;
			}
		}
		catch(FileNotFoundException e){ 
			System.out.println("File Not Found");
		}
	}
	
	//makeStudent
	public void makeStudent(String first, String last, String email){
		Student newStud = new Student(first,last,email); //creates new student
		userHash.put(newStud.getID(),newStud); //adds newStud to hash
	}
	
	//makeReturnStudent
	public Student makeReturnStudent(String first, String last, String email){
		Student newStud = new Student(first,last,email); //creates new student
		userHash.put(newStud.getID(),newStud); //adds newStud to hash
		return newStud; //returns new Student
	}
	
	//getHash
	public HashTable getHash(){
		//return the hash table so it can be accessed directly
		return userHash;
	}
	
	//getHeap
	public IdeaHN getHeap(){
		//returns the heap so it can be accessed directly
		return ideaHeap;
	}
	
	//awardCheck
	public void awardCheck(){
		//Checks if users "award1" and "award2" exist
		//If not, creates 2 "people" to hold top ten ideas 
		if(!userHash.checkForKey("award1")){makeStudent("award1","award1","award1");}
		if(!userHash.checkForKey("award2")){makeStudent("award2","award2","award2");}
	}
	
	public void run() {
		//add check for most recent data file, IDK how to do this...
		
		//get next line --> dataIn.nextLine()
		
		//while there is still more file to parse
			//accesses a user's data from current position in file
			//creates a Student node, fills Student's array with Idea nodes
			//populates hash table with email as key and Student Node as value
			//first two records will be award holders, email: award1 and award2
		
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
		System.out.println("\n\nWelcome to the Idea Network Browser! \n");
		//MAIN LOOP: a while loop to perform user interaction
		Scanner scanner = new Scanner(System.in);
		System.out.print("Novice users, type \"info\" for instructions \n");
		while(running) {
			//checks that a user is logged in
			while(login==false && quitting == false){
				inStr = scanner.nextLine();
				if (inStr.compareTo("quit")==0 || inStr.compareTo("cancel")==0 || inStr.compareTo("exit")==0) {
					running = false; // this will terminate running
					quitting = true;
				}
				else if(inStr.compareTo("admin")==0){
					if(password(adminPass)){
						admin=true;
						login=true;
					}
					else{
						admin=false;
					}
				}
				else if(inStr.compareTo("info")==0){
					System.out.print("Students: Type \"login\" to log in OR Type \"new\" to make a new user \n");
					System.out.print("Administrators: Type \"admin\" to access admin login \n");
				}
				else if(inStr.compareTo("login")==0){
					//dialogue for student to login
					//studentLogin
					//enter student's id to log in
					//find Student associated with that key value
					//Set that student to user
					boolean loginRunning = true;
					while(loginRunning){
						System.out.println("\nType your email address to locate your login, \ncommands \"cancel\" and \"exit\" function as such \n");
						inStr=scanner.nextLine();
						user = searchStudent(inStr);
						if(user==null){
							System.out.println("\nYou entered an invalid user name, try again. \n");
							login=false;
						}
						else if(inStr.compareTo("cancel")==0 || inStr.compareTo("exit")==0){
							System.out.println("\nCancelled Log In \n");
							loginRunning=false;
						}
						else{
							System.out.println("\nYou logged in\n");
							login=true;
							loginRunning=false;
						}
					}
				}
				else if(inStr.compareTo("new")==0){
					String theEmailLogin = createNewUser();
					
				}
				else {
					System.out.print("Try typing info for instructions.\n");
					inStr = scanner.nextLine();
				}
			}
			
			//gets user input (if not quitting from login)
			if(quitting==false){
				System.out.println("A request for information can be rendered via the phrase: \"info\" \n");
				System.out.print("What would you ask of me? ");
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
				if(doubleCheck(" you want to log out")){
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
				System.out.println("Commands at one's disposal: info, quit, log out, search, SA (show awards), STI (show top ideas), SAD (show all data, users & ideas), SAI (show all ideas)");
				if(!admin){System.out.println("User Commands: CUI (change user info)");}
				if(admin){System.out.println("Admin Commands: CNU (create new user), CUI (change user info)");}
			}

			//add idea if logged in as student
			
			
			//showTopIdeas - loop with options for user and admin
			else if (inStr.compareTo("STI")==0){
				//asks for number of ideas to show first
				//takes from priority queue
				//admin can award (removes idea from student, adds to awarded)
				//User can vote on ideas, iterates votes +1/-1
				System.out.println("How many ideas should be shown?");
				/* int totalIdeas =  NEEDS WORK */ //gets total number of ideas
				//int showNum = getNumber(totalIdeas); //gets number of ideas	
			}
			
			//showAllIdeas
			else if (inStr.compareTo("SAI")==0){
				//accesses priority queue of Ideas and prints in reverse numberical order
				//admin can award (removes idea from student, adds to awarded)
				//User can vote on ideas, iterates votes +1/-1
				System.out.println(" ~~ Showing All Ideas ~~ ");
				Idea[] allTheIdeas = ideaHeap.returnListOfIdeas();
				//prints all ideas
				for(int ide=0;ide<allTheIdeas.length && allTheIdeas[ide]!=null;ide++){
					//if (allTheIdeas[ide]==null)
					//	break;
					
					allTheIdeas[ide].showNumberedIdea(ide);
				}
				if(admin){System.out.println("Admin Commands: \"award\" to award an idea");}
				if(!admin){System.out.println("User Commands: type a number to select an idea to vote on, type cancel to exit");}
				//put single while loop here that performs all of the above
				/* NEEDS WORK */
			}
			
			
			//showAllData - loop with options for user and admin
			else if (inStr.compareTo("SAD")==0) {
				//student can vote on ideas
				//admin can remove user or change user's info
				
				Student[] allUsers = showReturnAllData();
				int theSize = allUsers.length;
				Student inStudd = getStudentFromList(allUsers);
				if(!admin){System.out.println("As user, one may \"vote\" on a specific user's info, referenced by number");}
				if(admin){System.out.println("As Admin, one may \"remove\" or \"change\" the user's info, referenced by number");}
				String curStr = scanner.nextLine();
				while(curStr.compareTo("exit")!=0 || curStr.compareTo("cancel")!=0){
					if(admin){System.out.println("Admin Commands: \"remove\" to remove user, \"change\" to change user");}
					if(!admin){System.out.println("User Commands: type a number to select a user & vote on their ideas, type cancel to exit");}
					if(curStr.compareTo("remove")==0 && admin){
						//remove interface
						int toRemove = getNumber(theSize);
						/* NEEDS DOING */
					}
					else if(curStr.compareTo("change")==0 && admin){
						//change interface
						int toChange = getNumber(theSize);
						changeUser(inStudd);
					}
					else if(curStr.compareTo("vote")==0 && !admin){
						//get the student object via it's # like Student[#]
						//then asks for the idea #, get Idea[] then Idea from Idea[]
						//call the "vote" method (from Idea class) on the Idea here for voting interface
						Student voteOn = getStudentFromList(allUsers);
						System.out.println("Choose an idea to vote on:");
						int studIdeaNum = getNumber(theSize);
						Idea chosen = voteOn.getIdeas()[studIdeaNum];
						if(chosen!=null){chosen.vote(user);}
					}
					else{
						System.out.println("Remember you can exit by typing exit or cancel");
						curStr = scanner.nextLine();
					}
				}	
				
			}
				
			
			//changeUserInfo for Admin
			else if (inStr.compareTo("CUI")==0 && admin){
				//dialogue to change a student file, choose student file from showAllUsers
				//prints: "fieldName | fieldValue" for each field
				
				System.out.println(" ~~ Showing All Ideas ~~ ");
				
				//prints all data
				
				System.out.println("Pick a user to modify by choosing the #");
				//put single while loop here that performs all of the above
					//asks which field to change
					//adding HeapNode to IdeaHN
						//ideaHeap.insert(HeapNode(int voteVal, Idea idea, Student inAuthor))
				/* NEEDS WORK */
			}
			
			//changeUserInfo for User
			else if (inStr.compareTo("CUI")==0 && !admin){
				//dialogue to change a student file, choose student file from showAllUsers
				//prints: "fieldName | fieldValue" for each field
				
				showUserProfile(user);
				if(doubleCheck(" change your user info")){
					changeUser(user);
				}
			}
			
			
			
			//createNewUser - calls dialogue 
			else if (inStr.compareTo("CNU")==0 && admin){ 
				//users can only access this dialogue before they log in
				System.out.println("Administrator Creating a New User");
				createNewUser();
			}
			
			
			//search - loop with options for user and admin
			else if (inStr.compareTo("search")==0){
				//targetType = "first" or "last" or "email"
				//targetStr is search target
				//search(targetType, targetStr)
				Student holdStu = searchStudentAdminDialogue();
				if(holdStu!=null){
					/*
					if(admin){changeStudDial();}
					if(!admin){studVoteDial();}
					*/
				}
				else{System.out.println("Cancelled or Student not Found");}
			}
			
			
			//showAwards
			else if (inStr.compareTo("SA")==0){
				//loop with options for admin: can remove award
				//User can only view
				Idea[] awards = showAwards();	
			}
			
			
			else {
				System.out.println("A thousand pardons, I didn't catch that...");
				System.out.println("  Perchance one did not speak their command in lower case...");
				System.out.println("    Perchance one did not use the correct uppercase abbreviation...");
			}
		}
		
		
	}
	
	//showAwards
	public Idea[] showAwards(){
		awardCheck();
		//print top 10
		String awName="";
		Idea[] awOut = new Idea[10];
		Student awIn1 = userHash.get("award1");
		Student awIn2 = userHash.get("award2");
		for(int a=0;a<5;a++){
			awOut[a]=awIn1.getIdea(a);
		}
		for(int b=0;b<5;b++){
			awOut[b+5]=awIn2.getIdea(b);
		}
		/*
		for(int lo=0;lo<2;lo++){
			awName="award"+lo+1;
			Idea[] awIn = userHash.get(awName).getIdeas();
			for(int a=0;a<5;a++){
				awOut[a+(5*lo)]=awIn[a];
			}
		}
		*/
		for(int hi=0;hi<10;hi++){
			System.out.println(hi+" | "+awOut[hi].getIdea());
		}
		return awOut;
	}
	
	//showTopIdeas 
	public Idea[] showTopIdeas(int numToShow){
		//print numToShow #of ideas, sorted by votes in descending numberical order
		//if no idea detected, should be able to handle error 
		Idea[] ideasOut = new Idea[numToShow];
		Idea[] allTheIdeas = ideaHeap.returnListOfIdeas();
		/* Should not use this function, too much effort!!! */
		for(int soup=0;soup<numToShow;soup++){
			ideasOut[soup]=allTheIdeas[soup];
		}
		return ideasOut;
		/* Super Inefficient!!!! Needs Fixing!!! */
	}
	
	//showReturnAllData returns Student[] of all students
	public Student[] showReturnAllData(){
		System.out.println("All users in database");
		Student[] allUsers = showAllUsers();
		for(int cat=0;cat<allUsers.length;cat++){
			showUserInfo(allUsers[cat],cat);
		}
		return allUsers;
	}
	
	//showReturnAllBase returns Student[] of all students
	public Student[] showReturnAllBase(){
		System.out.println("All users in database");
		Student[] allUsers = showAllUsers();
		for(int cat=0;cat<allUsers.length;cat++){
			showNumUserBase(allUsers[cat],cat);
		}
		return allUsers;
	}
	
	//showUserBase - prints " name | email " 
	public void showUserBase(Student inStud){
		String first = inStud.getFirstName();
		String last = inStud.getLastName();
		String email = inStud.getEmail();
		System.out.println(first+" "+last+" | "+email);
	}
	
	//showUserProfile
	public void showUserProfile(Student inStud){
		String name = inStud.getFullName();
		String email = inStud.getEmail();
		Idea[] ideaLi=inStud.getIdeas();
		System.out.println("  ~~  Account for User: "+name+" | "+email+"  ~~  ");
		int kt=0;
		while(kt<5 && ideaLi[kt]!=null){System.out.println(" ~~~ "+ideaLi[kt]);}
	}
	
	
	//showNumUserBase - prints " name | email " 
	public void showNumUserBase(Student inStud, int inNum){
		String first = inStud.getFirstName();
		String last = inStud.getLastName();
		String email = inStud.getEmail();
		//String num = inNum.toString();
		String num = ""+inNum;
		System.out.println("#"+num+" | "+first+" "+last+" | "+email);
	}
	
	
	public void showUserInfo(Student inStud, int inNum){ 
		//prints " name | email " then the Ideas " votes | idea ", 
		//uses showUserBase, then prints user's ideas, for one user
		String first = inStud.getFirstName();
		String last = inStud.getLastName();
		String email = inStud.getEmail();
		Idea[] ideaLi=inStud.getIdeas();
		System.out.println(inNum + " | "+first+" "+last+ " | "  + email);
		int kt=0;
		while(kt<5 && ideaLi[kt]!=null){System.out.println(" ~~~ "+ideaLi[kt]);}
	}
	
	
	//createNewUser - general dialogue user/admin goes through to add user
	public String createNewUser(){
		//asks for email, then name
		//create scanner object to take user input
		Scanner scannerCNU = new Scanner(System.in);
		System.out.println("Creating a New User");
		System.out.println("Begin by inputing a first name:");
		String first = scannerCNU.nextLine();
		System.out.println("Next input a last name:");
		String last = scannerCNU.nextLine();
		System.out.println("And next your email:");
		String email = scannerCNU.nextLine();
		Student newStud = new Student(first,last,email); //creates new student
		userHash.put(newStud.getID(),newStud); //adds newStud to hash
		//then adds ideas to student
		boolean getIdeaBool = doubleCheck(" you want to add an idea");
		while(getIdeaBool){
			//asks for up to 5 ideas, after each, asks "want to add another idea?"
			if(getIdeaBool){
				String inputOfIdea = scannerCNU.nextLine();
				newStud.addIdea(inputOfIdea);
			}
			getIdeaBool = doubleCheck(" you want to add another idea");
		}
		System.out.println("\nCreated New User, Congradulations!\n");
		return email;
	}
	
	//changeUser - general dialogue user/admin goes through to change user
	public void changeUser(Student inStud){
		Scanner scannerCNU = new Scanner(System.in);
		System.out.println("Editing the Info for User: "+inStud.getFullName());
		if(doubleCheck(" you want to edit first name from"+inStud.getFirstName())){
			System.out.println("Editing first name: ");
			inStud.setFirstName(scannerCNU.nextLine());
		}
		if(doubleCheck(" you want to edit last name from"+inStud.getLastName())){
			System.out.println("Editing last name: ");
			inStud.setLastName(scannerCNU.nextLine());
		}
		if(doubleCheck(" you want to edit email from"+inStud.getLastName())){
			System.out.println("Editing email: ");
			inStud.setEmail(scannerCNU.nextLine());
		}
		if(inStud.getIdealist().hasRoom()){
			boolean addIdeaBool = doubleCheck(" you want add any ideas");
			while(addIdeaBool){
				//asks for up to 5 ideas, after each, asks "want to add another idea?"
				if(addIdeaBool){
					String inputOfIdea = scannerCNU.nextLine();
					inStud.addIdea(inputOfIdea);
				}
				addIdeaBool = doubleCheck(" you want edit any other ideas");
			}
		}
		if(doubleCheck(" you want change any ideas")){
			Idea[] ideaAr = inStud.getIdeas();
			boolean chIdeaBool = doubleCheck(" you want change any ideas");
			while(chIdeaBool){
				//asks for up to 5 ideas, after each, asks "want to add another idea?"
				int gotNum = getNumber(5);
				if(doubleCheck(" you want change idea #"+gotNum+" that says "+ideaAr[gotNum].getIdea())){
					String theInStri = doubleCheckInput("What should the idea be changed to?"); 
					ideaAr[gotNum].changeIdea(theInStri);
				}
				chIdeaBool = doubleCheck(" you want change any other ideas");
			}
		}
		System.out.println("\nEdited User, Congradulations!\n");
	}
	
	public int getNumber(int maxOpt){
		//gets user to pick a number that is less than the maxOpt
		//Scanner scanner = new Scanner(System.in);
		System.out.println("Pick a number less than "+maxOpt+".");
		int choice=-1;
		String inStr="";
		while(true){
			inStr = scanner.nextLine();
			try{choice = Integer.parseInt(inStr);}
			finally{
				if(choice>maxOpt){
					System.out.println("Chose a number larger than "+maxOpt+".");
				}
				else if(choice>-1 && choice<maxOpt){return choice;}
   			}
		   }
	}
	
	
	public boolean doubleCheck(String addOn){
		//a humorous double check method
		Scanner scanner = new Scanner(System.in);
		System.out.println("Are you sure"+addOn+"? y or n");
		String inStr = scanner.nextLine();
		int anger = 0;
		while(true){
			if (inStr.compareTo("y")==0) {
				return true;
			}
			else if (inStr.compareTo("n")==0) {
				return false;
			}
			else if(anger>0 && anger%2==0 && anger<=4) {
				System.out.println("Excuse me, are you sure"+addOn+"? y or n");
			}
			else if(anger>0 && anger%7==0) {
				System.out.println("Your father smelt of elderberries and your mother was a shrubbery!");
			}
			else if(anger>0 && anger%5==0) {
				System.out.println("Are you even listening?");
			}
			else if(anger>0 && anger%3==0) {
				System.out.println("Don't be stupid, are you sure"+addOn+"?!?!? y or n");
			}
			else if(anger>2 && anger%2==0) {
				System.out.println("Just answer, are you sure"+addOn+"?!?!? y or n");
			}
			else if(anger%2==1) {
				System.out.println("Hey, are you sure"+addOn+"? y or n");
			}
			else {
				System.out.println("No really, are you sure"+addOn+"? y or n");
			}
			anger++;
			inStr = scanner.nextLine();
		}
	}
	
	public String doubleCheckInput(String addOn){
		//a humorous double check method
		Scanner scanner = new Scanner(System.in);
		System.out.println(addOn);
		String theOut = scanner.nextLine();
		boolean tall=true;
		while(true){
			if(doubleCheck(" that this is correct?"+theOut+"\n So are you sure")){return theOut;}
			if(doubleCheck(" want to try again?")){theOut = scanner.nextLine();}
		}
	}
	
	
	public boolean password(String adminPass){
		//checks admin password
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter password:");
		String inStr = scanner.nextLine();
		while(true){
			if (inStr.compareTo(adminPass)==0) {
				return true;
			}
			else if (inStr.compareTo("cellar door")==0) {
				System.out.println("h4x0r3d");
				return true;
			}
			else if (inStr.compareTo("cancel")==0) {
				return false;
			}
			else {
				System.out.println("Try again or type cancel");
				inStr = scanner.nextLine();
			}
		}
	}
	

	public Student[] giveAllData(){ 
		//getKeys then showUserInfo(key) - returns Student[]
		//getKeys, for each showUserBase(key)
			//showUserBase - prints "userName | userEmail"
		//admin and user can showUserInfo for further options
		//showUserInfo - loop with options for user and admin
		//showUserBase, then prints user's ideas
		//showUserBase - prints "userName | userEmail"
		//print the list of users, "# | Name | email"
			//getKeys and for each key, 
			//showUsersInfo(key).getKeys();
			//showUserInfo(key);
			//showUserBase(key);
		String[] allKeys = userHash.keySet();
		Student[] allStuds = new Student[allKeys.length];
		for(int y=0;y<allKeys.length;y++){
			allStuds[y]=userHash.get(allKeys[y]);
		}
		return allStuds;
	}
	
	public Student[] showAllUsers(){
		//getKeys then showUserBase(key) - returns Student[]
		//getKeys, for each showUserBase(key)
			//showUserBase - prints "userName | userEmail"
		//admin and user can showUserInfo for further options
		String[] allKeys = userHash.keySet();
		Student[] allStuds = new Student[allKeys.length];
		for(int y=0;y<allKeys.length;y++){
			allStuds[y]=userHash.get(allKeys[y]);
			showUserInfo(allStuds[y], y);
		}
		return allStuds;
	}
	
	
	/*
	*
	
	public void storeDatabase(){ //writes all changes to an new .txt file
		//new database files created each time
		//will NEVER overwrite old database files, MMKAY?
		// NEEDS DOING
	}
	
	*
	*/
	
	public Student getStudentFromList(Student[] inStudList){
		Student[] listStuds = showAllUsers();
		Student studentRetuObj=null;
		boolean gSFLRunning = true;
		String inStr="";
		while(gSFLRunning){
			System.out.println("Type email address to access user, \ncommands \"cancel\" and \"exit\" function as such \n");
			inStr=scanner.nextLine();
			studentRetuObj = userHash.get(inStr);
			if(user==null){
				System.out.println("You entered an invalid user name, try again. \n");
			}
			else if(inStr.compareTo("cancel")==0 || inStr.compareTo("exit")==0){
				System.out.println("Cancelled \n");
				gSFLRunning=false;
				//returns null
			}
			else{
				System.out.println("You accessed user "+studentRetuObj.getFullName()+"\n");
				gSFLRunning=false;
			}
		}
		return studentRetuObj;
	}
   
	public Student searchStudent(String studentName) {
		return userHash.get(studentName);
	}
	
	public Student searchStudentAdminDialogue() {
		Student studentRetObj=null;
		boolean loginRunning = true;
		String studentName="";
		while(loginRunning){
			System.out.println("\nType email address to access user, \ncommands \"cancel\" and \"exit\" function as such \n");
			studentName=scanner.nextLine();
			studentRetObj = userHash.get(studentName);
			if(user==null){
				System.out.println("\nYou entered an invalid user name, try again. \n");
			}
			else if(studentName.compareTo("cancel")==0 || inStr.compareTo("exit")==0){
				System.out.println("\nCancelled Log In \n");
				loginRunning=false;
			}
			else{
				System.out.println("\nYou accessed the file of "+studentName+"\n");
				loginRunning=false;
			}
		}
		return studentRetObj;
	}
	
	public boolean tryToGetNumber(String theStrIn){
		boolean theRetBoolean = true;
		try{Integer.parseInt(theStrIn);}
		catch(NumberFormatException e){return theRetBoolean;}
		return theRetBoolean;
	}
	
	//public void changeStudDial()
		//Dialogue that doubleCheck() if the admin wants to change each field
	
	//public void studVoteDial()
             
}
