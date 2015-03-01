public class DLinkedList<E> implements ListADT<E>{	

	private Listnode<E> head;
	private Listnode<E> tail;
	private int numItems;

	public DLinkedList(){
		head = null;
		tail = null;
		numItems = 0;
	}

	public void add(E item) {
		if (item == null) throw new IllegalArgumentException();
		Listnode <E> newnode = new Listnode<E>(item);
		Listnode <E> curr = head;
		if (head == null){
			head = newnode;
			tail = newnode;
		} else {
			//E temp;
			tail.setNext(newnode);
			newnode.setPrev(tail);
			tail = newnode;
		}
		numItems ++;
	}

	public void add(int pos, E item) {
		if (pos < 0 || pos > numItems) throw new IndexOutOfBoundsException();

		Listnode<E> curr = head;
		for (int k = 0; k < pos; k++) {
			curr = curr.getNext();
		}
		Listnode<E> newnode = new Listnode<E>(item);
		newnode.setNext(curr.getNext());
		curr.getNext().setPrev(newnode);
		newnode.setPrev(curr);
		curr.setNext(newnode);
		numItems++;
	}


	public boolean contains(E item) {
		if (item == null) throw new IllegalArgumentException();
		Listnode <E> curr = head;
		while(curr.getNext() != null){
			curr = curr.getNext();
			if(curr.getData().equals(item)){
				return true;
			}
		}
		return false;
	}

	public E get(int pos) {
		E data = null;
		if(pos < 0 || pos >= numItems)
			throw new IndexOutOfBoundsException();
		Listnode<E> curr = head;

		for(int i = 0; i < pos; i ++){
			curr = curr.getNext();
		}
		data = curr.getData();
		return data;
	}

	public boolean isEmpty() {
		if (numItems == 0 ){
			return true;
		}
 		return false;
	}

	public E remove(int pos) {
		if(pos < 0 || pos > numItems) throw new IllegalArgumentException();

		Listnode<E> curr = head;
		for(int i = 0; i < pos; i ++){
			curr = curr.getNext();
		}
		curr.setNext(curr.getNext().getNext());
		curr.getNext().setPrev(curr);
		return null;	
	}

	public int size() {
		return numItems;
	}

}