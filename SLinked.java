public class SLinked{
	protected Singly head;
	protected int size;
	public SLinked(){
		head = null;
		size = 0;
	}
	//update and search methods.
	public void update(Student user){
		Singly temp = new Singly(user, head.getNext());
		head = temp;
		size++;
	}

	public boolean check(Student user){
		Singly pointer = head.getNext();
		while(pointer.getNext()!=null){
			if (pointer.getElement() == user){
				return true;
			}
		}
		return false;
	}
}
