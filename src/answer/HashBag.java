package answer;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;


/**
 * An implementation of the Bag class based on HashMaps.
 * 
 * @author Colin Fidge
 * @version 2.2
 *
 * @param <E> The type of elements contained in the bag
 */
public class HashBag<E> extends Bag<E> {

	/* The data structure used to implement bags
	 *
	 * Only elements with a positive number of occurrences
	 * in the bag appear in the map.  Elements that occur
	 * zero times in the bag are not stored in the map.
	 */
	private Map<E, Integer> contents;

	public HashBag() {
		contents = new HashMap<E, Integer>();
	}

	@Override
	/*
	 * We have chosen to make adding a negative number of items an error,
	 * although you could also consider it equivalent to removing that
	 * number of items.
	 */
	public void add(E newElement, int quantity) throws BagException {
		if (quantity < 0)
			throw new BagException("Attempt to add negative number of items to bag");
		else if (contents.containsKey(newElement)) // already in bag
			contents.put(newElement, quantity + contents.get(newElement));
		else // new element for bag
			contents.put(newElement, quantity);
	}

	@Override
	public int quantity(E element) {
		if (contents.containsKey(element)) // element is in bag
			return contents.get(element);
		else // element not in bag at all
			return 0;
	}

	@Override
	/*
	 * We have chosen to treat an attempt to remove a negative number of items
	 * as an error, although it could be validly considered the same as
	 * adding items.  Similarly, we consider trying to remove more items
	 * than are in the bag as an error, although you could instead just
	 * reduce the number of items to zero in this case.
	 */
	public void remove(E oldElement, int numToGo) throws BagException {
		if (numToGo < 0)
			throw new BagException("Attempt to remove negative number of items");
		else if (quantity(oldElement) < numToGo) // try to remove too many
			throw new BagException("Attempt to remove nonexistent items");
		else
			contents.put(oldElement, quantity(oldElement) - numToGo);
	}

	@Override
	public Set<E> toSet() {
		return contents.keySet();
	}

	/**
	 * An efficient iterator for bags.  This implementation does not 
	 * guarantee the order of distinct elements, but returns all copies 
	 * of the same element together.
	 */
	@Override
	public Iterator<E> iterator() {
		return new HashBagIterator<E>( contents.entrySet().iterator() );
	}
	
	/*
	 * A naive, and simple, implementation of an iterator for bags.
	 * This implementation does not guarantee the order of distinct
	 * elements, but returns all copies of the same element together.
	 *
	@Override
	public Iterator<E> iterator() {
		ArrayList<E> allElements = new ArrayList<E>();
		for (E element : toSet())
			for (int index = quantity(element); index > 0; index--)
				allElements.add(element);
		return allElements.iterator();
	}
	*/

	/**
	 * Returns the total number of elements in the bag, counting
	 * duplicates.
	 * 
	 * @return the total number of elements in the bag
	 */
	@Override
	public int size() {
		int total = 0;
		for (E element : toSet())
			total += quantity(element);
		return total;
	}

}
