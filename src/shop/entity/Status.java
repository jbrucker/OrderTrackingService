package shop.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@XmlAccessorType (XmlAccessType.FIELD)
public class Status {
	
	@XmlAttribute
	private StatusType statusType;
	@XmlElement
	private String date;
	@XmlElement
	private String description;
	@XmlElement
	private String updatedby;
	
	protected Status() {
		this(null,null,null,null);
	}
	
	public Status(StatusType statusType,String date,String description,String updatedby){
		this.statusType = statusType;
		this.date = date;
		this.description = description;
		this.updatedby = updatedby;
	}
}
