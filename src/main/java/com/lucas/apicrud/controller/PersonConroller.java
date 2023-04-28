package com.lucas.apicrud.controller;

import com.lucas.apicrud.dto.request.PersonRequestDTO;
import com.lucas.apicrud.dto.response.PersonResponseDTO;

import com.lucas.apicrud.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/people")
@RequiredArgsConstructor
public class PersonConroller {

    private final PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity <PersonResponseDTO> findById(@PathVariable(name = "id") Long id){

        return ResponseEntity.ok().body(personService.findbyId(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> findAll(){

        return ResponseEntity.ok().body(personService.findAll());
    }

    @PostMapping
    public ResponseEntity<PersonResponseDTO> register (@RequestBody PersonRequestDTO personRequestDTO, UriComponentsBuilder uribuilder){

        PersonResponseDTO personResponseDTO = personService.register(personRequestDTO);
        URI uri = uribuilder.path("/people/{id}").buildAndExpand(personResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(personResponseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonResponseDTO> update (@RequestBody PersonRequestDTO personDTO ,@PathVariable(name = "id") Long id){

        return ResponseEntity.ok().body(personService.update(id , personDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(personService.delete(id));
    }
}
