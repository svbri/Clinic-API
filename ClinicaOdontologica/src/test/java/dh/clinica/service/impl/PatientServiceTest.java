package dh.clinica.service.impl;

import dh.clinica.dto.PatientDTO;
import dh.clinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Test
    void findById() throws ResourceNotFoundException {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName("Mr.");
        patientDTO.setLastname("PeanutButter");
        patientDTO.setEmail("pb@email.com");
        PatientDTO patientDTO1 = patientService.create(patientDTO);
        assertNotNull(patientService.findById(patientDTO1.getId()));
    }

    @Test
    void create() throws ResourceNotFoundException {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName("Mr.");
        patientDTO.setLastname("PeanutButter");
        patientDTO.setEmail("pb@email.com");
        PatientDTO patientDTO1 = patientService.create(patientDTO);
        assertNotNull(patientService.findById(patientDTO1.getId()));
    }

    @Test
    void deleteById() throws ResourceNotFoundException {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName("Mr.");
        patientDTO.setLastname("PeanutButter");
        patientDTO.setEmail("pb@email.com");
        PatientDTO patientDTO1 = patientService.create(patientDTO);
        assertNotNull(patientService.findById(patientDTO1.getId()));
        patientService.deleteById(patientDTO1.getId());
        assertThrows(ResourceNotFoundException.class, () -> patientService.findById(patientDTO1.getId()));
    }

    @Test
    void update() throws ResourceNotFoundException {
        //Creo un paciente
        PatientDTO patient = new PatientDTO();
        patient.setName("Zaira");
        patient.setLastname("Tira");
        patient.setEmail("zaira@algo");

        //Lo guardo
        PatientDTO np = patientService.create(patient);

        //Le cambio el nombre y lo actualizo
        np.setName("Zoe");
        patientService.update(np, patient.getId());

        //Me lo traigo
        PatientDTO p1 = patientService.findById(np.getId());

        //Lo chequeo
        assertEquals(p1.getName(), "Zoe");
    }

    @Test
    void findAll() throws ResourceNotFoundException {
        PatientDTO patient = new PatientDTO();
        patient.setName("Zaira");
        patient.setLastname("Tira");
        PatientDTO patientDTO = patientService.create(patient);

        PatientDTO patient2 = new PatientDTO();
        patient.setName("Z");
        patient.setLastname("T");
        PatientDTO patientDTO2 = patientService.create(patient2);
        assertNotNull(patientService.findAll());
        assertTrue(patientService.findAll().size() > 1);
    }
}