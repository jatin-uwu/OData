package com.abc.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abc.entities.Vendor;

public interface IVendorPersistence extends JpaRepository<Vendor, String> {
	List <Vendor> findByCompanyName(String companyName);
	
	@Query(nativeQuery = true,
		       value = "SELECT * FROM public.vendor WHERE LOWER(GST_NO) LIKE CONCAT('%', LOWER(?1), '%')")
		List<Vendor> lookupVendorByGST(String GSTNo);
}
