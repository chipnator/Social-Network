public class HashTable {

	protected HashEntry hashEntry;
	protected int numEnt=0;	//number of entries
	protected int capacity;
	protected HashEntry[] userStorage;	//userStorage array
	//Creates a hashtable with given capacity
	public HashTable(int cap) {		
		capacity=cap;
		userStorage = (HashEntry[]) new HashEntry[capacity];
	}       
	
	//Determines if key is valid
	protected void checkKey(String email) {
		if (email==null){
			System.out.println("Invalid key");
		}
	}
	
	//Hash function that applies MAD method to default hash code
	public int hashValue(String key) {
		return (int) (Math.abs(key.hashCode()))%capacity;
	}
	
	
	//Returns the obect array that contains all keys
	public String[] keySet() {
		int keepTrack=0;
		String[] keys = new String[numEnt];   //creates new key
		for(int i=0; i<capacity; i++) {
			if((userStorage[i]!=null)){
				keys[keepTrack++]=userStorage[i].getKey();
				//checks every key mapped into hash table and takes key and puts into array
			}
		}
		return keys;
	}
	
	
	//Helper search method - returns index of found key or -(a-1) where a is
	//  the index of the first empty or available slot found (pages 403-404)
	//Checks if key is there
	public Integer findEntry(String key) {
		int avail= -1;
		if(key==null){
			System.out.println("Invalid entry");
			return avail;
		}
		else{
			int i=hashValue(key);
			int j=i;
			do{
				HashEntry curEnt =userStorage[i];
				if(curEnt==null){
					if(avail<0){
						avail=i;
						break; 
					}
				}
				if(key.equals(curEnt.getKey())){
					return i;
				}
				i=(i+1)%capacity;
			}
			while(i!=j);
			return -(avail+1);
		}
	}
	
	
	public boolean checkForKey(String key){
		//Checks if key is there, returns boolean
		return findEntry(key)>=0;
	}
	
	/*Returns the value associated with a key*/
	//Takes key and finding if that key is mapped to anything
	public Student get(String key){
		int i=findEntry(key);
		if(i<0){return null;}
		return userStorage[i].getValue();	//if anything is mapped returns
	}
	
	//Put a key-value pair in the ap, replacing previous one if it exists
	public void put(String key, Student value) {
		int i=findEntry(key); 		//finds appropriate spot for this entry
		if(numEnt>=capacity/2){
			rehash();
			i=findEntry(key); 	//finds appropriate spot for the entry
		}
		userStorage[-i-1] = new HashEntry(key,value); //creating new entry and putting inside userStorage
		numEnt++;
	}
	
	//Doubles the size of the hash table and rehashes all of the entries
	protected void rehash() {
		capacity = 2*capacity;
		HashEntry[] old = userStorage;
		userStorage = (HashEntry[]) new HashEntry[capacity];//new userStorage that is twice as big
		for(int i=0; i<old.length; i++){
			HashEntry curEnt = old[i];
			if((curEnt!=null)){
				int j = -1-findEntry(curEnt.getKey());
				userStorage[j] = curEnt;
			}
		}
	}
	
	//Removes the key-value pair with a specified key
	public Student remove (String key) {
		int entr = findEntry(key);
		if(entr<0) return null;		//if no value returns null
		Student toReturn = userStorage[entr].getValue();
		userStorage[entr]=null;		//action of removing by setting to null
		numEnt--;	//decreasing size
		return toReturn;
	}
	
	//hashSize gives the size of the hash
	//Returns the number of entries in the hash table
	public int hashSize(){return numEnt;} //numEnt = number of entries in hash
	public int size() {return numEnt;}
	
	
	public boolean isEmpty() {
		//Returns whether or not the table is empty 
		return numEnt==0;
	}
}
     