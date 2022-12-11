package com.example.isa.service.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Appointment;
import com.example.isa.repository.AppointmentRepository;
import com.example.isa.service.interfaces.AppointmentService;
import com.example.isa.util.DateConvertor;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	private final AppointmentRepository repository;
	
	@Autowired
	public AppointmentServiceImpl(AppointmentRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Appointment> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Appointment> getByBloodBank(UUID bloodBankId) {
		return repository.getByBloodBank(bloodBankId);
	}

	@Override
	public List<Appointment> getByPatient(UUID patientId) {
		return repository.getByPatient(patientId);
	}

	@Override
	public Appointment create(Appointment appointment) {
		List<Appointment> listScheduled = repository.getByBloodBankForDate(appointment.getBloodBank().getId(), DateConvertor.convert(appointment.getDateTime()).toLocalDate());
		for(Appointment scheduled : listScheduled) {
			if(scheduled.hasDateTimeOverlap(DateConvertor.convert(appointment.getDateTime()), appointment.getDuration())) {
				return null;
			}
		}
		return repository.save(appointment);
	}

	@Override
	public Appointment update(Appointment appointment) {
		return repository.save(appointment);
	}

}
