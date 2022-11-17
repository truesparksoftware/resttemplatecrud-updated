package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;



@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/getallbanks")
	public List<Bank> getAllBank() {
		Bank b = new Bank();
		HttpEntity entity = new HttpEntity(b);
		ResponseEntity res = restTemplate.exchange("http://localhost:9090/bank/onebank", HttpMethod.GET, entity,
				Bank.class);

		System.out.println(res.getBody().toString());

		return null;
	}

	@PostMapping("/save")
	public Bank saveBank(@RequestBody Bank bank) {
		HttpEntity<Bank> entity = new HttpEntity<Bank>(bank);
		ResponseEntity res = restTemplate.exchange("http://localhost:9090/bank/save", HttpMethod.POST, entity,
				Bank.class);
		Bank b1 = (Bank) res.getBody();
		return b1;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBank(@PathVariable Integer id) {
		HttpEntity<Integer> entity = new HttpEntity<Integer>(id);
		Map<String, Integer> vars = new HashMap<>();
		vars.put("id", id);

		ResponseEntity<String> res = restTemplate.exchange("http://localhost:9090/bank/delete/{id}", HttpMethod.DELETE,
				entity, String.class, vars);
		System.out.println("responseEntity...." + res.toString());

		System.out.println("responseEntity...." + res.getBody());
		return res.getBody();
	}

	@PutMapping("/update")
	public Bank updateBank(@RequestBody Bank bank) {
		HttpEntity<Bank> entity = new HttpEntity<Bank>(bank);
		ResponseEntity res = restTemplate.exchange("http://localhost:9090/bank/update", HttpMethod.PUT, entity,
				Bank.class);
		Bank b1 = (Bank) res.getBody();
		return b1;
	}

}
