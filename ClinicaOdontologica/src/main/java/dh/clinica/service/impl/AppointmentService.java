package dh.clinica.service.impl;

import dh.clinica.dto.AppointmentDTO;
import dh.clinica.dto.DentistDTO;
import dh.clinica.dto.PatientDTO;
import dh.clinica.entity.Appointment;
import dh.clinica.exceptions.ResourceNotFoundException;
import dh.clinica.repository.IAppointmentRepository;
import dh.clinica.service.IAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private DentistService dentistService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AppointmentDTO findById(Integer id) throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Turno", "id", id));
        return mapDTO(appointment);
    }

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) throws ResourceNotFoundException {
        PatientDTO patientDTO = patientService.findById(appointmentDTO.getPatient().getId());
        DentistDTO dentistDTO = dentistService.findById(appointmentDTO.getDentist().getId());

        Appointment appointment = mapEntity(appointmentDTO);

        appointment.setDate(appointmentDTO.getDate());
        appointment.setPatient(patientService.mapEntity(patientDTO));
        appointment.setDentist(dentistService.mapEntity(dentistDTO));

        Appointment newAppointmentSave = appointmentRepository.save(appointment);
        return mapDTO(newAppointmentSave);
    }

    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
        Appointment address = appointmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Turno","id",id));
        appointmentRepository.delete(address);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO, Integer id) {
        return null;
    }

    @Override
    public List<AppointmentDTO> findAll() {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<AppointmentDTO> appointmentDTOList = appointmentList.stream().map(appointment -> mapDTO(appointment)).collect(Collectors.toList());
        return appointmentDTOList;
    }


    //Mapper
    private AppointmentDTO mapDTO(Appointment appointment) {
        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        return appointmentDTO;
    }

    private Appointment mapEntity(AppointmentDTO appointmentDTO) {
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        return appointment;
    }
}
