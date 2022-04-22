package dh.clinica.service.impl;

import dh.clinica.dto.AddressDTO;
import dh.clinica.dto.AppointmentDTO;
import dh.clinica.dto.DentistDTO;
import dh.clinica.dto.PatientDTO;
import dh.clinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceTest {
    @Autowired
    private PatientService patientService;
    PatientDTO patientDTO;

    @Autowired
    private DentistService dentistService;
    DentistDTO dentistDTO;

    @Autowired
    private AppointmentService appointmentService;

    @BeforeEach
    public void creatingPAndD() throws ResourceNotFoundException {
        dentistDTO = new DentistDTO();
        dentistDTO.setLastname("Elgee");
        dentistDTO.setName("Bee-T");
        dentistDTO.setLicense("1234");


        patientDTO = new PatientDTO();
        patientDTO.setName("Mr.");
        patientDTO.setLastname("PeanutButter");
        patientDTO.setEmail("pb@email.com");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("StreetName");
        addressDTO.setNumber(22);
        addressDTO.setCity("L.A.");
        addressDTO.setState("CA");
    }

    @Test
    void create() throws ResourceNotFoundException {
        DentistDTO dentistDTO = dentistService.create(this.dentistDTO);
        PatientDTO patientDTO = patientService.create(this.patientDTO);

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(patientDTO);
        appointmentDTO.setDentist(dentistDTO);
        appointmentDTO.setDate(appointmentDTO.getDate());
        AppointmentDTO appointmentDTO1 = appointmentService.create(appointmentDTO);

        //Verifica que un objeto no sea null. Si lo es, tira un mensaje AssertionError
        assertNotNull(appointmentService.findById(appointmentDTO1.getId()));
    }

    @Test
    void findAll() {
    }
}