package com.abc.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entities.Address;

public interface IAddressPersistence extends JpaRepository<Address, String> {

}
