/**
 * Project: a01164474 Lab5
 * File: Lab5.java
 */

package a01164474;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import a01164474.data.CompareByJoinedDate;
import a01164474.data.Customer;
import a01164474.io.CustomerReader;
import a01164474.io.CustomerReport;
import a01164474.util.CopyFile;
import a01164474.util.Logging;

/**
 * To demonstrate knowledge Generics and Collections
 * 
 * Create a commandline program which reads customer data, creates Customer objects, adds them to a collection, and prints a simple Customer report
 * sorted by joined date
 * 
 * @author Sam Cirka, A00123456
 * @author Nathan Souza, A01164474
 *
 */
public class Lab5 {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Instant startTime = Instant.now();		
		Logging.LOG.info(startTime);		
		Lab5.run();	
		Instant endTime = Instant.now();
		Logging.LOG.info(endTime);
		Logging.LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}
	
	
	/**
	 * Populate the customers and print them out.
	 * @throws Exception 
	 */
	private static void run() throws Exception {
		CustomerReader readCustomersFile = new CustomerReader();
		CustomerReport report = new CustomerReport();
		
		Logging.LOG.info("Starting Logging.");
		try {
			report.addAllCustomers(readCustomersFile.read());
			// sort the customers by joined date
			ArrayList<Customer> listOfcustomers = readCustomersFile.read();
			Logging.LOG.info("listOfCustomers populated by customers.txt file");
			listOfcustomers.sort(new CompareByJoinedDate());
			Logging.LOG.info("listOfCustomers sorted by joined date");
			CustomerReport.write(listOfcustomers);
			CopyFile.backup();			
		} catch (Exception e) {
			Logging.LOG.error("Unable to generate customers_report.txt", e.getCause());
		}
		Logging.LOG.info("Exiting Logging.");
	}

}
