package dh.clinica.dto;

import dh.clinica.entity.Dentist;
import dh.clinica.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AppointmentDTO {
    private Integer id;
    private String date;
    private String time;
    private DentistDTO dentist;
    private PatientDTO patient;
}
