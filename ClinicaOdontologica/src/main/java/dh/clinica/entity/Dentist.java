package dh.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dentists")
@Getter @Setter

@AllArgsConstructor
@NoArgsConstructor
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastname;
    private String name;
    private String license;

    @OneToMany(mappedBy = "dentist")
    @JsonIgnore
    private Set<Appointment> appointments;

    @Override
    public String toString() {
        return "Dentist{" +
                "id=" + id +
                ", lastName='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", license='" + license + '\'' +
                '}';
    }
}
