package com.lucas.apicrud.service;


import com.lucas.apicrud.dto.request.PersonRequestDTO;
import com.lucas.apicrud.dto.response.PersonResponseDTO;

import java.util.List;

public interface PersonService {
    PersonResponseDTO findbyId(Long id);

    List<PersonResponseDTO> findAll();

    PersonResponseDTO register(PersonRequestDTO PersonDTO);

    PersonResponseDTO update(Long id,PersonRequestDTO PersonDTO);

    String delete(Long id);
}
