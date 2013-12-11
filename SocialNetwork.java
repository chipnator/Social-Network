/*
 * chris dale - data structures final project - this is SocialNetwork.java 
 * this is the SocialNetwork class
 * requires: Student node class, priority queue class, hash table class
 * methods should print, not return 
 * methods will simply be called, not called inside print statements
 */
// need this scanner to take input from terminal (command line)
import java.util.Scanner;

public class SocialNetwork{
	protected HashTable userHash;
	
	//constructor
	public SocialNetwork(){
		
		//reading in user data file
		String fileName = "DataFile1.txt";
		//check for most recent data file
		//String checkName = "DataFile"+num+".txt"
		//while(file checkName num+1 exists)
			//num++
		HashTable userHash = new HashTable(101);//initalizes the userHash
		getDatabase(fileName);  
		awardCheck();
	}

	//getDatabase
	public void getDatabase(String fileName) {
		//read in user data file
		//Initalize hash
		 
		//created scanner object to take in data file
		Scanner dataIn = new Scanner(fileName);
		//How to get next line: dataIn.nextLine()
		
		int fileCount = 0;
		String line1 = " ";
		String line2 = " ";
		while(line1!=null && line2!=null){
			String first = dataIn.nextLine();
			String last = dataIn.nextLine();
			String email = dataIn.nextLine();
			Student newStud = new Student(first,last,email); //creates new student
			userHash.put(newStud.getID(),newStud); //adds newStud to hash
			line1 = dataIn.nextLine();
			line2 = dataIn.nextLine();
			while(line1.compareTo("\n")!=0 && line2.compareTo("\n")!=0 ){
				newStud.addIdea(line1);
				line1 = line2;
				line2 = dataIn.nextLine();
			}
			fileCount++;
		}
	}
	
	//makeStudent
	public void makeStudent(String first, String last, String email){
		Student newStud = new Student(first,last,email); //creates new student
		userHash.put(newStud.getID(),newStud); //adds newStud to hash
	}
	
	//getHash
	public HashTable getHash(){
		//return the hash table so it can be accessed directly
		return userHash;
	}
	
	//awardCheck
	public void awardCheck(){
		//Checks if users "award1" and "award2" exist
		//If not, creates 2 "people" to hold top ten ideas 
		if(!userHash.checkForKey("award1")){makeStudent("award1","award1","award1");}
		if(!userHash.checkForKey("award2")){makeStudent("award2","award2","award2");}
	}
	
	
	//showAwards
	public Idea[] showAwards(){
		//awardCheck
		//print top 10
		String awName="";
		Idea[] awOut = new Idea[10];
		for(int lo=0;lo<2;lo++){
			awName="award"+lo+1;
			Idea[] awIn = userHash.get(awName).getIdeas();
			for(int a=0;a<5;a++){
				awOut[a+(5*lo)]=awIn[a];
			}
		}
		for(int hi;hi<10;hi++){
			System.out.println(hi+" | "+awOut[hi].getIdea());
		}
		return awOut;
	}

	
	//showTopIdeas 
	public Idea[] showTopIdeas(int numToShow){
		//print numToShow #of ideas, sorted by votes in descending numberical order
		//if no idea detected, should be able to handle error 
		Idea[] ideaAr = new Idea[numToShow];
		/* NEEDS DOING */
	}
   
   
	//showUserBase - prints " name | email " 
	public void showUserBase(Student inStud){
		String first = inStud.getFirstName();
		String last = inStud.getLastName();
		String email = inStud.getEmail();
		System.out.println(first+" "+last+" | "+email);
	}
	
	
	//showUserBase - prints " name | email " 
	public void showNumUserBase(Student inStud, int inNum){
		String first = inStud.getFirstName();
		String last = inStud.getLastName();
		String email = inStud.getEmail();
		//String num = inNum.toString();
		String num = ""+inNum;
		System.out.println("#"+num+" | "+first+" "+last+" | "+email);
	}
	
	
	public void showUserInfo(Student inStud, int inNum, String inIdea){ 
		//prints " name | email " then the Ideas " votes | idea ", 
		//uses showUserBase, then prints user's ideas, for one user
		String first = inStud.getFirstName();
		String last = inStud.getLastName();
		String email = inStud.getEmail();
		//String idCode = inNum.toString();
		String idCode = ""+inNum;
		Idea[] ideaLi=inStud.getIdeas();
		System.out.println(idCode + " | "+first+" "+last+ " | "  + email);
		int kt=0;
		while(kt<5 && ideaLi[kt]!=null){System.out.println(ideaLi[kt]);}
	}
	
	
	//createNewUser - general dialogue user/admin goes through to add user
	public void createNewUser(){
		//asks for email, then name
		//create scanner object to take user input
		Scanner scanner = new Scanner(System.in);
		System.out.println("Creating a New User");
		System.out.println("Begin by inputing a first name:");
		String first = scanner.nextLine();
		System.out.println("Next input a last name:");
		String last = scanner.nextLine();
		System.out.println("And next your email:");
		String email = scanner.nextLine();
		Student newStud = new Student(first,last,email); //creates new student
		userHash.put(newStud.getID(),newStud); //adds newStud to hash
		//then adds ideas to student
		boolean getIdeaBool = doubleCheck(" you want to add an idea");
		while(getIdeaBool){
			//asks for up to 5 ideas, after each, asks "want to add another idea?"
			if(getIdeaBool){
				String inputOfIdea = scanner.nextLine();
				newStud.addIdea(inputOfIdea);
			}
			getIdeaBool = doubleCheck(" you want to add another idea");
		}
		System.out.println("\nCreated New User, Congradulations!\n");
	}
	
	
	public int getNumber(int maxOpt){
		//gets user to pick a number that is less than the maxOpt
		Scanner scanner = new Scanner(System.in);
		System.out.println("Pick a number less than "+maxOpt+".");
		int choice=-1;
		while(true){
			String inStr = scanner.nextLine();
			try{choice = Integer.parseInt(inStr);}
			finally{
				if(choice>maxOpt){
					System.out.println("Chose a number larger than "+maxOpt+".");
				}
				else if(choice!=-1 && choice<maxOpt){return choice;}
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
	
	/*
	*
	public Student[] showAllData(){ 
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
		// NEEDS DOING 
	}
	*
	*/
	
	public Student[] showAllUsers(){
		//getKeys then showUserBase(key) - returns Student[]
		//getKeys, for each showUserBase(key)
			//showUserBase - prints "userName | userEmail"
		//admin and user can showUserInfo for further options
		String[] allKeys = userHash.keySet();
		Student[] allStuds = Student[allKeys.length()];
		for(int y=0;y<allKeys.length();y++){
			allStuds[y]=userHash.get(allKeys[y]);
		}
		return allStuds;
	}
	
	/*
	*
	public I[] showAllIdeas(){
		//returns Idea[]
		// NEEDS DOING 
	}
	
				
	public void changeUserInfo(){
		//Dialogue that doubleCheck() if the admin wants to change each field
		// NEEDS DOING
	}
	
	public S[] search(String targetType, String targetStr){
		//search(targetType, targetStr) - returns Student[]
		//targetType = "first" or "last" or "email"
		//targetStr is search target
		//returns all users that have targetStr anywhere in them,
		//  starting with users that have it as the first set of letters 
		// NEEDS DOING 
	}
	
	public void storeDatabase(){ //writes all changes to an new .txt file
		//new database files created each time
		//will NEVER overwrite old database files, MMKAY?
		// NEEDS DOING
	}
	*
	*/

}
