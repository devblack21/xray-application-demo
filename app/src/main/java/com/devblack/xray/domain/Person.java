package com.devblack.xray.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class Person {
	
	private String firstName;
	private String lastName;
	private int age;
	@Setter
	private Address address;
	
}