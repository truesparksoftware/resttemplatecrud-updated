package com;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/getallbanks")
	public List<Bank> getAllBank() throws JSONException {
		Bank b = new Bank();
		HttpEntity<Bank> entity = new HttpEntity(b);
		ResponseEntity<List<Bank>> bankList = restTemplate.exchange("http://localhost:9090/bank/all", HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Bank>>() {
				});

		return bankList.getBody();
	}


	
	@GetMapping("/getbankbyid/{id}")
	public Bank getOneBank(@PathVariable Integer id) {
		HttpEntity entity = new HttpEntity<Bank>(new Bank());
		Map<String, Integer> vars = new HashMap<>();
		vars.put("id", id);
		ResponseEntity<Bank> bank = restTemplate.exchange("http://localhost:9090/bank//onebank/{id}", HttpMethod.GET, entity,
				Bank.class,vars);

		return bank.getBody();
	}
	
	
	@PostMapping("/save")
	public Bank saveBank(@RequestBody Bank bank) throws JsonMappingException, JsonProcessingException {
		HttpEntity<Bank> entity = new HttpEntity<Bank>(bank);
		ResponseEntity res = restTemplate.exchange("http://localhost:9090/bank/save", HttpMethod.POST, entity,
				Bank.class);
		ObjectMapper mapper = new ObjectMapper();
		Bank rootNode = mapper.readValue(res.toString(), Bank.class);
		return rootNode;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBank(@PathVariable Integer id) {
		HttpEntity<Integer> entity = new HttpEntity<Integer>(id);
		Map<String, Integer> vars = new HashMap<>();
		vars.put("id", id);
		ResponseEntity<String> res = restTemplate.exchange("http://localhost:9090/bank/delete/{id}", HttpMethod.DELETE,
				entity, String.class, vars);
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
