//Student Node

class Student{
	protected String firstName; 
	protected String lastName;
	protected String email;
	protected String id;    
	protected Idealist ideas; //Idealist

	public void Student(String firstNameIn, String lastNameIn, String emailIn){ 
		firstName=firstNameIn; 
		lastName=lastNameIn;
		email=emailIn;
		Idealist ideas = new Idealist();
		resetID();
	}

	public String getFirstName(){ 
		return firstName; 
	}
	
	public String getLastName(){ 
		return lastName; 
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
	
	public Idea[] getIdeas(){
		return ideas.giveList();
	}
	
	public Idealist getIdealist(){
		return ideas;
	}
	
	public Idea getIdea(int getNum){
		return ideas.giveList()[getNum];
	}
	/*
	public Idea changeIdea(Idea oldIdea, String replaceIdea){  	 
		Idea[oldIdea].editElement(replaceIdea);
		Idea[oldIdea].resetRating();
		//simply modifies and returns Idea, no dialogue
		NEEDS DOING 
	}
	
	public void moveIdea(int pos1, int pos2){
		Idea temp = Idea[pos1]; // need to create a temporary holder for the pos1 idea *** probably not the way we create an idea
		Idea[pos1] = Idea[pos2]; // put the pos2 idea into the pos1
		temp = Idea[pos2]; // pos2 is filled by pos1
		//will switch the two idea's positions in Student's Idea[]
		 NEEDS DOING 
	}
	*/
}