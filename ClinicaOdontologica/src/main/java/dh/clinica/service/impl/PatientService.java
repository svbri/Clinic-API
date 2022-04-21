package dh.clinica.service.impl;

import dh.clinica.dto.PatientDTO;
import dh.clinica.entity.Patient;
import dh.clinica.exceptions.ResourceNotFoundException;
import dh.clinica.repository.IPatientRepository;
import dh.clinica.service.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {
    //Inyecto el repositorio. Con esto traigo los métodos guardar, eliminar, traer por id, etc.
    @Autowired
    private IPatientRepository patientRepository;

    //Inyecto el model mapper
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressService addressService;

    @Override
    public PatientDTO findById(Integer id) throws ResourceNotFoundException {
        //Traigo la entidad de la base de datos
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", id));
        //Paso la entidad a DTO
        return mapDTO(patient);
    }

    //Petición POST en Postman
    //No lleva id, Spring lo crea automáticamente
    @Override
    public PatientDTO create(PatientDTO patientDTO) throws ResourceNotFoundException {
        if(patientDTO == null){
            throw new ResourceNotFoundException("Paciente","id",null);
        }

        //La fecha de admisión la establezco como la actual en el momento que creo el paciente
        patientDTO.setDateAdmission(LocalDate.now());

        //Recibiendo DTO por parámetro
        //1- Hay que convertir el DTO en entidad
        Patient patient = mapEntity(patientDTO);
        //2- Guardamos la entidad en la base de datos
        Patient newPatientSave = patientRepository.save(patient);
        //3- La entidad guardada en la base de datos, la retornamos como DTO
        return mapDTO(newPatientSave);
    }

    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
        //1- Buscar la entidad
        //2- Verificar que se encontró
        //3- Eliminarla
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Paciente","id", id));
        patientRepository.delete(patient);
    }


    //Cuando lo paso por Postman le pongo PUT en lugar de POST
    //Si el id existe, el método save lo actualiza
    @Override
    public PatientDTO update(PatientDTO patientDTO, Integer id) throws ResourceNotFoundException {
//        Patient patient = mapEntity(patientDTO);
//        Patient newPatientSave = patientRepository.save(patient);
//        return mapDTO(newPatientSave);

        Patient patient = patientRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("Patient", "id", id)));

        patient.setName(patientDTO.getName());
        patient.setLastname(patientDTO.getLastname());
        patient.setDni(patientDTO.getDni());
        patient.setEmail(patientDTO.getEmail());
        patient.setDateAdmission(patientDTO.getDateAdmission());
        //2-opciones
        //paciente.setDomicilio(pacienteDTO.getDomicilioDTO().toEntity());
        patient.setAddress(addressService.mapEntity(patientDTO.getAddress()));
        Patient updatedPatient = patientRepository.save(patient);

        return mapDTO(updatedPatient);
    }

    @Override
    public List<PatientDTO> findAll() {
        List<Patient> patientList = patientRepository.findAll();
        List<PatientDTO> patientDTOList = patientList.stream().map(patient -> mapDTO(patient)).collect(Collectors.toList());
        return patientDTOList;
    }

    public List<PatientDTO> getPatientWithLastNameLike (String lastname) {
        List<Patient> patients = patientRepository.getStudentByLastNameLike(lastname);
        List<PatientDTO> patientDTOList = patients.stream().map(patient -> mapDTO(patient)).collect(Collectors.toList());
        return patientDTOList;
    }

    //Mapper
    private PatientDTO mapDTO(Patient patient) {
        PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
        return patientDTO;
    }

    Patient mapEntity(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        return patient;
    }

}
