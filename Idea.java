import java.util.Scanner;

public class Idea{
	protected Student owner;
	protected int rating;
	protected SLinked voted;
	protected String ideastr;

	public class Singly{ // Nodes of a singly linked list of S's.
		private Student element;
		private Singly next;
		public Singly(Student prs, Singly nextIn){ // Creates a node with the given element and the next node.
			element = prs;
			next = nextIn;
		}

		public Student getElement() { return element; } // returns the element of this node.

		public Singly getNext() { return next; }

		public void setElement(Student newElem) { element = newElem; }

		public void setNext(Singly newNext) { next = newNext; }

	}

	// Singly linked list
	public class SLinked{
		protected Singly head;
		protected int size;
		public SLinked(){
			head = null;
			size = 0;
		}
		//update and search methods.
		public void update(Student user){
			Singly temp = new Singly(user, head.getNext());
			head = temp;
			size++;
		}

		public boolean check(Student user){
			Singly pointer = head.getNext();
			while(pointer.getNext()!=null){
				if (pointer.getElement() == user){
					return true;
				}
			}
			return false;
		}
	}


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
	
	// get set for rating, users who already voted, idea string.
	// iterate up, down for rating.
	public int getRating(){ return rating; }

	public int upRating(){ return rating++; }

	public int downRating(){ return rating--; }

	public String getIdea(){ return ideastr; }

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
				/*Finish*/
			}
			else if (curStr.compareTo("d")==0){ //disapprove
				/*Finish*/
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















