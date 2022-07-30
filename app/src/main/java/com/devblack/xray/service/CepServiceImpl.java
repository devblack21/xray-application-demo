package com.devblack.xray.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.devblack.xray.client.ApiCepClient;
import com.devblack.xray.client.dto.CepResponse;
import com.devblack.xray.core.CepService;
import com.devblack.xray.domain.Address;
import com.devblack.xray.infra.XRayTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CepServiceImpl implements CepService {
	
	@Autowired
	private ApiCepClient cepClient;
	
	@Override
	public Address returnInfoCep(final String cep) {
		final AtomicReference<CepResponse> cepResponse = new AtomicReference<>();
		XRayTrace.trace("api-cep", () -> cepResponse.set(this.cepClient.describeCep(Integer.parseInt(cep)).getBody()));
		return EnderecoMapper.mapFrom(cepResponse.get());
	}
	
	private static class EnderecoMapper {
		
		private static Address mapFrom(final CepResponse source) {
			if (Objects.isNull(source)) {
				return null;
			}
			
			return Address.builder()
					.city(source.getCity())
					.code(source.getCode())
					.state(source.getState())
					.district(source.getDistrict())
					.address(source.getAddress())
					.build();
		}
		
	}
	
}
