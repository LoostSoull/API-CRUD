package com.lucas.apicrud.service;

import com.lucas.apicrud.dto.request.PersonRequestDTO;
import com.lucas.apicrud.dto.response.PersonResponseDTO;
import com.lucas.apicrud.entity.Person;
import com.lucas.apicrud.repository.PersonRepository;
import com.lucas.apicrud.util.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    @Override
    public PersonResponseDTO findbyId(Long id) {

    return personMapper.toPersonDTO(returnPerson(id));

    }

    @Override
    public List<PersonResponseDTO> findAll() {



       return personMapper.toPeopleDTO(personRepository.findAll());
    }

    @Override
    public PersonResponseDTO register(PersonRequestDTO PersonDTO) {

        Person person = personMapper.toPerson(PersonDTO);

         return personMapper.toPersonDTO(personRepository.save(person));

    }

    @Override
    public PersonResponseDTO update(Long id,PersonRequestDTO personDTO) {

        Person person = returnPerson(id);

        personMapper.upatePersonData(person , personDTO);

        return personMapper.toPersonDTO(personRepository.save(person));

    }

    @Override
    public String delete(Long id) {


      personRepository.deleteById(id);
      return "person id:" + id + "deleted";
    }

    private Person returnPerson(Long id){
        return personRepository.findById(id).orElseThrow(()-> new RuntimeException("person wasn't found in data base"));
    }

}
