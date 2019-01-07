
public class PriorityQueue {
	private Interval[] heap; // An array that encodes a max-heap data structure.
	private int size; // The size of allocated buffer for the heap.
	private int numElements; // The number of elements currently stored.

	/**
	 * Constructor: s is the initial size of the heap.
	 */
	public PriorityQueue(int s) {
		size = s;
		heap = new Interval[size + 1]; // 1 extra element allows us to use 1-based indexing. The max heap stores
										// intervals keyed on their lengths.
		numElements = 1;
	}

	private int parent(int i) {
		return i / 2;
	}

	private int left(int i) {
		return 2 * i;
	}

	private int right(int i) {
		return 2 * i + 1;
	}

	/**
	 * Inserts a new Interval k into the heap. Automatically expands the heap if the
	 * buffer allocated is full. TODO: Please complete this method.
	 */
	public void insert(Interval k) {
		if (numElements == size) {
			// Expand the buffer allocated for the heap to another buffer that is twice as
			// big.
			Interval[] oldHeap = heap;
			int oldsize = size;
			numElements = 1;
			size = 2 * size;
			heap = new Interval[size];
			for (int i = 1; i < oldsize; i++) {
				insert(oldHeap[i]);
			}

		}
		// Insert without buffer expansion here.
		int i = numElements;
		heap[i] = k;
		siftUp(i);
		numElements++;

	}

	private void siftUp(int i) {
		// TODO Auto-generated method stub
		while (heap[parent(i)] != null && heap[i].compareTo(heap[parent(i)]) >= 0) {
			Interval temp = heap[parent(i)];
			heap[parent(i)] = heap[i];
			heap[i] = temp;
			i = parent(i);
		}

	}

	/**
	 * Returns the maximum Interval from the heap (usually the one with the largest
	 * length. See the compareTo function of Interval for more details on the
	 * comparison. TODO: Please complete this method.
	 */
	public Interval remove_max() {
		if (numElements == 1)
			return null; // Retuns null if heap is empty.
		// Remove_max code here.

		Interval temp = heap[numElements - 1];
		heap[numElements - 1] = heap[1];
		heap[1] = temp;
		siftDown();
		numElements--;
		return heap[numElements];

	}

	private void siftDown() {
		int i = 1;
		while (2 * i < numElements - 1) {
			int largeChild = 2 * i;
			if (2 * i + 1 < numElements - 1 && heap[2 * i + 1].compareTo(heap[2 * i]) > 0) {
				largeChild = 2 * i + 1;
			}
			if (heap[i].compareTo(heap[largeChild]) < 0) {
				Interval temp = heap[largeChild];
				heap[largeChild] = heap[i];
				heap[i] = temp;
			} else {
				break;
			}
			i = largeChild;
		}

	}

	/**
	 * This function prints the contents of the array that encodes a heap.
	 */
	public void print() {
		System.out.println("Printing heap:");
		for (int i = 1; i < numElements; ++i)
			System.out.println(heap[i]);
	}
}
