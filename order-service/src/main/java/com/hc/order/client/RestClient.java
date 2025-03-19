package com.hc.order.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public abstract class RestClient {

    private final RestTemplate restTemplate;

    protected abstract String getServiceUrl();

    protected <T> T sendRequest(String endpoint, HttpMethod method, ParameterizedTypeReference<T> responseType, HttpHeaders headers) {
        String url = getServiceUrl() + endpoint;

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, method, entity, responseType);

        return response.getBody();
    }
}

