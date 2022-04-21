package dh.clinica.service.impl;

import dh.clinica.dto.DentistDTO;
import dh.clinica.entity.Dentist;
import dh.clinica.exceptions.ResourceNotFoundException;
import dh.clinica.repository.IDentistRepository;
import dh.clinica.service.IDentistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentistService implements IDentistService {
    @Autowired
    private IDentistRepository dentistRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DentistDTO findById(Integer id) throws ResourceNotFoundException {
        Dentist dentist = dentistRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Odontólogo", "id", id));
        return mapDTO(dentist);
    }

    @Override
    public DentistDTO create(DentistDTO dentistDTO) throws ResourceNotFoundException {
        if(dentistDTO == null){
            throw new ResourceNotFoundException("Odontólgo", "id", null);
        }

        Dentist dentist = mapEntity(dentistDTO);
        Dentist newDentistSave = dentistRepository.save(dentist);
        return mapDTO(newDentistSave);
    }

    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
        Dentist dentist = dentistRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Odontólogo", "id", id));
        dentistRepository.delete(dentist);
    }

    @Override
    public DentistDTO update(DentistDTO dentistDTO, Integer id) {
        Dentist dentist = mapEntity(dentistDTO);
        Dentist newDentistSave = dentistRepository.save(dentist);
        return mapDTO(newDentistSave);
    }

    @Override
    public List<DentistDTO> findAll() {
        List<Dentist> dentistList = dentistRepository.findAll();

        List<DentistDTO> dentistDTOList = dentistList.stream().map(dentist -> mapDTO(dentist)).collect(Collectors.toList());
        return dentistDTOList;
    }


    //Mapper
    private DentistDTO mapDTO(Dentist dentist) {
        DentistDTO dentistDTO = modelMapper.map(dentist, DentistDTO.class);
        return dentistDTO;
    }

    Dentist mapEntity(DentistDTO dentistDTO) {
        Dentist dentist = modelMapper.map(dentistDTO, Dentist.class);
        return dentist;
    }
}
