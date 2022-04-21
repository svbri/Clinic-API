package dh.clinica.service.impl;

import dh.clinica.dto.DentistDTO;
import dh.clinica.dto.PatientDTO;
import dh.clinica.entity.Dentist;
import dh.clinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistServiceTest {
    @Autowired
    private DentistService dentistService;

    @Test
    void findById() throws ResourceNotFoundException {
        DentistDTO dentistDTO = new DentistDTO();
        dentistDTO.setName("Mr.");
        dentistDTO.setLastname("PeanutButter");
        DentistDTO dentistDTO1 = dentistService.create(dentistDTO);
        assertNotNull(dentistService.findById(dentistDTO1.getId()));
    }

    @Test
    void create() throws ResourceNotFoundException {
        DentistDTO dentistDTO = new DentistDTO();
        dentistDTO.setName("Mr.");
        dentistDTO.setLastname("PeanutButter");
        DentistDTO dentistDTO1 = dentistService.create(dentistDTO);
        assertNotNull(dentistService.findById(dentistDTO1.getId()));
    }

    @Test
    void deleteById() throws ResourceNotFoundException {
        DentistDTO dentistDTO = new DentistDTO();
        dentistDTO.setName("Mr.");
        dentistDTO.setLastname("PeanutButter");
        DentistDTO dentistDTO1 = dentistService.create(dentistDTO);
        assertNotNull(dentistService.findById(dentistDTO1.getId()));
        dentistService.deleteById(dentistDTO1.getId());
        assertThrows(ResourceNotFoundException.class, () -> dentistService.findById(dentistDTO1.getId()));
    }

    @Test
    void update() throws ResourceNotFoundException {
        DentistDTO dentist = new DentistDTO();
        dentist.setName("Zaira");
        dentist.setLastname("Tira");
        DentistDTO np = dentistService.create(dentist);

        np.setName("Zoe");
        dentistService.update(np, dentist.getId());

        DentistDTO p1 = dentistService.findById(np.getId());

        assertEquals(p1.getName(), "Zoe");
    }

    @Test
    void findAll() throws ResourceNotFoundException {
        DentistDTO dentist = new DentistDTO();
        dentist.setName("Zaira");
        dentist.setLastname("Tira");
        DentistDTO dentistDTO = dentistService.create(dentist);

        DentistDTO dentist2 = new DentistDTO();
        dentist.setName("Z");
        dentist.setLastname("T");
        DentistDTO dentistDTO1 = dentistService.create(dentist2);
        assertNotNull(dentistService.findAll());
        assertTrue(dentistService.findAll().size() > 1);
    }
}