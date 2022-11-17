package com;

import java.util.*;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankRepository bankRepository;

	@GetMapping("/all")
	public List<Bank> getAllBanks() {
		// System.out.println("jwt token"+jwt);
		List<Bank> bankList = bankRepository.findAll();
		return bankList;
	}

	@GetMapping("/onebank")
	public Bank getOneBank() {
		// System.out.println("jwt token"+jwt);
		Bank b1 = new Bank(123, "INDUs", "Mum", "govt");
		return b1;
	}

	@PostMapping("/save")
	public Bank saveBankIntoDB(@RequestBody Bank bank) {
		// System.out.println("jwt token"+jwt);
		Bank bankEntity = bankRepository.save(bank);
		return bankEntity;
	}

	@PutMapping("/update")
	public Bank updateBank(@RequestBody Bank bank) {
		Optional<Bank> bankentity = bankRepository.findById(bank.getId());
		Bank bank1 = bankentity.get();

		if (!bank.getBankType().equals(bank1.getBankType())) {
			bank1.setBankType(bank.getBankType());
		}

		if (!bank.getBranch().equals(bank1.getBranch())) {
			bank1.setBranch(bank.getBranch());
		}

		if (!bank.getName().equals(bank1.getName())) {
			bank1.setName(bank.getName());
		}

		Bank bankEntity = bankRepository.save(bank1);
		return bankEntity;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBank(@PathVariable Integer id) {
		System.out.println("id....." + id);
		bankRepository.deleteById(id);
		return "record deleted successfully";
	}

}
