package answer;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

/**
 * A memory-efficient iterator for HashBag.
 * 
 * @author Lawrence Buckingham
 * 
 * @param <E> 
 */

public class HashBagIterator<E> implements Iterator<E> {

	/**
	 * An iterator which can be used to traverse the underlying collection of
	 * entries.
	 */
	private Iterator<Entry<E, Integer>> collectionIterator;

	/**
	 * A reference to an entry in the underlying collection. This acts as a
	 * cursor which performs a traversal of the collection.
	 * <p>
	 * This value will be null if we have exhausted the collection, and non-null
	 * otherwise.
	 * </p>
	 */
	private Entry<E, Integer> currentEntry;

	/**
	 * A counter which iterates over the "elements" of the current entry.
	 * <p>
	 * Invariant: currentEntry == null || 1 &lt;= currentCount &lt;=
	 * currentEntry.getValue()
	 * </p>
	 */
	private int currentCount;

	/**
	 * Initialises a HashBagIterator, attaching it to an iterator which will
	 * traverse the underlying collection.
	 * 
	 * @param iterator
	 *            A non-null iterator which is used to traverse the underlying
	 *            collection.
	 */
	public HashBagIterator(Iterator<Entry<E, Integer>> iterator) {
		collectionIterator = iterator;
		advanceEntry();
	}

	@Override
	public boolean hasNext() {
		return currentEntry != null;
	}

	@Override
	public E next() {
		if (currentEntry == null) {
			throw new NoSuchElementException();
		}

		E nextItem = currentEntry.getKey();
		
		if (currentCount == currentEntry.getValue()) {
			// All items in the current entry have been processed.
			advanceEntry();
		} else {
			// There are more items to process in the current entry.
			currentCount++;
		}

		return nextItem;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Attempts to advance the current entry to the next available entry
	 * containing a positive number of items (as it is not meaningful to repeat
	 * zero or fewer times). If we have reached the end of the underlying
	 * collection, current entry will be null. The current counter is set to 1.
	 */

	private void advanceEntry() {
		do {
			currentEntry = collectionIterator.hasNext() ? collectionIterator.next() : null;
		} while (currentEntry != null && currentEntry.getValue() <= 0);

		currentCount = 1;
	}

}
