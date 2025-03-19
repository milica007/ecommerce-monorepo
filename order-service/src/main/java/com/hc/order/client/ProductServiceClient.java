package com.hc.order.client;

import com.hc.order.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductServiceClient extends RestClient {

    private final String productServiceUrl;

    public ProductServiceClient(RestTemplate restTemplate, @Value("${product-service.url}") String productServiceUrl) {
        super(restTemplate);
        this.productServiceUrl = productServiceUrl;
    }

    @Override
    protected String getServiceUrl() {
        return productServiceUrl;
    }

    public List<ProductDTO> getProductsByIds(List<Long> productIds, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        String endpoint = "?ids=" + productIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        return sendRequest(endpoint, HttpMethod.GET, new ParameterizedTypeReference<>() {}, headers);
    }
}

