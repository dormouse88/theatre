/**
 * 
 */
package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author soumyakcherian
 *
 */
class CustomerTest {

	/**
	 * Test method for {@link model.Customer#Customer(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testCustomer() {
		String username = "testuser";
		String name = "Test User";
		String address = "10 Fairthorn Road, Charlton";
		
		Customer customer  = new Customer(username, name, address);
		
		assertTrue(customer != null, "Verify customer object creation");
		assertEquals(username, customer.getUsername(), "Verify getUsername");
		assertEquals(name, customer.getName(), "Verify getName");
		assertEquals(address, customer.getAddress(), "Verify getAddress");
	}

	/**
	 * Test method for {@link model.Customer#isComplete()}.
	 */
	@Test
	void testIsCompleteWithValues() {
		String username = "testuser";
		String name = "Test User";
		String address = "10 Fairthorn Road, Charlton";
		
		Customer customer  = new Customer(username, name, address);
		
		assertTrue(customer.isComplete(), "Verify customer isComplete true");
	}
	
	@Test
	void testIsCompleteWithoutName() {
		
		String username = "testuser";
		String name = "";
		String address = "10 Fairthorn Road, Charlton";
		
		Customer customer  = new Customer(username, name, address);
		
		assertFalse(customer.isComplete(), "Verify customer isComplete returns false on empty name");
		
	}
	
	@Test
	void testIsCompleteWithoutAddress() {
		
		String username = "testuser";
		String name = "Test User";
		String address = "";
		
		Customer customer  = new Customer(username, name, address);
		
		assertFalse(customer.isComplete(), "Verify customer isComplete returns false on empty address");
		
	}
	
	@Test
	void testIsCompleteWithNameNull() {
		
		String username = "testuser";
		String name = null;
		String address = "10 Fairthorn Road, Charlton";
		
		Customer customer  = new Customer(username, name, address);
		
		assertFalse(customer.isComplete(), "Verify customer isComplete returns false on null name");
		
	}
	
	@Test
	void testIsCompleteWithAddressNull() {
		
		String username = "testuser";
		String name = "Test User";
		String address = null;
		
		Customer customer  = new Customer(username, name, address);
		
		assertFalse(customer.isComplete(), "Verify customer isComplete returns false on null address");
		
	}

}
