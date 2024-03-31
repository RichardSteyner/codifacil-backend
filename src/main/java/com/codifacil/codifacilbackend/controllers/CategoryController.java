package com.codifacil.codifacilbackend.controllers;

import com.codifacil.codifacilbackend.common.CommonController;
import com.codifacil.codifacilbackend.models.entity.Category;
import com.codifacil.codifacilbackend.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin({"https://www.codifacil.club/", "http://www.codifacil.club/", "http://localhost:4200"})
@RequestMapping(value = "api/category")
@RestController
public class CategoryController extends CommonController<Category, Integer, CategoryService> {

    @PutMapping(value="/{id}")
    //binding result debe ir después del body que se está validando
    public ResponseEntity<?> edit(@Valid @RequestBody Category category, BindingResult result, @PathVariable Integer id){
        if(result.hasErrors()){
            return this.validate(result);
        }
        Optional<Category> o = this.service.findById(id);
        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Category categoryDB = o.get();
        categoryDB.setId(id);
        categoryDB.setName(category.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.update(categoryDB));
    }


    @Override
    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<Category> o = service.findById(id);
        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Category categoryBD = o.get();
        categoryBD.setVigencia(false);
        service.update(categoryBD);
        return ResponseEntity.noContent().build();
    }
}
