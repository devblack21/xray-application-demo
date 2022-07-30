package com.devblack.xray.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Address {
	
	private String code;
	private String state;
	private String city;
	private String district;
	private String address;
	
}