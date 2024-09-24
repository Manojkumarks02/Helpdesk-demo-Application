package com.HelpdeskDemo.app.controller;

import com.HelpdeskDemo.app.dto.AddEmployeeEmailDto;
import com.HelpdeskDemo.app.service.AddEmployeeEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
@RequiredArgsConstructor
public class EmployeeEmailController {

    private final AddEmployeeEmailService addEmployeeEmailService;

    @PostMapping(path = "/email")
    public ResponseEntity<List<AddEmployeeEmailDto>> addEmployee(@RequestBody List<AddEmployeeEmailDto> addEmployeeEmailDto){
        return new ResponseEntity<>(addEmployeeEmailService.addEmployeeEmail(addEmployeeEmailDto), HttpStatus.CREATED);

    }


}
