package com.devblack.xray.infra;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.plugins.ECSPlugin;
import com.amazonaws.xray.strategy.sampling.CentralizedSamplingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.net.URL;

@Configuration
public class XRayConfig {
	
	static {
		final AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard().withPlugin(new ECSPlugin());
		
		final URL ruleFile = XRayConfig.class.getResource("/sampling-rules.json");
		builder.withSamplingStrategy(new CentralizedSamplingStrategy(ruleFile));
		
		AWSXRay.setGlobalRecorder(builder.build());
	}
	
	@Bean
	public Filter TracingFilter() {
		return new AWSXRayServletFilter("xray-application-demo");
	}

}