package io.praveen.reactbootapp.config;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ProxyConfig implements RestTemplateCustomizer {

	@Override
	public void customize(RestTemplate restTemplate) {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.xxxx.com", 8080));
		requestFactory.setProxy(proxy);
		restTemplate.setRequestFactory(requestFactory);
    }

}
