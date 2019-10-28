package com.mitrais.demo.employee;

import lombok.Getter;
import lombok.Setter;

import com.mitrais.demo.employee.ValidationGroups.CreateEmployee;
import com.mitrais.demo.employee.ValidationGroups.UpdateEmployee;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Phone {
  @NotNull(groups = CreateEmployee.class)
  private Long id;

  @NotEmpty(groups = {CreateEmployee.class,
                      UpdateEmployee.class})
  private String type;

  @NotEmpty(groups = {CreateEmployee.class,
                      UpdateEmployee.class})
  private String isdCode;

  @NotEmpty(groups = {CreateEmployee.class,
                      UpdateEmployee.class})
  private String phoneNumber;

  private String extension;
}
