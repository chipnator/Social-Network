//IDNode.java
//IDNode is the node of 
//ummmm... well this is interesting... this didn't get used at all
//I think that Idea replaced this... how did this happen???

class IDNode{
	protected Student owner;
	protected int rating;
	protected SLinked raterList; //user who already voted
	protected String idea;
	protected HeapNode heapNode;
	
	public void IDNode(Student ownIn, int ratIn, String ideaIn, HeapNode heapIn){
		owner=ownIn;
		rating=ratIn;
		idea=ideaIn;
		heapNode=heapIn;
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
