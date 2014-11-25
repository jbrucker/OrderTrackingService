package shop.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@XmlAccessorType (XmlAccessType.FIELD)
public class Order {

	@Id
	@XmlAttribute
	private long id;
	@XmlElement
	private List<Status> statuslist;
	
	protected Order() {
		this(0,null);
	}
	
	public Order(long id,List<Status> statuslist){
		this.id = id;
		this.statuslist = statuslist;
	}
	
	public void addStatus(Status status){
		statuslist.add(status);
	}
}
