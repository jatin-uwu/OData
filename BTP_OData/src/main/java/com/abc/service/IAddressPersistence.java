package com.abc.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entities.address;

public interface IAddressPersistence extends JpaRepository<address, String> {

}
