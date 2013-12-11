public class HashTable<K,V> {

	public class HashEntry<K,V> {
		protected K key;
		protected V value;
		public HashEntry(K k, V v) { key = k; value = v;}
		public V getValue() {return value;}
		public K getKey() {return key;}
		public V setValue(V val) {
			V oldValue = value;
			value = val;
			return oldValue;
		}
		public boolean equals(Object o){
			HashEntry<K,V> ent;
			try{ent = (HashEntry<K,V>) o; }   //turns o into HashEntry
			catch(ClassCastException ex) {return false;} //returns when o is not a HashEntry
			return(ent.getKey()==key) && (ent.getValue()==value); //returns if the key and value are equal
		}
	}
	protected int numEnt=0;	//number of entries
	protected int capacity;
	protected HashEntry<K,V>[] userStorage;	//userStorage array
	//Creates a hashtable with given capacity
	public HashTable(int cap) {		
		capacity=cap;
		userStorage = (HashEntry<K,V>[]) new HashEntry[capacity];
	}       
	
	//Determines if key is valid
	protected void checkKey(K email) {
		if (email==null){
			System.out.println("Invalid key");
		}
	}
	
	//Hash function that applies MAD method to default hash code
	public int hashValue(K key) {
		return (int) (Math.abs(key.hashCode()))%capacity;
	}
	
	
	//Returns the obect array that contains all keys
	public Object[] keySet() {
		Object[] keys = new Object[capacity];   //creates new key
		for(int i=0; i<capacity; i++) {
			if((userStorage[i]!=null)){
				keys[i]=userStorage[i].getKey();   //checks every key mapped into hash table and takes key and puts into array
			}
		}
		return keys;
	}
	
	/*Helper search method - returns index of found key or -(a-1) where a is he index
	of the first empty or available slot found (pages 403-404)*/
	//Checks if key is there
	public Integer findEntry(K key) {
		int avail= -1;
		if(key==null){
			System.out.println("Invalid entry");
			return null;
		}
		else{
			int i=hashValue(key);
			int j=i;
			do{
				HashEntry<K,V> curEnt =userStorage[i];
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
	
	
	public boolean checkEntry(K key){
		//Checks if key is there, returns boolean
	}
	
	/*Returns the value associated with a key*/
	//Takes key and finding if that key is mapped to anything
	public V get(K key){
		int i=findEntry(key);
		if(i<0){return null;}
		return userStorage[i].getValue();	//if anything is mapped returns
	}
	
	//Put a key-value pair in the ap, replacing previous one if it exists
	public void put(K key, V value) {
		int i=findEntry(key); 		//finds appropriate spot for this entry
		if(numEnt>=capacity/2){
			rehash();
			i=findEntry(key); 	//finds appropriate spot for the entry
		}
		userStorage[-i-1] = new HashEntry<K,V>(key,value); //creating new entry and putting inside userStorage
		numEnt++;
	}
	
	//Doubles the size of the hash table and rehashes all of the entries
	protected void rehash() {
		capacity = 2*capacity;
		HashEntry<K,V>[] old = userStorage;
		userStorage = (HashEntry<K,V>[]) new HashEntry[capacity];//new userStorage that is twice as big
		for(int i=0; i<old.length; i++){
			HashEntry<K,V> curEnt = old[i];
			if((curEnt!=null)){
				int j = -1-findEntry(curEnt.getKey());
				userStorage[j] = curEnt;
			}
		}
	}
	
	//Removes the key-value pair with a specified key
	public V remove (K key) {
		int entr = findEntry(key);
		if(entr<0) return null;		//if no value returns null
		V toReturn = userStorage[entr].getValue();
		userStorage[entr]=null;		//action of removing by setting to null
		numEnt--;	//decreasing size
		return toReturn;
	}
	
	
	public String[] returnListKeys(){
		//returns list of keys
		/* NEEDS DOING */
	}
	
	
	public V[] returnListValues(V value){
		//returns list of values
		/* NEEDS DOING */
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
     