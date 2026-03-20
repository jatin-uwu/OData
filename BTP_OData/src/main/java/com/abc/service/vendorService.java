package com.abc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abc.entities.Vendor;

@Component
public class vendorService {
	
	@Autowired
	IVendorPersistence vendor;
	
	public List<Vendor> readAllVendors(){
		return vendor.findAll();
	}
	
//	public Vendor getSingleVendorById(String vendorCode) {
//		
//		return (Vendor)vendors.get(vendorCode);
//	}
//	
//	public Vendor changeVendor(Vendor vendor) {
//		return vendor;
//	}
//	
	public Vendor createVendor(Vendor vendorObj) {
		return vendor.save(vendorObj);
	}
	
	public List<Vendor> searchByCompanyName(String companyName){
		return vendor.findByCompanyName(companyName);
	}
	
	public List<Vendor> lookupVendorByGST(String GSTNo){
		return vendor.lookupVendorByGST(GSTNo);
	} 
	
	public Vendor getSingleVendor(String id) {
		Optional<Vendor> searchResult = vendor.findById(id);
		if(!searchResult.isPresent()) {
			return new Vendor((String)"", "","","","","","", null);
		}
		return searchResult.get();
	}
	
	public Vendor changeVendor(Vendor payload) {
		Optional<Vendor> myVendor = vendor.findById(payload.getId());
		if(!myVendor.isPresent()) {
			return new Vendor((String)"", "","","","","","", null);
		}
		return vendor.save(payload);
	}
	
	public String deleteVendor(String Id) {
		vendor.deleteById(Id);
		return "Deleted Successfully";
	}
	
	
}
