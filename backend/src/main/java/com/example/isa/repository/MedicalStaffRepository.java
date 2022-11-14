package com.example.isa.repository;

import com.example.isa.model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, UUID> {
}