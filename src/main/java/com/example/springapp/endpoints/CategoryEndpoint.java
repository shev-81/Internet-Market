package com.example.springapp.endpoints;

import com.example.springapp.entities.Category;
import com.example.springapp.services.CategoryService;
import com.example.springapp.soap.categories.CategorySoap;
import com.example.springapp.soap.categories.GetCategoryByTitleRequest;
import com.example.springapp.soap.categories.GetCategoryByTitleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {
    private static final String NAMESPACE_URI = "http://www.shev.com/spring/ws/categories";
    private final CategoryService categoryService;

    /*
        Пример запроса: POST http://localhost:8189/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.shev.com/spring/ws/categories">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getCategoryByTitleRequest>
                    <f:name>Fruits</f:name>
                </f:getCategoryByTitleRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByTitleRequest")
    @ResponsePayload
    @Transactional
    public GetCategoryByTitleResponse getCategoryByTitle(@RequestPayload GetCategoryByTitleRequest request) {
        GetCategoryByTitleResponse response = new GetCategoryByTitleResponse();
        CategorySoap categorySoap = new CategorySoap();
        Category category = categoryService.getCategoryByName(request.getName());
        categorySoap.setName(category.getName());
        response.setCategory(categorySoap);
        return response;
    }
}
