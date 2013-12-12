public class Singly{ // Nodes of a singly linked list of S's.
	private Student element;
	private Singly next;
	public Singly(Student prs, Singly nextIn){ // Creates a node with the given element and the next node.
		element = prs;
		next = nextIn;
	}

	public Student getElement() { return element; } // returns the element of this node.

	public Singly getNext() { return next; }

	public void setElement(Student newElem) { element = newElem; }

	public void setNext(Singly newNext) { next = newNext; }

}
