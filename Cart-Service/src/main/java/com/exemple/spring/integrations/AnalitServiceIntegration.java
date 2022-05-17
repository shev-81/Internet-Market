package com.exemple.spring.integrations;

import com.exemple.spring.core.ProductDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Data
@Component
@RequiredArgsConstructor
public class AnalitServiceIntegration {

    private final WebClient analitServiceWebClient;

    public void registration(ProductDto productDto){
//        analitServiceWebClient.get()
//                .uri("/api/v1/analit/reg")
//                .header("nameProduct", productDto.getName())
//                .retrieve()
//                .toBodilessEntity()
//                .block();

        analitServiceWebClient.post()
                .uri("/api/v1/analit/reg")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(productDto))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
