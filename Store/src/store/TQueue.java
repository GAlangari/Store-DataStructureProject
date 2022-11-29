//Ghaida Alangari, 439017383, 374
package store;


public class TQueue<E> {
	private TNode<E> head;
	private TNode<E> tail;
	private int size;

	public TQueue() {
		head = null;
		tail = null;
		size = 0;
	}

	public void enqueue(E e) {
		TNode<E> n = new TNode<E>();
		n.setItem(e);
		if (tail == null) {
			head = n;
			tail = n;
		}
		else {
			tail.setRight(n);
			tail = n;
		}
		size++;
	}

	public E dequeue() {
		if (head != null) {
			E e = head.getItem();
			head = head.getRight();
			if (head == null)
				tail =  null;
			size--;
			return e;
		}
		return null;
	}

	public E first() {
		if (head != null) {
			return head.getItem();
		}
		return null;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public void print() {
		TNode<E> n =  head;
		while (n != null) {
			System.out.println(n.getItem());
			n = n.getRight();
		}
	}
}


