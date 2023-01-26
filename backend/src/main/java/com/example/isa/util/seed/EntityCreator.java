package com.example.isa.util.seed;

import com.example.isa.model.*;
import com.example.isa.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Component
public class EntityCreator {
    private final RoleRepository roleRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final BloodDonorRepository bloodDonorRepository;
    private final AdminRepository adminRepository;
    private final BloodBankRepository bloodBankRepository;
    private final PasswordEncoder passwordEncoder;

    public EntityCreator(RoleRepository roleRepository, MedicalStaffRepository medicalStaffRepository, BloodDonorRepository bloodDonorRepository, AdminRepository adminRepository, BloodBankRepository bloodBankRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.medicalStaffRepository = medicalStaffRepository;
        this.bloodDonorRepository = bloodDonorRepository;
        this.adminRepository = adminRepository;
        this.bloodBankRepository = bloodBankRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void create() {
        if(roleRepository.findAll().isEmpty()) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleDonor = new Role("ROLE_DONOR");
            Role roleStaff = new Role("ROLE_STAFF");
            roleRepository.save(roleAdmin);
            roleRepository.save(roleDonor);
            roleRepository.save(roleStaff);
            Admin admin = new Admin("0000000000000", "isaproject202223@gmail.com", passwordEncoder.encode("ISA202223"),passwordEncoder.encode("ISA202223"), "Admin", "Admin", "0000000000", Gender.FEMALE, true, roleAdmin);
            adminRepository.save(admin);
            BloodDonor bloodDonor = new BloodDonor("2801001123456", "blooddonor@gmail.com", passwordEncoder.encode("BLOODDONOR"), "Jane", "Doe", "0681234567", Gender.FEMALE, true, "Student", new Address("Ulica 15", "Novi Sad", "Srbija"), "FTN", roleDonor, 0);
            bloodDonor.setId(UUID.fromString("16e4a8c2-3e86-4e93-825f-24e36cb29645"));
            bloodDonorRepository.save(bloodDonor);
            BloodDonor bloodDonor2 = new BloodDonor("2801001123475", "blooddonor2@gmail.com", passwordEncoder.encode("BLOODDONOR"), "John", "Doe", "0681234467", Gender.MALE, true, "Student", new Address("Ulica 15", "Novi Sad", "Srbija"), "FTN", roleDonor, 0);
            bloodDonor2.setId(UUID.fromString("26e4a8c2-3e86-4e93-825f-24e36cb29645"));
            bloodDonorRepository.save(bloodDonor2);

            BloodBank bloodBank = new BloodBank("Prva prava banka krvi", new Address("Daniciceva 15", "Novi Sad", "Srbija"), "Najopremljenija banka krvi ovih prostora", new Interval(new Date(12345), new Date(12345)), 4.8, 45.92, 19.23);
            bloodBankRepository.save(bloodBank);
            MedicalStaff medicalStaff1 = new MedicalStaff("1101010101010", "staff1@gmail.com", passwordEncoder.encode("STAFF1"), "Good", "Doctor", "7101010101", Gender.FEMALE, true, roleStaff, bloodBank);
            medicalStaffRepository.save(medicalStaff1);
            BloodBank bank = new BloodBank("banka puno krvi",new Address("Vojvode Misica 10","Kragujevac","Srbija"),"Vampiri ovde povremeno borave",new Interval(new Date(),new Date()),3.5, 45.97, 20.23);
            bank.setId(UUID.fromString("16e4a8c2-3e86-4e93-825f-24e36cb29655"));
            bloodBankRepository.save(bank);
            MedicalStaff medicalStaff2 = new MedicalStaff("0101010101010", "staff@gmail.com", passwordEncoder.encode("STAFF"), "Good", "Doctor", "0101010101", Gender.FEMALE, true, roleStaff, bank );
            medicalStaffRepository.save(medicalStaff2);
        }

    }
}
