package com.devblack.xray.client;

import com.devblack.xray.client.dto.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "apiCep", url = "${feign.client.cep.url}", configuration = CepConfiguration.class)
public interface ApiCepClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/cep/{codigo}.json", consumes = "application/json")
	ResponseEntity<CepResponse> describeCep(@PathVariable("codigo") Integer cep);
	
}