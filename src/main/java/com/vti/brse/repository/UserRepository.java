package com.vti.brse.repository;

import com.vti.brse.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Page<UserEntity> findByEmailLike(String email, Pageable pageable);

    List<UserEntity> findByEmailAndPassword(String email, String password);

    List<UserEntity> findByCreatedDateLessThanEqualOrderByCreatedDateDesc(LocalDate date);
    // select * from user where created_date <= :date

}
