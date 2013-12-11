//IDNode.java

class IDNode{
	protected Student owner;
	protected int rating;
	protected Idea.SLinked raterList; //user who already voted
	protected String idea;
	
	public void IDNode(Student ownIn, int ratIn, String ideaIn){
		owner=ownIn;
		rating=ratIn;
		idea=ideaIn;
	}
	
	public int getRating(){
		return rating;
	}
	
	public void setRating(int ratIn){
		rating = ratIn;
	}
	
	public Student getOwner(){
		return owner;
	}
	
	public void setOwner(Student ownIn){
		owner=ownIn;
	}
	/*
	public void addRater(Student rater){
		//add to list
		// Needs Work
	}
	
	public boolean checkRater(Student rater){
		//check if in list 
		// Needs Work 
	}
	*/
}
