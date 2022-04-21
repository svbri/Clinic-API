package dh.clinica.controller;

import dh.clinica.dto.AppointmentDTO;
import dh.clinica.exceptions.ResourceNotFoundException;
import dh.clinica.service.impl.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointmentDTO) throws ResourceNotFoundException {
        AppointmentDTO newAppointmentDTO = appointmentService.create(appointmentDTO);
        return new ResponseEntity<>(newAppointmentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws ResourceNotFoundException {
        appointmentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
    }

    @GetMapping("/list")
    public ResponseEntity<List<AppointmentDTO>> listAppointments() {
        List<AppointmentDTO> appointmentDTOList = appointmentService.findAll();
        return new ResponseEntity<>(appointmentDTOList, HttpStatus.OK);
    }
}
