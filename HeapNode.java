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

	public void Node(int newKey, Idea insertIdea, Student newAuthor){ 
		key = newKey;
		idea = insertIdea;
		author = newAuthor;
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
	
	public void upvote(){
		setKey(key++);
		// key++;
	}
	public Idea returnIdea(){
		return idea;
	}

	
}
