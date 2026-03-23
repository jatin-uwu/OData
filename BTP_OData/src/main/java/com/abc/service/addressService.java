package com.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.abc.entities.*;

@Component
public class addressService {

	@Autowired
	IAddressPersistence address;
	
	public List<Address> getAddress(){
		return address.findAll();
	}
	
	public Address createAddress(Address payload) {
		return address.save(payload);
	}
	
}
