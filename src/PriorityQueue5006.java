import java.util.NoSuchElementException;

public class PriorityQueue5006<P> implements Cloneable {
	
	private Object[] element;
	private int manyitems;
	private int front;
	private int rear;
	private int[] priority;
	private int highestPrio;
	
	public PriorityQueue5006(int initialCapacity, int highest) {
		if(initialCapacity < 0) 
			throw new IllegalArgumentException("..");
		manyitems = 0;
		element = new Object[initialCapacity];
		priority = new int[initialCapacity];
		highestPrio = highest;
	}
	
	public void add(P item, int prio) {
		if(prio > highestPrio || prio < 0) {
			throw new IllegalArgumentException("..");
		}
		else {
			if(manyitems == element.length) {
				ensuresCapacity(manyitems*2 + 1);
			}
			if(manyitems == 0) {
				front = 0;
				rear = 0;
			}
			else {
				rear = nextIndex(rear);
			}
			element[rear] = item;
			priority[rear] = prio;
			manyitems++;
		}
	}
	
	private int nextIndex(int i) {
		if(++i == element.length)
			return 0;
		else
			return i;
	}
	
	public void ensuresCapacity(int minimumCapacity) {
		Object[] biggerArray;
		int n1, n2;
		if(element.length >= minimumCapacity) return;
		else if(front <= rear) {
			biggerArray = new Object[minimumCapacity];
			System.arraycopy(element, front, biggerArray, front, manyitems);
			element = biggerArray;
		} else{
			biggerArray = new Object[minimumCapacity];
			n1 = element.length-front; n2 = rear + 1;
			System.arraycopy(element, front, biggerArray, 0, n1);
			System.arraycopy(element, 0, biggerArray, n1, n2);
			front = 0;
			rear = manyitems - 1;
			element = biggerArray;
		}
	}
	
	@SuppressWarnings("unchecked")
	public P remove() {
		P answer;
		if(manyitems == 0) {
			throw new NoSuchElementException("..");
		}
		int prio = priority[rear];
		answer = (P) element[front];
		int index = front;
		
		for(int i = front; i <= rear; i++) {
			if(priority[i] > prio) {
				prio = priority[i];
				index = i;
				answer = (P)element[i];
			}
		}
		if(prio == priority[front]) {
			front = nextIndex(front);
		}
		else {
			for(int i = index; i <= rear; i++) {
				element[i] = element[i + 1];
				priority[i] = priority[i + 1];
			}
			rear = rear - 1;
		}
		manyitems = manyitems - 1;
		return answer;
	}
	
	@SuppressWarnings("unchecked")
	public PriorityQueue5006<P> clone() {
		PriorityQueue5006<P> answer;
		try {
			answer = (PriorityQueue5006<P>) super.clone();
		} catch(CloneNotSupportedException e) {
			throw new RuntimeException("..");
		}
		answer.element = element.clone();
		return answer;
	}
	
	public boolean isEmpty() {
		return(manyitems == 0);
	}
	
	public int getCapacity() {
		return element.length;
	}
	
	public void print() {
		for(int i = 0; i < element.length; i++) {
			System.out.print(element[i] + " ");
		}
			
	}
	
	public static void main(String arg[]) {
		
		PriorityQueue5006<String> ag = new PriorityQueue5006<String> (5, 15);
		
		ag.add("Apples", 2);
		ag.add("Oranges", 10);
		ag.add("Bananas", 5);
		ag.add("Peaches", 7);
		
		ag.print();
		
		ag.remove();
		System.out.println();
		ag.print();
	}
	
} 


