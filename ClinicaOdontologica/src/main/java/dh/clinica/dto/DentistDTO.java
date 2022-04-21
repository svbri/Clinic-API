package dh.clinica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class DentistDTO {
    private Integer id;
    private String lastname;
    private String name;
    private String license;
}
