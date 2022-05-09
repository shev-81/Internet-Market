package com.example.springapp.endpoints;

import com.example.springapp.entities.Product;
import com.example.springapp.services.ServicesProducts;
import com.example.springapp.soap.products.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class ProductsEndpoint {
    private static final String NAMESPACE_URI = "http://www.shev.com/spring/ws/products";
    private final ServicesProducts servicesProducts;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByNameRequest")
    @ResponsePayload
    public GetProductByNameResponse getProductByName(@RequestPayload GetProductByNameRequest request) {
        GetProductByNameResponse response = new GetProductByNameResponse();
        Product product = servicesProducts.getByName(request.getName());
        ProductSoap productSoap = new ProductSoap();
        productSoap.setId(product.getId());
        productSoap.setName(product.getName());
        productSoap.setPrice(product.getPrice());
        response.setProduct(productSoap);
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/app/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.shev.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllStudents(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<Product> listProducts = servicesProducts.findAll();
        for(Product product: listProducts){
            ProductSoap productSoap = new ProductSoap();
            productSoap.setId(product.getId());
            productSoap.setName(product.getName());
            productSoap.setPrice(product.getPrice());
            response.getProducts().add(productSoap);
        }
        return response;
    }
}
