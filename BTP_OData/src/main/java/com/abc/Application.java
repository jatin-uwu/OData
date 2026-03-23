package com.abc;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.abc.annotation.processor.MyODataServiceFactory;
import com.abc.entities.AddressOdataAgent;
import com.abc.entities.VendorODataAgent;

@DependsOn("springUtils")
@SpringBootApplication(scanBasePackages = "com.abc")
@EnableJpaRepositories(basePackages = "com.abc")
@EntityScan(basePackages = "com.abc.entities") 
@ServletComponentScan(basePackages = "com.abc")
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean(name="com.abc.annotation.processor.MyODataServiceFactory")
	public ODataServiceFactory getServiceFactory(){
	    return new MyODataServiceFactory();
	}
	
	
	@Bean(name="com.abc.entities.VendorODataAgent")
	public VendorODataAgent vendorODataAgent(){
		return new VendorODataAgent();
	}
	
	@Bean(name="com.abc.entities.addressODataAgent")
	public AddressOdataAgent addressODataAgent(){
		return new AddressOdataAgent();
	}

}
