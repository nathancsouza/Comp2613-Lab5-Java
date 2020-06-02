/**
 * Project: a01164474 Lab5
 * File: CompareByJoinedDate.java
 */

package a01164474.data;

import java.util.Comparator;

/**
 * @author Sam Cirka, A00123456
 * @author Nathan Souza, A01164474
 *
 */
public class CompareByJoinedDate implements Comparator<Customer> {
	@Override
	public int compare(Customer customer1, Customer customer2) {
		return customer1.getJoinedDate().compareTo(customer2.getJoinedDate());
	}
}
