package com.devblack.xray.processor;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.devblack.xray.core.CepService;
import com.devblack.xray.core.NotFoundException;
import com.devblack.xray.core.PersonProcessor;
import com.devblack.xray.core.PersonRepository;
import com.devblack.xray.domain.Address;
import com.devblack.xray.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@XRayEnabled
public class PersonProcessorImpl implements PersonProcessor {
	
	@Autowired
	private CepService cepService;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person savePerson(final Person person) {
		
		final Address address = cepService.returnInfoCep(person.getAddress().getCode());
		
		if (Objects.isNull(address.getCode())) {
			throw new NotFoundException("CEP inválido.");
		}
		
		person.setAddress(address);
		
		personRepository.save(person);
		
		return person;
	}
	
}