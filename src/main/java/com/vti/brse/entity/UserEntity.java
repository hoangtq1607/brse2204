package com.vti.brse.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 12)
    private String password;

    @NotNull
    private LocalDate birthDay;

    private LocalDateTime createdDate;

}
