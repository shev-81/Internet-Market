package com.example.springapp.repositories.cart;

import com.example.springapp.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
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

    public void delProdictById(Long id){
        Iterator <ProductDto> iter = productDtoList.iterator();
        while(iter.hasNext()){
            ProductDto p = iter.next();
            if(p.getId() == id){
                iter.remove();
                break;
            }
        }
    }
}
