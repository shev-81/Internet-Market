package com.example.spring.analityc.services;

import com.example.spring.analityc.entities.Statistic;
import com.example.spring.analityc.repositories.AnalitRepository;
import com.exemple.spring.analityc.StatisticDto;
import com.exemple.spring.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalitService {

    private final AnalitRepository analitRepository;

    public List<StatisticDto> getAll(){
        return analitRepository.getAll().stream().map(s-> (new StatisticDto(s.getNameProducts(), s.getCountVisits()))).collect(Collectors.toList());
    }

    @Transactional
    public void register(ProductDto product){
        Statistic statistic = analitRepository.findProductByName(product.getName()).orElse(null);
        if(statistic == null){
            statistic = new Statistic(product.getName(), 1);
        }else{
            statistic.setCountVisits(statistic.getCountVisits()+1);
        }
        analitRepository.save(statistic);
    }
}
