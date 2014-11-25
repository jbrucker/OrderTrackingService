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
	public StatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

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
