package dh.clinica.repository;

import dh.clinica.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//El primer parámetro es la entidad a la que hago referencia, el segundo es el tipo del atributo id
//JPA trae los métodos findById, findAll, etc...
public interface IPatientRepository extends JpaRepository<Patient, Integer> {
    @Query("from Patient p where p.lastname like %:apellido%")
    List<Patient> getStudentByLastNameLike(@Param("apellido") String lastname);
}
