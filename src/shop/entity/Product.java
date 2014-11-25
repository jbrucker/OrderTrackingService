package shop.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name="ProductDescriptions")
@Table(name="ProductDescriptions")
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProductDescriptionId")
	@XmlAttribute
	private long id;
	
	@XmlElement
	@Column(name="ProductName")
	private String name;
	@Transient
	private double price;
	@Column(name="Description")
	private String description;
	
	
	protected Product() {
		
	}
	
	public void setPrice(double p) {
		price = p;
	}

	public long getId() {
		return id;
	}
}
