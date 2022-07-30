package com.devblack.xray.controller.dto;

import com.devblack.xray.domain.Address;
import com.devblack.xray.domain.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PersonRequestDto {
	
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("age")
	private int age;
	private String cep;
	
	@JsonProperty("cep")
	public void setCep(String cep) {
		this.cep = cep.trim().replace("-", "");
	}
	
	public Person transform() {
		return Person.builder()
				.firstName(this.firstName)
				.lastName(this.lastName)
				.age(this.age)
				.address(Address.builder()
						.code(this.cep)
						.build())
				.build();
	}
	
}