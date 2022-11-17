package com;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bank {
	private Integer id;
	private String name;
	private String branch;
	private String bankType;
}
