/*	Tom Conlin
*  COM212 Data Structure 
*	Professor Lee
*  Idea storage class
*
*  10/24/2013
*/

public class IdeaHN {

	protected HeapNode[] myArray;
	//protected HeapNode root;
	//protected HeapNode rootPointer;
	//protected HeapNode lastItem;
	protected int currentSize;
	//protected Student student;
	
	
	public IdeaHN(){
		myArray = new HeapNode[101];
		currentSize = 0;
		//rootPointer = myArray[0];
		//lastItem = myArray[0];
		//Student root = null; // Student is null to start
	}
	
	public boolean isEmpty(){
		return currentSize==0;
	}
	
	public void insert(HeapNode newIdea){
		
		// newIdea = myArray[currentSize + 1];
		myArray[++currentSize] = newIdea;
		//currentSize++;
		
		// sort the array to make sure its in order
		upHeap(currentSize);
		
		// expand if Heap's Array is full
		/*
		
		// Add back In if stuff is working
		
		if (currentSize == myArray.length){
			HeapNode[] temp = new HeapNode[myArray.length * 2];
			for (int i = 0; i < currentSize; i++){
				temp[i] = myArray[i];
			}
			myArray = temp;
		}*/		
	}
	
	
	
	public HeapNode removeMax() { 
		
		if (currentSize==0)
			return null;
		
		HeapNode temp = myArray[1]; //Why is it creating an array where size=1?
		//myArray[1] = null; //Why is it setting a non-existant array spot to null?
		myArray[1] = myArray[currentSize];
		myArray[currentSize] = null;
		currentSize--;
		downHeap(1);
		return temp;
		/*
		if (isEmpty()){
			System.out.println("There are no idea's in the heap.");
			return null;
		}
		else {
			int i = 0;
			// checks each element to the others to find the max element
			while(i < currentSize){
				//if (temp.key > rootPointer.key) {
				//	rootPointer = temp;
				//}
				i++;
			}
			return temp;
		}*/
		
	}
	
	public HeapNode remove(int position) { 
		//not proper heap implimentation of heap, but it works in this case
		//Don't genereally remove from middle of heap
		if (currentSize==0)
			return null;
		
		HeapNode temp = myArray[position]; 
		myArray[position] = myArray[currentSize];
		myArray[currentSize] = null;
		currentSize--;
		downHeap(position);
		return temp;
		
	}
	
	public HeapNode returnMax() { 
		
		if (currentSize==0)
			return null;
		
		HeapNode temp = myArray[1]; //Why is it creating an array where size=1?
		//myArray[1] = null; //Why is it setting a non-existant array spot to null?
		return temp;
		
	}
	
	public Idea[] returnListOfIdeas(){
		//IdeaHN tempHeap = this;

		HeapNode[] tempHeapNodes = new HeapNode[101];
		
		Idea[] listToReturn = new Idea[currentSize];
		if (isEmpty()){
			System.out.println("There are no idea's in the heap.");
			return null;
		}
		else{
			int i = 0;
			int tempCur = currentSize;
			while(currentSize>0){
				tempHeapNodes[i] = removeMax();
				listToReturn[i]=tempHeapNodes[i].returnIdea();	
				i++;
			}
			
			for (i=0; i<tempCur; i++)
			{
				insert(tempHeapNodes[i]);	
			}
			
			return listToReturn;
		}
	}
	
	public void upHeap(int index){ //what is the index? 
		int parent = (index) / 2;
		if (parent ==0){return;}
		//HeapNode bottom = myArray[index];
		/*
		while (myArray[parent].getKey() < bottom.getKey()){
			myArray[index] = myArray[parent];  
			index = parent;
			parent = (parent) / 2;
		}  
		myArray[index] = bottom;
		*/
		
		
		// james
		if (myArray[parent].getKey() < myArray[index].getKey()){
			HeapNode tNode = myArray[index];
			myArray[index] = myArray[parent];
			myArray[parent] = tNode;
			index = parent;
			upHeap(index);
		}
	}

	public void downHeap(int index){ //what is the index?
		int left = index*2;
		int right = index*2+1;
		if (left > currentSize || right > currentSize)
			return;
		
		// find which is large (left or right)
		int max = right;
		if(myArray[left].getKey() > myArray[right].getKey()){
			//HeapNode curHeap=myArray[left].getKey();
			max = left;
		}
		//else{curHeap=myArray[right].getKey()}
		if (myArray[max].getKey() > myArray[index].getKey()){
			HeapNode tempNode = myArray[index];
			myArray[index] = myArray[max];
			myArray[max] = tempNode;
			//index = curHeap;
			downHeap(max);
		}
	}
	
	public int getPosOfIdea(Idea inIdea){
		//returns the position of the HeapNode
		for(int yugioh=1;yugioh<currentSize+1;yugioh++){
			if(myArray[yugioh].returnIdea()==inIdea){
				return yugioh;
			}
		}
		return -1;
	}

}
