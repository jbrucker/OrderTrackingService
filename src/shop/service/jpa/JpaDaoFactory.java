package shop.service.jpa;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import shop.service.DaoFactory;
import shop.service.ProductDao;

/**
 * JpaDaoFactory is a factory for DAO that use the Java Persistence API (JPA)
 * to persist objects.
 * The factory depends on the configuration information in META-INF/persistence.xml.
 * 
 * @see shop.service.DaoFactory
 * @version 2014.09.19
 * @author jim
 */
public class JpaDaoFactory extends DaoFactory {
	
	private static final String PERSISTENCE_UNIT = "product";
	
	/** instance of the entity DAO */
	private ProductDao contactDao;
	private final EntityManagerFactory emf;
	private EntityManager em;
	private static Logger logger;
	
	static {
		logger = Logger.getLogger(JpaDaoFactory.class.getName());
	}
	
	/**
	 * Constructor require nothing.
	 */
	public JpaDaoFactory() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
		contactDao = new JpaProductDao( em );
	}
	
	/**
	 * @see shop.service.DaoFactory#getContactDao()
	 */
	@Override
	public ProductDao getContactDao() {
		return contactDao;
	}
	
	/**
	 * @see shop.service.DaoFactory#shutdown()
	 */
	@Override
	public void shutdown() {
		try {
			if (em != null && em.isOpen()) em.close();
			if (emf != null && emf.isOpen()) emf.close();
		} catch (IllegalStateException ex) {
			ex.getStackTrace();
		}
	}
}
