package com.vti.brse.repository;

import com.vti.brse.entity.UserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistoryEntity, Integer> {
    List<UserHistoryEntity> findByUserIdOrderByIdDesc(Integer userId);
}
