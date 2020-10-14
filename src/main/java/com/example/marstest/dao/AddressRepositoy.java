package com.example.marstest.dao;

import com.example.marstest.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepositoy extends JpaRepository<Address,Integer> {

}
