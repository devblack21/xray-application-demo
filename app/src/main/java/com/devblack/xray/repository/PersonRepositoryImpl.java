package com.devblack.xray.repository;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.devblack.xray.core.PersonRepository;
import com.devblack.xray.domain.Person;
import com.devblack.xray.infra.XRayTrace;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
@XRayEnabled
public class PersonRepositoryImpl implements PersonRepository {
	
	@Override
	public void save(final Person person) {
		XRayTrace.trace("database", () -> {
			Logger.getLogger("database").info("salvo com sucesso!");
		});
	}
	
}