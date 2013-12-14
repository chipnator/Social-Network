
// AJ Omartian
// Com 212 project 1
// 9/27/13

public class Idealist{ //Sets up the class 
	protected Idea[] array; 
	protected int nElements;
	
	public Idealist(){ //Constructor. Initializes array variable with given nElements.
		array = new Idea[5];
		nElements = 0;
	}
	
	public boolean hasRoom(){
		return (nElements<5);
	}
		
	public void insert(Idea value){ // Inserts given value in the array (at the first available empty spot)
		array[nElements] = value;
		nElements++;
	}
	
	public boolean remove(Idea value){ // Removes the given value from the array and returns true. If not exist, return false
		for (int i=0; i<nElements; i++){
			if (array[i]==value){
				for (int j=i; j<nElements; j++){
					array[j]=array[j+1];
				}
			nElements--;
				return true;
			}
		}
		return false;
	}
	
	public void display(){ // Prints out all elements in the array from 0th element to the last.
		for (int i=0; i<nElements; i++){
			System.out.print(array[i]+" ");
		}
		System.out.println("");
	}

	public void addIdea(Idea inIdea){
		if(array[nElements]!=null){
			array[nElements]=inIdea;
			nElements++;
		}
		else{
			System.out.println("Unable to add Idea. Idealist is full.");
		}
	}
	
	public Idea[] giveList(){ //RETURNS idea array
		return array;
	}
	
}
