package com.devblack.xray.client.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CepResponse {

	@JsonProperty("status")
	private int status;
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
	@JsonProperty("statusText")
	private String statusText;
	
}