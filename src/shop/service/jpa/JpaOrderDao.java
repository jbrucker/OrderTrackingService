package shop.service.jpa;

import java.util.logging.Logger;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import shop.entity.Order;
import shop.entity.Orders;
import shop.service.OrderDao;

/**
 * Data access object for saving and retrieving contacts,
 * using JPA.
 * To get an instance of this class use:
 * <p>
 * <tt>
 * dao = DaoFactory.getInstance().getContactDao()
 * </tt>
 * 
 * @author jim
 */
public class JpaOrderDao extends OrderDao {

	/** the EntityManager for accessing JPA persistence services. */
	private final EntityManager em;

	/**
	 * Constructor with injected EntityManager to use.
	 * @param em an EntityManager for accessing JPA services
	 */
	public JpaOrderDao(EntityManager em) {
		this.em = em;
	}

	/**
	 * @see shop.service.OrderDao#find(long)
	 */
	@Override
	public Order find(long id) {
		return em.find(Order.class, id);
		//		Product productdesc = em.find(Product.class, id);
		//		ProductPrice productprice = em.find(ProductPrice.class, id);
		//		productdesc.setPrice(productprice.getPrice());
		//		return new Products(productdesc);
	}

	/**
	 * @return 
	 * @see shop.service.OrderDao#findAll()
	 */
	@Override
	public Orders findAll() {
		Query query = em.createQuery("SELECT o FROM Order o");
		//		ArrayList<Product> productList = new ArrayList<Product>(query.getResultList());
		//		for(int i = 0;i<productList.size();i++){
		//			long id = productList.get(i).getId();
		//			ProductPrice productprice = em.find(ProductPrice.class, id);
		//			productList.get(i).setPrice(productprice.getPrice());
		//		}
		//		Products result = new Products(productList);

		//		return result;
		return new Orders(query.getResultList());
	}

	@Override
	public boolean containID(long l) {
		Query query = em.createQuery("SELECT o FROM Order o where c.id = :id");
		query.setParameter("id", l);
		return !query.getResultList().isEmpty();	
	}

	@Override
	public boolean update(Order order) {
		EntityTransaction tx  = em.getTransaction();
		try{
			tx.begin();
			if(!containID(order.getId()))
				return false;
			em.find(Order.class, order.getId());
			em.merge(order);
			tx.commit();
			return true;
		}catch(EntityExistsException ex){
			Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
			if(tx.isActive())try{ tx.rollback(); } catch (Exception e) {}
			return false;
		}
	}
}
