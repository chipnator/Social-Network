/*	Tom Conlin
 *  COM212 Data Structure 
 *	Professor Lee
 *  Idea storage node class
 *
 *  10/24/2013
*/

class HeapNode{
	protected int key; 
	protected Idea idea; 
	protected Student author;          

	public HeapNode(int newKey, Idea insertIdea, Student newAuthor){ 
		key = newKey;
		idea = insertIdea;
		author = newAuthor;
	}

	public HeapNode(Idea insertIdea){
		
		key = insertIdea.getRating();
		idea = insertIdea;
		author = insertIdea.getOwner();
	}
	
	public int getKey(){ 
		return key; 
	}

	public void setKey(int identifier){ 
		key = identifier; 
	}
	public void downVote(){
		setKey(key--);
		// key--;
	}
	
	public void upVote(){
		setKey(key++);
		// key++;
	}
	public Idea returnIdea(){
		return idea;
	}

	public void showNode(){
		System.out.println("Top Idea: "+idea.getIdea());
		System.out.println("Top Idea Has "+idea.getRating()+" Votes!");
		System.out.println("Top Idea thought of by "+author.getFullName());
	}
	
}

