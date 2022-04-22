package dh.clinica.controller;

import dh.clinica.exceptions.ResourceNotFoundException;
import dh.clinica.service.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws ResourceNotFoundException {
        addressService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
    }
}
