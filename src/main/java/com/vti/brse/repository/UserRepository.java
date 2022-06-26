package com.vti.brse.repository;

import com.vti.brse.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByEmail(String email);

    List<UserEntity> findByEmailAndPassword(String email, String password);

}
