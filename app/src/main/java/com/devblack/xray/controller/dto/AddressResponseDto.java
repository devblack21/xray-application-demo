package com.devblack.xray.controller.dto;

import com.devblack.xray.domain.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressResponseDto {
	
	@JsonProperty("code")
	private String code;
	@JsonProperty("state")
	private String state;
	@JsonProperty("city")
	private String city;
	@JsonProperty("district")
	private String district;
	@JsonProperty("address")
	private String address;
	
	public static AddressResponseDto transform(final Address address) {
		return AddressResponseDto.builder()
				.address(address.getAddress())
				.city(address.getCity())
				.code(address.getCode())
				.district(address.getDistrict())
				.state(address.getState())
				.build();
		
	}
}
