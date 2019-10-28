package com.mitrais.demo.employee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "phone")
@Getter
@Setter
@EqualsAndHashCode
public class PhoneEntity implements Serializable {

  @Id
  private Long id;

  private String type;
  private String isdCode;
  private String phoneNumber;
  private String extension;

}
