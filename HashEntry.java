public class HashEntry {
	protected String key; //k is key is String, v is value is Student
	protected Student value;
	public HashEntry(String k, Student v) { key = k; value = v;}
	public Student getValue() {return value;}
	public String getKey() {return key;}
	public Student setValue(Student val) {
		Student oldValue = value;
		value = val;
		return oldValue;
	}
	public boolean equals(Object o){
		HashEntry ent;
		try{ent = (HashEntry) o; }   //turns o into HashEntry
		catch(ClassCastException ex) {return false;} //returns when o is not a HashEntry
		return(ent.getKey()==key) && (ent.getValue()==value); //returns if the key and value are equal
	}
}
