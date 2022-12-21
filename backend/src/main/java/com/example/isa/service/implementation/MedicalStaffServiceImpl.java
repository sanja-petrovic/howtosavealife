package com.example.isa.service.implementation;


import com.example.isa.model.MedicalStaff;
import com.example.isa.model.User;
import com.example.isa.repository.BloodBankRepository;
import com.example.isa.repository.MedicalStaffRepository;
import com.example.isa.service.interfaces.BloodBankService;
import com.example.isa.service.interfaces.MedicalStaffService;
import java.util.List;
import com.example.isa.model.BloodBank;
import com.example.isa.model.MedicalStaff;
import com.example.isa.repository.MedicalStaffRepository;
import com.example.isa.service.interfaces.MedicalStaffService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicalStaffServiceImpl implements MedicalStaffService {
    private  final MedicalStaffRepository repository;

    public MedicalStaffServiceImpl(MedicalStaffRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean register(MedicalStaff medicalStaff) {
         repository.save(medicalStaff);
         return true;
    }

    public List<MedicalStaff> getAll() {
        return repository.findAll();
    }

    @Override
    public MedicalStaff getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public MedicalStaff updateMedicalStaff(MedicalStaff medicalStaff) {
        if (medicalStaff.getPassword() == null || medicalStaff.getPassword().trim().isEmpty()) {
            //TODO
        }
        return repository.save(medicalStaff);
    }

    @Override
    public BloodBank getBloodBank(MedicalStaff medicalStaff) {
        return medicalStaff.getBloodBank();
    }

    @Override
    public Optional<MedicalStaff> findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
