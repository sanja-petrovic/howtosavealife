package com.example.isa.service.implementation;

import com.example.isa.model.*;
import com.example.isa.repository.BloodDonorRepository;
import com.example.isa.service.interfaces.BloodDonorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodDonorServiceImpl implements BloodDonorService {
    private final BloodDonorRepository repository;

    @Autowired
    public BloodDonorServiceImpl(BloodDonorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BloodDonor> getAll() {
        return repository.findAll();
    }
	@Override
	public BloodDonor getByPersonalId(String personalId) throws Exception {
		return repository.findByPersonalId(personalId).orElseThrow(() -> new Exception(String.format("No user found with personalId '%s'.", personalId)));
	}

	@Override
	public BloodDonor update(BloodDonor bloodDonor){
		BloodDonor fromRepo = repository.findByPersonalId(bloodDonor.getPersonalId()).orElse(null);
		if(fromRepo == null) {
			return null;
		}
		BloodDonor swapped = swapValues(fromRepo, bloodDonor);
		
		return repository.save(swapped);
	}
	private BloodDonor swapValues(BloodDonor fromRepo, BloodDonor bloodDonor) {
		fromRepo.setPersonalId(bloodDonor.getPersonalId());
    	fromRepo.setFirstName(bloodDonor.getFirstName());
    	fromRepo.setLastName(bloodDonor.getLastName());
    	fromRepo.setEmail(bloodDonor.getEmail());
    	fromRepo.setPassword(bloodDonor.getPassword());
    	fromRepo.setPhoneNumber(bloodDonor.getPhoneNumber());
    	fromRepo.setGender(bloodDonor.getGender());
    	fromRepo.setOccupation(bloodDonor.getOccupation());
    	fromRepo.setAddress(bloodDonor.getAddress());
    	fromRepo.setInstitution(bloodDonor.getInstitution());
    	return fromRepo;
	}
    @Override
    public BloodDonor getByEmail(String email) {
        return repository.findAllByEmail(email).orElse(null);
    }
}