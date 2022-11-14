package com.example.isa.service.implementation;

import com.example.isa.model.BloodBank;
import com.example.isa.repository.BloodBankRepository;
import com.example.isa.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodBankServiceImpl implements BloodBankService {
    private final BloodBankRepository repository;

    @Autowired
    public BloodBankServiceImpl(BloodBankRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BloodBank> getAll() {
        return repository.findAll();
    }

    @Override
    public List<BloodBank> search(Sort sort, List<String> searchCriteria, String filterGrade) {
    	String titleString = searchCriteria.get(0);
    	String cityString = searchCriteria.get(1);
        if (titleString.equals("") && cityString.equals("")) {
        	return repository.findAllWithFilter(Double.parseDouble(filterGrade),sort);
        }
        else if(titleString.equals("")){
        	return repository.findByAddressCityLike("%"+cityString+"%",Double.parseDouble(filterGrade),sort);
        }
        else if(cityString.equals("")){
        	return repository.findByTitleLike("%"+titleString+"%",Double.parseDouble(filterGrade), sort);
        }
        else {
        	return repository.findByTitleLikeAndAddressCityLike("%"+titleString+"%", "%"+cityString+"%",Double.parseDouble(filterGrade), sort);
        }
    }
}
