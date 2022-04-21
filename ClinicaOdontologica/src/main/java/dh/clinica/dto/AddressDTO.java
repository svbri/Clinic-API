package dh.clinica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AddressDTO {
    private Integer id;
    private String street;
    private int number;
    private String city;
    private String state;
}
