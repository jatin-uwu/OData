package com.abc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;
import org.hibernate.annotations.GenericGenerator;



import lombok.Getter;
import lombok.Setter;

@EdmEntitySet
@EdmEntityType
@Entity(name = "Address")
@Table(name="ADDRESS")
public class Address {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name="uuid2", strategy="org.hibernate.id.UUIDGenerator")
	@Column(nullable=false, name="ID")
	@EdmKey
	@EdmProperty
	private String addressId;
	@Column(name="TYPE")
	@EdmProperty
	private String addressType;
	@Column(name="STREET")
	@EdmProperty
	private String street;
	@Column(name="CITY")
	@EdmProperty
	private String city;
	@Column(name="COUNTRY")
	@EdmProperty
	private String country;
	@Column(name="REGION")
	@EdmProperty
	private String region;
	
	@ManyToOne
	@JoinColumn(name="vendor_id", referencedColumnName="id")
	@Setter
	@Getter
	public Vendor vendor;
	
	public Address() {
		
	}
	public Address(String addressId, String addressType, String street, String city, String country, String region) {
		super();
		this.addressId = addressId;
		this.addressType = addressType;
		this.street = street;
		this.city = city;
		this.country = country;
		this.region = region;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
