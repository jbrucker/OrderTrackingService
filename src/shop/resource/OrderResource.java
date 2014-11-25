package shop.resource;

import java.net.URISyntaxException;
import java.sql.Time;
import java.util.Calendar;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

import shop.entity.Order;
import shop.entity.Orders;
import shop.service.DaoFactory;
import shop.service.OrderDao;

@Path("/products")
@Singleton
public class OrderResource {
	
	private OrderDao dao;
	private CacheControl cache;
	
	private static final int NOT_FOUND = 404;
	
	public OrderResource() {
		dao = DaoFactory.getInstance().getOrderDao();
		cache = new CacheControl();
		cache.setMaxAge(-1);
		cache.setPrivate(true);
		System.out.println("OrderResource Created");
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getContact(@Context Request request) {
		Orders orders = dao.findAll();
		return Response.ok(orders).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getOrder(@PathParam("id") int id, @Context Request request) {
		Order order = dao.find(id);
		if (order == null) {
			return Response.status(NOT_FOUND).build();
		}
		
		EntityTag etag = new EntityTag(getEtag(order));
		Response.ResponseBuilder builder = request.evaluatePreconditions(etag);
		// cache changed
		if (builder == null)
			builder = Response.ok(order).tag(etag);
			
		builder.cacheControl(cache);
		return builder.build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	public Response update (@PathParam("id") long id,JAXBElement<Order> order,@Context Request req) throws URISyntaxException{
		if(!dao.containID(id))
			return Response.status(Status.NOT_FOUND).build();
		Response.ResponseBuilder rb = null;
		Order orderGet = dao.find(id);
		EntityTag etag = new EntityTag(getEtag(orderGet));
		rb = req.evaluatePreconditions(etag);
		if(rb!= null){
			return rb.cacheControl(cache).build();
		}
		Order orderReceive = (Order)order.getValue();
		for(shop.entity.Status status : orderReceive.getStatusList() ){		
			status.setDate(Calendar.getInstance().getTime().toString());
			orderGet.addStatus(status);
		}		
		dao.update(orderGet);
		return Response.ok().build();
	}

	
	public String getEtag(Order order){
		return Integer.toString(order.hashCode());
	}
}
