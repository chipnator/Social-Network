/*	Tom Conlin
*  COM212 Data Structure 
*	Professor Lee
*  Idea storage class
*
*  10/24/2013
*/

public class IdeaHN {

	private HeapNode[] myArray;
	private HeapNode root;
	private HeapNode rootPointer;
	private HeapNode lastItem;
	private int currentSize;
	
	public IdeaHN(){
		myArray = new HeapNode[100];
		currentSize = 0;
		rootPointer = myArray[0];
		lastItem = myArray[0];
		//Student root = null; // Student is null to start
	}
	
	public boolean isEmpty(){
		return currentSize==0;
	}
	
	//public void insert(String value, int score, Student author){
	public void insert(HeapNode newIdea){
		
		//newIdea = myArray[currentSize + 1];
		myArray[currentSize++] = newIdea;
		
		// sort the array to make sure its in order
		//currentSize++;
		
		if (currentSize == myArray.length){
			HeapNode[] temp = new HeapNode[myArray.length * 2];
			for (int i = 0; i < currentSize; i++){
				temp[i] = myArray[i];
			}
			myArray = temp;
		}		
	}
	
	public HeapNode removeMax() {
		HeapNode temp = myArray[1];
		myArray[1] = null;
		if (isEmpty()){
			System.out.println("There are no idea's in the heap.");
			return null;
		}
		else {
			int i = 0;
			// checks each element to the others to find the max element
			while(i < currentSize){
				if (temp.key > rootPointer.key) {
					rootPointer = temp;
				}
				i++;
			}
			return temp;
		}
	}
	
	public HeapNode returnList(){
		HeapNode printList = myArray[1];
		if (isEmpty()){
			System.out.println("There are no idea's in the heap.");
			return null;
		}
		else{
			int i = 0;
			while(i < currentSize){
				removeMax();	
				i++;
			}
			return printList;	
		}
	}
	
	public void upHeap(int index){
		int parent = (index - 1) / 2;
		HeapNode bottom = myArray[index];
		while (index > 0 && myArray[parent].getKey() < bottom.getKey()){
			myArray[index] = myArray[parent];  
			index = parent;
			parent = (parent - 1) / 2;
		}  
		myArray[index] = bottom;
	}

	public void downHeap(int index){
		int largerChild;
		HeapNode top = myArray[index];   
		while(index < currentSize / 2){                               
			int leftChild = 2*index+1;
			int rightChild = leftChild+1;                            
			if(rightChild < currentSize && myArray[leftChild].getKey() < myArray[rightChild].getKey()){
				largerChild = rightChild;
			}
			else{
				largerChild = leftChild;
			}
			
			if( top.getKey() >= myArray[largerChild].getKey()){
				myArray[index] = myArray[largerChild];
				index = largerChild; 
				myArray[index] = top;
			}      
		}
	}

}
