package com.abc.configuration;

import javax.sql.DataSource;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class EntityManagerFactoryProvider {
	
	 public static LocalContainerEntityManagerFactoryBean get(
	            DataSource dataSource, String packageName) {

	        LocalContainerEntityManagerFactoryBean emf =
	                new LocalContainerEntityManagerFactoryBean();

	        emf.setDataSource(dataSource);
	        emf.setPackagesToScan(packageName);

	        return emf;



	}

}
