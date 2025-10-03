package org.example.task7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import lombok.RequiredArgsConstructor;
import org.example.task6.dto.ProductDto;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final RestClient restClient = RestClient.create();

    public ProductDto findProductById(int id) {
        return restClient
            .get()
            .uri("http://localhost:8080/v1/products/{id}", id)
            .retrieve()
            .onStatus(HttpStatusCode::isError, this::handleError)
            .body(ProductDto.class);
    }

    private void handleError(HttpRequest httpRequest, ClientHttpResponse response) {
        System.out.println("Ошибка при обращении по адресу: " + httpRequest.getURI() + " " + getBody(response));
    }

    public static String getBody(ClientHttpResponse response) {
        try {
            return StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }
}
