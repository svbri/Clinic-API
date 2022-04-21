package dh.clinica.dto;

import dh.clinica.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PatientDTO {
    private Integer id;
    private String lastname;
    private String name;
    private String email;
    private String dni;
    private LocalDate dateAdmission;
    private AddressDTO address;
}
