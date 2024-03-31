package com.codifacil.codifacilbackend.controllers;

import com.codifacil.codifacilbackend.common.CommonController;
import com.codifacil.codifacilbackend.models.entity.Person;
import com.codifacil.codifacilbackend.models.entity.User;
import com.codifacil.codifacilbackend.services.PersonService;
import com.codifacil.codifacilbackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin({"https://www.codifacil.club/", "http://www.codifacil.club/", "http://localhost:4200"})
@RequestMapping(value = "user")
@RestController
public class UserController extends CommonController<User, Long, UserService> {

    @Autowired
    private PersonService personService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return ResponseEntity.ok(this.service.login(user.getUsername(), user.getPassword()));
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody User entity, BindingResult result) {
        //return super.crear(entity, result);
        if(result.hasErrors()){
            return this.validate(result);
        }

        try {
            User entityDB;
            entity.setVigencia(true);
            if(entity.getPerson().getId()!=null) {
                entityDB = service.insert(entity);
            } else {
                Optional<Person> oPerson = personService.findByDocumentNumber(entity.getPerson().getDocumentNumber());
                if(oPerson.isPresent()) {
                    entity.getPerson().setId(oPerson.get().getId());
                } else {
                    Person person = personService.insert(entity.getPerson());
                    entity.setPerson(person);
                }
                entityDB = service.insert(entity);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value="/{id}")
    //binding result debe ir después del body que se está validando
    public ResponseEntity<?> edit(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return this.validate(result);
        }
        Optional<User> o = this.service.findById(id);
        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User userBD = o.get();
        userBD.setId(id);
        userBD.setPassword(user.getPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.update(userBD));
    }

    @Override
    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> o = service.findById(id);
        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User userBD = o.get();
        userBD.setVigencia(false);
        service.update(userBD);
        return ResponseEntity.noContent().build();
    }

}
