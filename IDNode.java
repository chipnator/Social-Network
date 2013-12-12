//IDNode.java

class IDNode{
	protected Student owner;
	protected int rating;
	protected SLinked raterList; //user who already voted
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
	
	public boolean addRater(Student rater){
		//check and add to list
		if(!checkRater(rater)){ //checkRater say "Yes, it isn't there", thus bash!
			raterList.update(rater);
			return true;
		}
		return false;
	}
	
	public boolean checkRater(Student rater){
		//check if in list 
		return raterList.check(rater);
	}
	
}
