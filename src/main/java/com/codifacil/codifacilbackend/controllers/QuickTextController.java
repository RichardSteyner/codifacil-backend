package com.codifacil.codifacilbackend.controllers;

import com.codifacil.codifacilbackend.common.CommonController;
import com.codifacil.codifacilbackend.models.entity.QuickText;
import com.codifacil.codifacilbackend.models.entity.User;
import com.codifacil.codifacilbackend.services.QuickTextService;
import com.codifacil.codifacilbackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin({"https://www.codifacil.club/", "http://www.codifacil.club/", "http://localhost:4200"})
@RequestMapping(value = "api/quicktext")
@RestController
public class QuickTextController extends CommonController<QuickText, Long, QuickTextService> {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/calculate")
    public ResponseEntity<?> calculate(@RequestBody QuickText quickText) {
        String result = this.service.calculate(quickText);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<?> getData(@PathVariable Long ownerId) {
        Optional<User> oUser = userService.findById(ownerId);

        if (oUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userBD = oUser.get();

        if(!userBD.isVigencia()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if(userBD.getRole().equals("ADMIN")) {
            return ResponseEntity.ok(this.service.findAll());
        } else {
            return ResponseEntity.ok(this.service.findByOwner(ownerId));
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody QuickText quickText, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return this.validate(result);
        }
        Optional<QuickText> o = this.service.findById(id);
        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        QuickText quickTextDB = o.get();
        quickTextDB.setId(id);
        quickTextDB.setTitle(quickText.getTitle());
        quickTextDB.setDescription(quickText.getDescription());
        quickTextDB.setCategory(quickText.getCategory());
        quickTextDB.setBody(quickText.getBody());
        quickTextDB.setMergeFields(quickText.getMergeFields());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.update(quickTextDB));
    }

}
