package com.example.isa.util;

import com.example.isa.model.*;
import com.example.isa.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class UserCreator {
    private final RoleRepository roleRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final BloodDonorRepository bloodDonorRepository;
    private final AdminRepository adminRepository;
    private final BloodBankRepository bloodBankRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCreator(RoleRepository roleRepository, MedicalStaffRepository medicalStaffRepository, BloodDonorRepository bloodDonorRepository, AdminRepository adminRepository, BloodBankRepository bloodBankRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.medicalStaffRepository = medicalStaffRepository;
        this.bloodDonorRepository = bloodDonorRepository;
        this.adminRepository = adminRepository;
        this.bloodBankRepository = bloodBankRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void create() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleDonor = new Role("ROLE_DONOR");
        Role roleStaff = new Role("ROLE_STAFF");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleDonor);
        roleRepository.save(roleStaff);
        Admin admin = new Admin("0000000000000", "isaproject202223@gmail.com", passwordEncoder.encode("ISA202223"),passwordEncoder.encode("ISA202223"), "Admin", "Admin", "0000000000", Gender.FEMALE, true, roleAdmin);
        adminRepository.save(admin);
        BloodDonor bloodDonor = new BloodDonor("2801001123456", "blooddonor@gmail.com", passwordEncoder.encode("BLOODDONOR"), "Jane", "Doe", "0681234567", Gender.FEMALE, true, "Student", new Address("Ulica 15", "Novi Sad", "Srbija"), "FTN", roleDonor);
        bloodDonor.setId(UUID.fromString("16e4a8c2-3e86-4e93-825f-24e36cb29645"));
        bloodDonorRepository.save(bloodDonor);
        //'Belgrade', 'Srbija', 'Ljube Jovanovića 12', 4.11, 'Najveća banka krvi u Beogradu',
        //        'Institut za transfuziju krvi', '24:00:00', '00:00:00');
        BloodBank bank = new BloodBank("banka puno krvi",new Address("Vojvode Misica 10","Kragujevac","Srbija"),new Interval(new Date(),new Date()),"Vampiri ovde povremeno borave",3.5);
        bank.setId(UUID.fromString("16e4a8c2-3e86-4e93-825f-24e36cb29655"));
        bloodBankRepository.save(bank);
        MedicalStaff medicalStaff = new MedicalStaff("0101010101010", "staff@gmail.com", passwordEncoder.encode("STAFF"), "Good", "Doctor", "0101010101", Gender.FEMALE, true, roleStaff, bank );
        medicalStaffRepository.save(medicalStaff);
    }
}
