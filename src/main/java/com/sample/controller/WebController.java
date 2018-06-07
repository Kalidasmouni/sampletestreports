package com.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.Customer;
import com.sample.repository.CustomerRepository;

@RestController
public class WebController {
	@Autowired
	CustomerRepository repository;

	@RequestMapping(name = "/save", method = RequestMethod.POST, produces = { "application/json" })
	public String process() {
		repository.save(new Customer("Jack", "Smith"));
		repository.save(new Customer("Adam", "Johnson"));
		repository.save(new Customer("Kim", "Smith"));
		repository.save(new Customer("David", "Williams"));
		repository.save(new Customer("Peter", "Davis"));
		return "Done";
	}

	@RequestMapping(name = "/findall", method = RequestMethod.GET)
	public @ResponseBody List<Customer> findAll() {
		List<Customer> customerList = new ArrayList<Customer>();
		Iterable<Customer> customerIterator = repository.findAll();

		for (Customer customer : customerIterator) {
			customerList.add(customer);
		}

		return customerList;
	}

	@RequestMapping("/findbyid")
	public Customer findById(@RequestParam("id") long id) {
		return repository.findOne(id);
	}

	@RequestMapping("/findbylastname")
	public List<Customer> fetchDataByLastName(@RequestParam("lastname") String lastName) {
		List<Customer> customerList = new ArrayList<Customer>();
		for (Customer customer : repository.findByLastName(lastName)) {
			customerList.add(customer);
		}

		return customerList;
	}
	
	
	@RequestMapping("/greeting")
    public String greeting() {
        return "Hello, World";
    }
	
	
	
}
