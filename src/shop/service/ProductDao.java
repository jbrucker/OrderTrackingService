package shop.service;

import shop.entity.Products;

/**
 * An abstract for contact dao contains method for handling
 * with contact.
 * 
 * @author Poramate Homprakob 5510546077
 *
 */
public abstract class ProductDao {

	/** A runner id for auto assign id to a new contact. */
	private static int runnerId = 1;

	/**
	 * Get the exist contact in the list with specific id.
	 * @param id id of the contact that want to find
	 * @return contact with specific id, null if not found
	 */
	public abstract Products find(long id);
	
	/**
	 * Get list of all contacts.
	 * @return list of all contacts
	 */
	public abstract Products findAll();
}
