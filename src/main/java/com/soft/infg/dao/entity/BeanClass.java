package com.soft.infg.dao.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="Medicine")
public class BeanClass implements Serializable{ 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="quant")

	
	private int quantity;
	
	@Column(name="price")
	private float price;
	@Column(name="MFG")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date mfgdate;
	@Column(name="Expiry")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date expirydate;
	@Column(name="Name")

	  @Size(min=2, message="Name should have atleast 2 characters")

	private String name;



	/*public BeanClass(int id, int quantity, float price,String yourDate, String expirydate, String name) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.mfgdate = yourDate;
		this.expirydate = expirydate;
		this.name = name;
	}
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getMfgdate() {
		return mfgdate;
	}
	public void setMfgdate(Date dateToValidate) {
		this.mfgdate = dateToValidate;
	}
	public Date getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
}
