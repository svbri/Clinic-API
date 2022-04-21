package dh.clinica.controller;

import dh.clinica.dto.DentistDTO;

import dh.clinica.exceptions.ResourceNotFoundException;
import dh.clinica.service.impl.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("dentists")
public class DentistController {
    @Autowired
    private DentistService dentistService;

    @GetMapping("/{id}")
    public ResponseEntity<DentistDTO> findById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        DentistDTO dentistDTO = dentistService.findById(id);
        return new ResponseEntity<>(dentistDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DentistDTO> create(@RequestBody DentistDTO dentistDTO) throws ResourceNotFoundException {
        DentistDTO newDentistDTO = dentistService.create(dentistDTO);
        return new ResponseEntity<>(newDentistDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        dentistService.deleteById(id);
        return new ResponseEntity<>("Odontologo eliminado", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DentistDTO> update(@RequestBody DentistDTO dentistDTO, @PathVariable("id") Integer id) throws ServerException, ResourceNotFoundException {
        if(dentistService.findById(dentistDTO.getId()) == null) {
            throw new ServerException("No se encontró el odontologo");
        } else {
            DentistDTO updateDentist = dentistService.update(dentistDTO, id);
            return new ResponseEntity<>(updateDentist, HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<DentistDTO>> listDentists() {
        List<DentistDTO> dentistDTOList = dentistService.findAll();
        return new ResponseEntity<>(dentistDTOList, HttpStatus.OK);
    }
}
