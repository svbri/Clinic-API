package dh.clinica.controller;

import dh.clinica.dto.PatientDTO;

import dh.clinica.exceptions.ResourceNotFoundException;
import dh.clinica.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {

    //Me traigo todos los métodos
    @Autowired
    private PatientService patientService;

    //Consulto datos
    @GetMapping("/{id}")
    //En el parámetro de @PathVariable le pongo el mismo nombre que puse en @GetMapping
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        PatientDTO patientDTO = patientService.findById(id);
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patientDTO) throws ResourceNotFoundException {
        PatientDTO newPatientDTO = patientService.create(patientDTO);
        return new ResponseEntity<>(newPatientDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        patientService.deleteById(id);
        return new ResponseEntity<>("Paciente eliminado", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO patientDTO, @PathVariable("id") Integer id) throws ResourceNotFoundException {
        PatientDTO updatePatient = patientService.update(patientDTO, id);
        return new ResponseEntity<>(updatePatient, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PatientDTO>> listPatients() {
        List<PatientDTO> patientDTOList = patientService.findAll();
        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }
}
