package com.abc.entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.springframework.beans.factory.annotation.Autowired;

import com.abc.annotation.processor.ODataInterface;
import com.abc.service.IVendorPersistence;

public class VendorODataAgent implements ODataInterface {
	
	@Autowired
	IVendorPersistence vendorAPI;
	
	@Override
	public List<?> getEntitySet() {
		// TODO Auto-generated method stub
		System.out.println("VendorSet_GET_ENTITYSET Called");
		return vendorAPI.findAll();
	}

	@Override
	public Object getEntity(Map<String, ?> keys) {
		// TODO Auto-generated method stub
		System.out.println("VendorSet_GET_ENTITY Called");
		return vendorAPI.findById((String) keys.get("Id")).get();
	}

	@Override
	public List<?> getRelatedEntity(Object source, String relatedEntityName, Map<String, Object> keys,
			Field sourceField) {
		// TODO Auto-generated method stub
		Vendor p;
		try {
			p = (Vendor)source;
		} catch (Exception e) {
			// TODO: handle exception
			Map<String, String> targetKey = (Map<String, String>) source;
			String vendorId = targetKey.get("Id");
			p = vendorAPI.findById(vendorId).get();
		}
		
		if(relatedEntityName.equalsIgnoreCase("AddressSet")) {
			return p.getAddresses();
		}
		
		return new ArrayList<>();
	}

	@Override
	public void createEntity(Object dataToCreate) {
		// TODO Auto-generated method stub
		System.out.println("VendorSet_CREATE_ENTITY Called");
		vendorAPI.save((Vendor)dataToCreate);

	}

	@Override
	public void deleteEntity(Map<String, ?> keys) {
		// TODO Auto-generated method stub
		System.out.println("VendorSet_DELETE_ENTITY Called");
		vendorAPI.deleteById((String)keys.get("Id"));
	}

	@Override
	public void updateEntity(Object dataToUpdate) {
		// TODO Auto-generated method stub
		vendorAPI.save((Vendor)dataToUpdate);
	}

	@Override
	public void writeRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet,
			Map<String, Object> targetKeys) {
		// TODO Auto-generated method stub

	}

}
