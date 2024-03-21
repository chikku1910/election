package org.mgm.elector.model;

import java.io.Serializable;

import javax.swing.JOptionPane;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 5552081508968527897L;
	@Column(nullable = false)
	private String houseName;
	private String street;
	@Column(nullable = false)
	private String place;
	private String district;
	@Column(nullable = false,length = 6)
	private String pinCode;
	public Address(String houseName, String street, String place, String district, String pinCode) {
		super();
		this.houseName = houseName;
		this.street = street;
		this.place = place;
		this.district = district;
		this.pinCode = pinCode;
	}
	public Address() {
		super();
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		if (houseName==null)
			throw new IllegalArgumentException("houseName cannot be null");
		this.houseName = houseName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		if (place==null)
			throw new IllegalArgumentException("place cannot be null");
		this.place = place;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		if (pinCode==null)
			throw new IllegalArgumentException("pincode cannot be null");
		if (pinCode.length()!=6) {
			throw new IllegalArgumentException("invalid pincode");
		}
		try {
			Integer.parseInt(pinCode);
			this.pinCode=pinCode;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "pincode should be numbers!","INVALID INPUT ERROR",JOptionPane.WARNING_MESSAGE);
		}
			
	}
	
}
