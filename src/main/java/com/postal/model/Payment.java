package com.postal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;
	
	private String paymentType;
	
	private int price;
	
	@OneToOne
	private Mail mail;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int pId, String paymentType, int price, Mail mail) {
		super();
		this.pId = pId;
		this.paymentType = paymentType;
		this.price = price;
		this.mail = mail;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}
	
	
	
	

}
