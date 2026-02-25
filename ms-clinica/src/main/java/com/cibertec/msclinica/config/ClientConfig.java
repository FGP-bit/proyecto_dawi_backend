package com.cibertec.msclinica.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.cibertec.msclinica.client.AdminClient;

@Configuration
public class ClientConfig {

    @Bean
    public AdminClient adminClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8001")
                .build();
        
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        
        return factory.createClient(AdminClient.class);
    }
}