/**
 * Project: a01164474 Lab5
 * File: CustomerReader.java
 */

package a01164474.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import a01164474.ApplicationException;
import a01164474.data.Customer;
import a01164474.util.Logging;
import a01164474.util.Validator;

/**
 * Read the customer input.
 * 
 * @author Sam Cirka, A00123456
 * @author Nathan Souza, A01164474
 *
 */
public class CustomerReader {

	public static final String FIELD_DELIMITER = "\\|";
	
	/**
	 * private constructor to prevent instantiation
	 */
	public CustomerReader() {
	}


	/**
	 * Parse a Customer data string into a Customer object;
	 * 
	 * @param row
	 * @throws ApplicationException
	 */
	public ArrayList<Customer> read() throws Exception {
		
		ArrayList<String> listFromFile = readFileOfCustomer();
		ArrayList<Customer> customersList = new ArrayList<>();
		Customer newCustomer;
		
		for (int i = 0; i < listFromFile.size(); i++) {		
			String[] elements = listFromFile.get(i).split(FIELD_DELIMITER);
			if (elements.length != Customer.ATTRIBUTE_COUNT) {
				Logging.LOG.error(String.format("Expected %d but got %d: %s", Customer.ATTRIBUTE_COUNT, elements.length, Arrays.toString(elements)));
			} 
			
			int index = 0;
			long id = Integer.parseInt(elements[index++]);
			String firstName = elements[index++];
			String lastName = elements[index++];
			String street = elements[index++];
			String city = elements[index++];
			String postalCode = elements[index++];
			String phone = elements[index++];
			// should the email validation be performed here or in the customer class? I'm leaning towards putting it here.
			String emailAddress = elements[index++];
			if (!Validator.validateEmail(emailAddress)) {
				Logging.LOG.error(String.format("Invalid email: %s", emailAddress));
			}
			String yyyymmdd = elements[index];
			if (!Validator.validateJoinedDate(yyyymmdd)) {
				Logging.LOG.error(String.format("Invalid joined date: %s for customer %d", yyyymmdd, id));
			}
			int year = Integer.parseInt(yyyymmdd.substring(0, 4));
			int month = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
			int day = Integer.parseInt(yyyymmdd.substring(6, 8));
		
			newCustomer = new Customer.Builder(id, phone).setFirstName(firstName).setLastName(lastName).setStreet(street).setCity(city).setPostalCode(postalCode)
					.setEmailAddress(emailAddress).setJoinedDate(year, month, day).build();
			
			customersList.add(newCustomer);
		}
		
		return customersList;
	}
	
	
	private ArrayList<String> readFileOfCustomer() throws Exception {

		ArrayList<String> customersLines = new ArrayList<>();

		File sourceFile = new File("customers.txt");
		if (!sourceFile.exists()) {
			Logging.LOG.error("File customers.txt does not exist!");
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(sourceFile));
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				customersLines.add(line);
			}
			br.close();
		} catch (IOException e) {
			throw new Exception(e.getCause());
		}
		return customersLines;
	}

}
