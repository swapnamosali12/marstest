package com.example.marstest.resource;

import com.example.marstest.dao.AddressRepositoy;
import com.example.marstest.dao.PersonRepository;
import com.example.marstest.model.Address;
import com.example.marstest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PersonResource {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressRepositoy addressRepositoy;

    @PostMapping("/saveperson")
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        Person persn = personRepository.save(person);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(persn, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/editperson")
    public ResponseEntity<?> editPerson(@PathVariable(value = "id") int id, @RequestBody Person person) {
        ResponseEntity<?> responseEntity = null;
        if (null == person) {
            responseEntity = new ResponseEntity<>(" provide details", HttpStatus.OK);
        }
        Person oldperson = personRepository.findById(id).orElse(null);
        if (oldperson == null) {
            responseEntity = new ResponseEntity<>("no person to edit", HttpStatus.OK);
        } else {
            if (null != person.getFirstName()) {
                oldperson.setFirstName(person.getFirstName());
            }
            if (null != person.getLastName()) {
                oldperson.setLastName(person.getLastName());
            }
        }
        Person updated = personRepository.save(oldperson);
        responseEntity = new ResponseEntity<>(updated, HttpStatus.OK);
        return responseEntity;
    }


    @PutMapping("/deleteperson")
    public ResponseEntity<?> deletePerson(@RequestParam Integer id) {
        ResponseEntity<?> responseEntity = null;

        personRepository.deleteById(id);
        responseEntity = new ResponseEntity<>("deleted successfully", HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/addaddress")
    public ResponseEntity<?> addadress(@PathVariable(value = "id") int id, @RequestBody List<Address> address) {
        ResponseEntity<?> responseEntity = null;
        Person oldperson = personRepository.findById(id).orElse(null);
        if (oldperson == null) {
            responseEntity = new ResponseEntity<>("no person to add address", HttpStatus.OK);
        } else {
            if (null == oldperson.getAddress()) {
                oldperson.setAddress(address);
            } else {
                oldperson.getAddress().addAll(address);
            }
        }
        Person person = personRepository.save(oldperson);
        responseEntity = new ResponseEntity<>(person, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/editaddress")
    public ResponseEntity<?> editAddress(@PathVariable(value = "id") int id, @RequestBody Address address) {
        ResponseEntity<?> responseEntity = null;
        if (null == address) {
            responseEntity = new ResponseEntity<>(" provide details", HttpStatus.OK);
        }
        Address oldAddrees = addressRepositoy.findById(id).orElse(null);
        if (oldAddrees == null) {
            responseEntity = new ResponseEntity<>("no address to edit", HttpStatus.OK);
        } else {
            if (null != address.getCity()) {
                oldAddrees.setCity(address.getCity());
            }
            if (null != address.getPostalCode()) {
                oldAddrees.setPostalCode(address.getPostalCode());
            }
            if (null != address.getState()) {
                oldAddrees.setState(address.getState());
            }
            if (null != address.getStreet()) {
                oldAddrees.setStreet(address.getStreet());
            }
            oldAddrees.setPersonId(address.getPersonId());
        }
        Address updated = addressRepositoy.save(oldAddrees);
        responseEntity = new ResponseEntity<>(updated, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/deletaddresss")
    public ResponseEntity<?> deleteAddress(@RequestParam Integer id) {
        ResponseEntity<?> responseEntity = null;

        addressRepositoy.deleteById(id);
        responseEntity = new ResponseEntity<>("deleted successfully", HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/personcount")
    public ResponseEntity<?> personCount() {
        ResponseEntity<?> responseEntity = null;

        Long totalNoofPersons = personRepository.count();
        responseEntity = new ResponseEntity<>(totalNoofPersons, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/getpersons")
    public ResponseEntity<?> getPersons(@RequestParam List<Integer> listIds) {
        ResponseEntity<?> responseEntity = null;
        if (null == listIds) {
            responseEntity = new ResponseEntity<>(" provide details", HttpStatus.OK);
        }
        List<Person> persn = personRepository.findByIdIn(listIds);
        responseEntity = new ResponseEntity<>(persn, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/getAllPersons")
    public ResponseEntity<?> getAllPersons(@RequestParam List<Integer> listIds) {
        ResponseEntity<?> responseEntity = null;
        if (null == listIds) {
            responseEntity = new ResponseEntity<>(" provide details", HttpStatus.OK);
        }
        List<Person> persn = personRepository.findAll();
        responseEntity = new ResponseEntity<>(persn, HttpStatus.OK);
        return responseEntity;
    }

}