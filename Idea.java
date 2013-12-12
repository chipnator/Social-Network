import java.util.Scanner;

public class Idea{
	protected Student owner;
	protected int rating;
	protected SLinked voted;
	protected String ideastr;
	// Node & Singly Linked List
	protected Singly singly;
	protected SLinked sLinked;
	

	
	


	public Idea(Student ownerIn, String ideaIn){ // constructor
		owner = ownerIn;
		int rating = 0;
		System.out.println(ideaIn);
		try{
			int p=0;
			for(int i=0;i<ideaIn.length();i++){
				if(ideaIn.substring(i,i+1).compareTo("|")==0){i=p;}
			}
			String thisThing = ideaIn.substring(0,p).trim();
			rating=Integer.parseInt(thisThing);
		}
		finally{
			SLinked voted = new SLinked();
			ideastr = ideaIn;
		}
	}
	
	public void changeIdea(String replaceIdea){  	 
		setIdea(replaceIdea);
		resetRating();
		//simply modifies idea
		
	}
	
	// get set for rating, users who already voted, idea string.
	// iterate up, down for rating.
	public void resetRating(){rating=0;}
	
	public int getRating(){ return rating; }

	public int upRating(){ return rating++; }

	public int downRating(){ return rating--; }

	public String getIdea(){ return ideastr; }
	
	public void setIdea(String theInIdea){ ideastr = theInIdea; }

	public Student getOwner(){ return owner; }

	public void setRating(int inInt){ rating = inInt; }

	public void addVoter(Student voter){ voted.update(voter); }

	public boolean checkVoter(Student voter){ return voted.check(voter); }
	
	//method that one calls to vote
	public void vote(boolean upOrDown, Student voter){
		System.out.println("Idea: "+ideastr);
		System.out.println("Do you \"s\"upport this idea, \"d\"isapprove of this idea");
		System.out.println("or do you \"n\"ot care about this idea?");
		boolean run = true;
		while (run == true){
			Scanner scan = new Scanner(System.in);
			String curStr = scan.nextLine();
			if (curStr.compareTo("s")==0){ //support
				/*Needs Work*/
			}
			else if (curStr.compareTo("d")==0){ //disapprove
				/*Needs Work*/
			}
			else if (curStr.compareTo("n")==0){
				System.out.println("So you don't care about this idea... \n ...ok I guess. \n \n I kinda liked it but it's whatever...\n\n");
				run = false;
			}
			else{
				System.out.println("So sorry, I completely missed that, come again?");
			}
		}
	}
}















