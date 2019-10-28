package com.mitrais.demo.employee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@EqualsAndHashCode
public class EmployeeEntity implements Serializable {

  @Id
  private Long id;

  private String firstName;
  private String lastName;

  public LocalDate dateOfBirth;
  public LocalDate startDate;
  public LocalDate endDate;
  private String employmentType;

  private String email;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "employee_id", referencedColumnName = "id")
  private List<PhoneEntity> phones;
}
