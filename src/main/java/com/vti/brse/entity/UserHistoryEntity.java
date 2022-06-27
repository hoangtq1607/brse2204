package com.vti.brse.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_history")
public class UserHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UserEntity user;

    private String email;

    private String fullName;

    private String password;

    private LocalDate birthDay;

    @JsonFormat(pattern = "yy-MM-dd-HH:mm")
    private LocalDateTime createdDate;
}
