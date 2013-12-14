import java.util.Scanner;
import java.util.StringTokenizer;

public class Idea{
	protected Student owner;
	protected int rating;
	protected SLinked voted;
	protected String ideastr;
	// Node & Singly Linked List
	protected Singly singly;
	protected SLinked sLinked;
	protected HeapNode heapNode;
	//protected IdeaHN ideaHeap; //really should not need
	
	//public Idea(Student ownerIn, String ideaIn, IdeaHN inIdHeap){ // old constructor
	public Idea(Student ownerIn, String ideaIn){ // new constructor
		owner = ownerIn;
		//ideaHeap = inIdHeap;
		rating = 0;
		//System.out.println(ideaIn);
		//heapNode=heapIn;		
		StringTokenizer tok = new StringTokenizer(ideaIn, "|");
		String thisThing = tok.nextToken().trim();
		//System.out.println(thisThing);
		rating=Integer.parseInt(thisThing);
		//System.out.println(rating);
		String desc = tok.nextToken().trim();
		ideastr = desc;
		SLinked voted = new SLinked();
		showIdea();
	}
	
	//changeIdea
	public void changeIdea(String replaceIdea){  
		//simply modifies idea
		setIdea(replaceIdea);
		resetRating();
	}
	
	//showIdea
	public void showIdea(){
		//prints out the Idea
		System.out.println(rating+" | "+ideastr+" | "+owner.getFullName());
	}
	
	//showNumberedIdea
	public void showNumberedIdea(int numbered){
		//prints out the Idea
		System.out.println("#"+numbered+" | "+rating+" | "+ideastr+" | "+owner.getFullName());
	}
	
	// Heap get/set --> removing Heap access because it doesn't make sense
	//
	// public void setHeap(HeapNode heapIn){ heapNode=heapIn; }
	// public HeapNode getHeap(){ return heapNode; }
	
	
	// get set for rating, users who already voted, idea string.
	// iterate up, down for rating.
	public void resetRating(){rating=0;}
	
	public int getRating(){ return rating; }

	public int upRating(){ return rating++; }

	public int downRating(){ return rating--; }
	
	
	// Idea get/set
	public String getIdea(){ return ideastr; }
	
	public void setIdea(String theInIdea){ ideastr = theInIdea; }

	
	// Owner get & Voter get/set
	public Student getOwner(){ return owner; }

	public void setRating(int inInt){ rating = inInt; }

	public void addVoter(Student voter){ voted.update(voter); }

	public boolean checkVoter(Student voter){ return voted.check(voter); }
	
	
	//method that one calls to vote
	public void vote(Student voter){
		System.out.println("Idea: "+ideastr);
		System.out.println("Do you \"s\"upport this idea, \"d\"isapprove of this idea");
		System.out.println("or do you \"n\"ot care about this idea?");
		boolean run = true;
		while (run == true){
			Scanner scan = new Scanner(System.in);
			String curStr = scan.nextLine();
			if (curStr.compareTo("s")==0 || curStr.compareTo("d")==0){ //support
				if(!checkVoter(voter) && voter!=null && curStr.compareTo("s")==0){ //checkVoter says, "yes, voter is in list"
					upRating();
					addVoter(voter);
				}
				else if(!checkVoter(voter) && voter!=null && curStr.compareTo("d")==0){ //checkVoter says, "yes, voter is in list"
					downRating();
					addVoter(voter);
				}
				else if(voter==null){System.out.println("Silly admin, voting is for students!");}
				else if(checkVoter(voter)){System.out.println("Students can't vote twice on an idea!");}
				else{
					System.out.println("Sorry, vote denied!");
				}
				run=false;
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















