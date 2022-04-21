package dh.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dh.clinica.service.impl.AddressService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

//Aclaro que es una entidad
@Entity
//Si no aclaro el nombre de la tabla, se utiliza el nombre de la entity
//Va en minúsculas
@Table(name = "patients")
//Creo getters y setters con lombok
@Getter @Setter

//Creo un constructor vacío y un constructor con todos los parámetros
@AllArgsConstructor
@NoArgsConstructor

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastname;
    private String name;
    private String email;
    private String dni;
    private LocalDate dateAdmission;

    //Relación entre las tablas Address y Patient. Le paso una Foreign Key a la tabla Patient
    //Fetch Lazy decide que solo trae los datos de Address cuando se lo llama
    //Cascade permite que las acciones que se aplican a la tabla Patient se apliquen a la tabla Address
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<Appointment> appointments;


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", lastName='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", dateAdmission=" + dateAdmission +
                ", address=" + address +
                '}';
    }
}
