package answer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for memory-efficient HashBagIterator.
 * 
 * @author L. Buckingham
 *
 */

public class HashBagIteratorTest {
	static final String APPLES = "apple";
	static final String ORANGES = "oranges";
	static final String PEARS = "pears";

	Bag<String> bag;

	@Before
	public void setUp() throws Exception {
		bag = new HashBag<String>();
	}

	@Test
	public void test_EmptyCollection() {
		final int expected = 0;
		int count = 0;

		for (String item : bag) {
			count++;
		}

		assertEquals(expected, count);
	}

	@Test
	public void test_OneItemInCollection() throws BagException {
		bag.add(APPLES, 1);
		final int expected = 1;
		int count = 0;

		for (String item : bag) {
			if (APPLES.equals(item)) {
				count++;
			}
		}

		assertEquals(expected, count);
	}

	@Test
	public void test_MultipleItemsInCollection_OneType() throws BagException {
		bag.add(APPLES, 1);
		bag.add(APPLES,5);
		final int expected = 6;
		int count = 0;

		for (String item : bag) {
			if (APPLES.equals(item)) {
				count++;
			}
		}

		assertEquals(expected, count);
	}

	@Test
	public void test_MultipleItemsInCollection_TwoTypes() throws BagException {
		bag.add(APPLES, 1);
		bag.add(ORANGES,7);
		bag.add(APPLES,5);
		bag.add(ORANGES,19);
		final int expectedApples = 1 + 5;
		final int expectedOranges = 7 + 19;
		
		int appleCount = 0;
		int orangeCount = 0;

		for (String item : bag) {
			if (APPLES.equals(item)) {
				appleCount++;
			}
			else if ( ORANGES.equals(item)) {
				orangeCount ++;
			}
		}

		assertEquals(expectedApples, appleCount);
		assertEquals(expectedOranges, orangeCount);
	}

	@Test
	public void test_MultipleItemsInCollection_ThreeTypes() throws BagException {
		bag.add(APPLES, 1);
		bag.add(ORANGES,7);
		bag.add(PEARS, 47);
		bag.add(APPLES,5);
		bag.add(ORANGES,19);
		
		final int expectedApples = 1 + 5;
		final int expectedOranges = 7 + 19;
		final int expectedPears = 47;
		
		int appleCount = 0;
		int orangeCount = 0;
		int pearCount = 0;

		for (String item : bag) {
			if (APPLES.equals(item)) {
				appleCount++;
			}
			else if ( ORANGES.equals(item)) {
				orangeCount ++;
			}
			else if ( PEARS.equals(item)) {
				pearCount ++;
			}
		}

		assertEquals(expectedApples, appleCount);
		assertEquals(expectedOranges, orangeCount);
		assertEquals(expectedPears, pearCount);
	}

	@Test
	public void test_MassiveCollection_TwoTypes() throws BagException {
		final int GIGATONS = 1000000000;
		
		bag.add(APPLES, GIGATONS);
		bag.add(ORANGES,GIGATONS);

		final int expectedApples = GIGATONS;
		final int expectedOranges = GIGATONS;
		
		int appleCount = 0;
		int orangeCount = 0;

		for (String item : bag) {
			if (APPLES.equals(item)) {
				appleCount++;
			}
			else if ( ORANGES.equals(item)) {
				orangeCount ++;
			}
		}

		assertEquals(expectedApples, appleCount);
		assertEquals(expectedOranges, orangeCount);
	}

}
