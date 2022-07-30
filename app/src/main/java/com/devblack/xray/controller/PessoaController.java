package com.devblack.xray.controller;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.devblack.xray.controller.dto.PersonRequestDto;
import com.devblack.xray.controller.dto.PersonResponseDto;
import com.devblack.xray.core.PersonProcessor;
import com.devblack.xray.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@XRayEnabled
@RestController
@RequestMapping("person")
public class PessoaController {

	@Autowired
	private PersonProcessor personProcessor;
	
	@PostMapping("/save")
	public ResponseEntity<?> getEndereco(@RequestBody PersonRequestDto personDto) {
		final Person personSaved = personProcessor.savePerson(personDto.transform());
		return ResponseEntity.ok(PersonResponseDto.transform(personSaved));
	}
 
}