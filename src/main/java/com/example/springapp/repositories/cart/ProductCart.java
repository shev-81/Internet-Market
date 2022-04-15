package com.example.springapp.repositories.cart;

import com.example.springapp.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCart {

    private List<ProductDto> productDtoList;

    @PostConstruct
    public void init(){
        productDtoList = new ArrayList<>();
    }

    public void add (ProductDto productDto){
        productDtoList.add(productDto);
    }

    public List<ProductDto> removeProductById(Long id){
        for(int i =0; i<productDtoList.size(); i++){
            ProductDto p = productDtoList.get(i);
            if(p.getId().equals(id)){
                productDtoList.remove(i);
            }
        }
        return productDtoList;
    }
}


























