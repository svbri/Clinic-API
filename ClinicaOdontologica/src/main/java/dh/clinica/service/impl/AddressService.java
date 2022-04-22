package dh.clinica.service.impl;


import dh.clinica.dto.AddressDTO;
import dh.clinica.entity.Address;
import dh.clinica.exceptions.ResourceNotFoundException;
import dh.clinica.repository.IAddressRepository;
import dh.clinica.service.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AddressDTO findById(Integer id) throws ResourceNotFoundException {
        Address address = addressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Dirección","id",id));
        return mapDTO(address);
    }

    @Override
    public AddressDTO create(AddressDTO addressDTO) {
        Address address = mapEntity(addressDTO);
        return mapDTO(addressRepository.save(address));
    }

    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
        Address address = addressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Dirección","id",id));
        addressRepository.delete(address);
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO, Integer id) {
        return null;
    }

    @Override
    public List<AddressDTO> findAll() {
        return null;
    }


    //Mapper
    private AddressDTO mapDTO(Address address) {
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        return addressDTO;
    }

    public Address mapEntity(AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        return address;
    }
}
