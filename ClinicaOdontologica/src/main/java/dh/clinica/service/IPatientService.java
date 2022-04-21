package dh.clinica.service;

import dh.clinica.dto.PatientDTO;

//El CRUD trabaja con T, un tipo. Si yo le paso Patient, estaría trabajando con una entidad: le paso el DTO en su lugar
public interface IPatientService extends ICRUDService<PatientDTO>{
    //Acá puedo poner métodos exclusivos de esta entidad
}
