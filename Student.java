//Student Node

class Student{
	protected String firstName; 
	protected String lastName;
	protected String email;
	protected String id;    
	protected Idealist ideas; //Idealist
	//protected IdeaHN ideaHeap;
	
	public Student(String firstNameIn, String lastNameIn, String emailIn){ 
		firstName=firstNameIn; 
		lastName=lastNameIn;
		email=emailIn;
		ideas = new Idealist();
		resetID();
	}

	public String getFirstName(){ 
		return firstName; 
	}
	
	public String getLastName(){ 
		return lastName; 
	}
	
	public String getFullName(){ 
		return firstName+" "+lastName; 
	}
	
	public String getEmail(){ 
		return email; 
	}
	
	public String getID(){ 
		return id; 
	}
	public void setFirstName(String inNom){ 
		firstName=inNom; 
	}
	
	public void setLastName(String inNom){ 
		lastName=inNom; 
	}
	
	public void setEmail(String inAdd){ 
		email=inAdd; 
	}
	
	public void resetID(){
		int p=email.length();
		for(int i=0;i<email.length();i++){
			if(email.substring(i,i+1).compareTo("@")==0){i=p;}
		}
		id=email.substring(0,p);
	}
	
	public void addIdea(String inIdea){
		Idea newIdea = new Idea(this, inIdea);
		ideas.addIdea(newIdea);
	}
	
	public Idea addIdeaAndReturn(String inIdea){
		Idea newIdea = new Idea(this, inIdea);
		//ideas.addIdea(newIdea);
		ideas.insert(newIdea);
		return newIdea;
	}
	
	public Idea[] getIdeas(){
		return ideas.giveList();
	}
	
	public Idealist getIdealist(){
		return ideas;
	}
	
	public Idea getIdea(int getIndexNum){ 
		//return the student's idea from their Idealist at index getNum
		return ideas.giveList()[getIndexNum];
	}
	
	public void moveIdea(int pos1, int pos2){
		Idea[] theArray=getIdeas();
		Idea temp = theArray[pos1]; // need to create a temporary holder for the pos1 idea
		//**** probably not the way we create an idea
		theArray[pos1] = theArray[pos2]; // put the pos2 idea into the pos1
		temp = theArray[pos2]; // pos2 is filled by pos1
		//will switch the two idea's positions in Student's Idea[]
	}
  
	
}
