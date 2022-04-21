package dh.clinica.service;

import dh.clinica.exceptions.ResourceNotFoundException;

import java.util.List;

//Interfaz padre CRUD
//Para cada entidad genero una interfaz correspondiente que hereda los métodos de la interfaz padre (o sea, ésta)
//Tengo todos los métodos comunes acá
public interface ICRUDService<T> {
    T findById(Integer id) throws ResourceNotFoundException;
    T create(T t) throws ResourceNotFoundException;
    void deleteById(Integer id) throws ResourceNotFoundException;
    T update(T t, Integer id) throws ResourceNotFoundException;
    List<T> findAll();
}
