package com.devblack.xray.client;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class CepConfiguration extends FeignClientsConfiguration {

	@Bean
	public Client client(HttpClientBuilder httpClientBuilder, FeignHttpClientProperties properties) {
		
		httpClientBuilder = httpClientBuilder != null ? httpClientBuilder : HttpClientBuilder.create();
		
		final PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);
		poolingHttpClientConnectionManager.setMaxTotal(100);
		
		final RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(100)
				.setConnectTimeout(properties.getConnectionTimeout())
				.setSocketTimeout(100)
				.setRedirectsEnabled(properties.isFollowRedirects())
				.build();
		
		return new ApacheHttpClient(httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager)
				.setDefaultRequestConfig(defaultRequestConfig).build());
	}
	
}