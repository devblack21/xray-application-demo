package com.devblack.xray.controller.dto;

import com.devblack.xray.domain.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponseDto {
	
	private String firstName;
	private String lastName;
	@JsonProperty("age")
	private int age;
	@JsonProperty("address")
	private AddressResponseDto address;
	
	@JsonProperty("firstName")
	public String getFirstName() {
		if (Objects.isNull(this.firstName)) {
			return null;
		}
		final String firstLether = firstName.substring(0,1).toUpperCase();
		return firstLether.concat(firstName.substring(1).toLowerCase());
	}
	
	@JsonProperty("lastName")
	public String getLastName() {
		if (Objects.isNull(this.lastName)) {
			return null;
		}
		
		final String firstLether = lastName.substring(0,1).toUpperCase();
		return firstLether.concat(lastName.substring(1).toLowerCase());
	}
	
	public static PersonResponseDto transform(final Person person) {
		return PersonResponseDto.builder()
				.firstName(person.getFirstName())
				.lastName(person.getLastName())
				.age(person.getAge())
				.address(AddressResponseDto.transform(person.getAddress()))
				.build();
	}
	
}