/**
 * Project: a01164474 Lab5
 * File: CustomerReport.java
 */

package a01164474.io;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import a01164474.ApplicationException;
import a01164474.data.Customer;
import a01164474.util.Common;
import a01164474.util.Logging;


/**
 * Prints a formated Customers report.
 * 
 * @author Sam Cirka, A00123456
 * @author Nathan Souza, A01164474
 *
 */
public class CustomerReport {
	
	public static final String HORIZONTAL_LINE = "----------------------------------------------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%3s. %-6s %-12s %-12s %-25s %-12s %-12s %-15s %-25s%s%n";
	public static final String CUSTOMER_FORMAT = "%3d. %06d %-12s %-12s %-25s %-12s %-12s %-15s %-25s%s%n";
	/**
	 * private constructor to prevent instantiation
	 */
	public CustomerReport() {
	}
	
	private ArrayList<Customer> listOfCustomers = new ArrayList<>();
	
	public void addCustomer(Customer data) {
		listOfCustomers.add(data);
	}

	public void addAllCustomers(ArrayList<Customer> data) {
		listOfCustomers.addAll(data);
	}

	/**
	 * Print the report.
	 * @param listOfCustomers 
	 * @param readCustomersFile 
	 * 
	 * @param customers
	 * @throws ApplicationException 
	 */
	public static void write(ArrayList<Customer> listOfCustomers) throws Exception {
		
		PrintWriter output = null;
		File sourceFile = new File("customers_report.txt");
		
		try {
			
			output = new PrintWriter(new FileWriter(sourceFile));
			
			output.println("Customers Report - Ascending order according to join date");
			output.println(HORIZONTAL_LINE);
			output.format(HEADER_FORMAT, "#", "ID", "First name", "Last name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date");
			output.println(HORIZONTAL_LINE);

			int i = 0;
			for (Customer customer : listOfCustomers) {
				LocalDate date = customer.getJoinedDate();
				output.format(CUSTOMER_FORMAT, ++i, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreet(),
						customer.getCity(), customer.getPostalCode(), customer.getPhone(), customer.getEmailAddress(), Common.DATE_FORMAT.format(date));			
			}
			Logging.LOG.info("Your file customers_report.txt was successfully generated");
		} catch (Exception e) {
			Logging.LOG.error(e.getMessage());
		} finally {
			output.close();
		}				
	}
	
}
