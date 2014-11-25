package shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Orders {
	@XmlElement(name = "Order")
	private List<Order> Orders;

	public Orders() {
		Orders = new ArrayList<Order>();
	}
	
	public Orders(Order Order) {
		Orders = new ArrayList<Order>();
		addOrder(Order);
	}
	
	public Orders(List<Order> list) {
		Orders = list;
	}
	
	public void addOrder(Order Order) {
		Orders.add(Order);
	}

}
