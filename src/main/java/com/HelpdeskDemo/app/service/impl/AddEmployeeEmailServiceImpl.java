package com.HelpdeskDemo.app.service.impl;

import com.HelpdeskDemo.app.dto.AddEmployeeEmailDto;
import com.HelpdeskDemo.app.entity.EmployeeEmail;
import com.HelpdeskDemo.app.repository.EmployeeEmailRepository;
import com.HelpdeskDemo.app.service.AddEmployeeEmailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddEmployeeEmailServiceImpl implements AddEmployeeEmailService {

    private final EmployeeEmailRepository employeeEmailRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<AddEmployeeEmailDto> addEmployeeEmail(List<AddEmployeeEmailDto> addEmployeeEmailDto) {
        List<EmployeeEmail> listOfEmails = addEmployeeEmailDto.stream().map(addEmployee -> modelMapper.map(addEmployee, EmployeeEmail.class)).collect(Collectors.toList());
        List<EmployeeEmail> employeeEmails = employeeEmailRepository.saveAll(listOfEmails);
        return employeeEmails.stream().map(employeeEmail -> modelMapper.map(employeeEmail, AddEmployeeEmailDto.class)).collect(Collectors.toList());

    }
}
