package com.devblack.xray.client;

import com.amazonaws.xray.proxies.apache.http.HttpClientBuilder;
import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

@Configuration(proxyBeanMethods = false)
public class CepConfiguration extends FeignClientsConfiguration {

	@Bean
	public Client client(FeignHttpClientProperties httpClientProperties) {
		
		final PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(2);
		poolingHttpClientConnectionManager.setMaxTotal(5);
		
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setConnectTimeout(httpClientProperties.getConnectionTimeout())
				.setRedirectsEnabled(httpClientProperties.isFollowRedirects())
				.build();
		
		return new ApacheHttpClient(HttpClientBuilder.create()
				.setConnectionManager(poolingHttpClientConnectionManager)
				.setDefaultRequestConfig(defaultRequestConfig).build());
	}
	
}