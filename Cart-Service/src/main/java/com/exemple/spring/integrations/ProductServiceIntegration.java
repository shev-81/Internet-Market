package com.exemple.spring.integrations;

import com.exemple.spring.core.ProductDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Data
@Component
public class ProductServiceIntegration {

//    private final RestTemplate restTemplate;
    @Autowired
    @Qualifier("coreServiceWebClient")
    private WebClient coreServiceWebClient;

    @Value("${integrations.core-service.url}")
    private String productServiceUrl;

    public Optional <ProductDto> findById(Long id){
//        ProductDto productDto = restTemplate.getForObject(productServiceUrl + "/api/v1/products/" + id, ProductDto.class);
        ProductDto productDto = coreServiceWebClient.get()
                .uri(productServiceUrl + "/api/v1/products/" + id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
        return Optional.of(productDto);
    }
}
