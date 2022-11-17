package com;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bankdb")
public class Bank {

	@Id
	private Integer id;
	private String name;
	private String branch;
	private String bankType;
}
