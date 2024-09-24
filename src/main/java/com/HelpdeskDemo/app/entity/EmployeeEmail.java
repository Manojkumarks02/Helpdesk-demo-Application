package com.HelpdeskDemo.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "EMPLOYEE_TBL")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
}

