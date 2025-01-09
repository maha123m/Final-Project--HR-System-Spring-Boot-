package com.ecommerce.project.service;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAddresses();

    AddressDTO getAddressById(Long id);

    List<AddressDTO> getUserAddresses(User user);

    AddressDTO updateAddress(Long id, AddressDTO addressDTO);

    String deleteAddress(Long id);
}
