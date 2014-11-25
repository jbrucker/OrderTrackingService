package shop.service;

import shop.service.jpa.JpaDaoFactory;

/**
 * An abstract for dao factory, and make the factory to be singleton.
 *  
 * @author Poramate Homprakob 5510546077
 *
 */
public abstract class DaoFactory {
	
	/** Singleton instance of this subclass. **/
	private static DaoFactory factory;
	
	/**
	 * Constructor require nothing, visible only for subclass.
	 */
	protected DaoFactory() {
		
	}
	
	/**
	 * Generate and return this subclass factory.
	 * @return singleton instance of this subclass
	 */
	public static DaoFactory getInstance() {
		if (factory == null)
			factory = new JpaDaoFactory();
		
		return factory;
	}
	
	/**
	 * Return this subclass factory.
	 * @return singleton instance of this subclass
	 */
	public abstract OrderDao getOrderDao();
	
	/**
	 * Method contains function to handle shutdown for DaoFactory.
	 */
	public abstract void shutdown();

}
