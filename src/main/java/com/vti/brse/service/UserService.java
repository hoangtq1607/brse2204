package com.vti.brse.service;

import com.vti.brse.entity.UserEntity;
import com.vti.brse.entity.UserHistoryEntity;
import com.vti.brse.repository.UserHistoryRepository;
import com.vti.brse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserHistoryRepository userHistoryRepository;

    public Page<UserEntity> findAllUsers(Pageable pageable) {
        return userRepository.findAllByOrderByCreatedDateDesc(pageable);
    }

    public List<UserEntity> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Page<UserEntity> findAllUsersByEmail(String email, Pageable pageable) {
        return userRepository.findByEmailLike("%" + email + "%", pageable);
    }

    public Optional<UserEntity> findUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public UserEntity addUser(UserEntity user) {
        user.setId(null);
        user.setCreatedDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Optional<UserEntity> updateUser(Integer userId, UserEntity user) {

        return userRepository.findById(userId)
                .map(dbUser -> {
                    saveHistory(dbUser);
                    dbUser.setEmail(user.getEmail());
                    dbUser.setPassword(user.getPassword());
                    dbUser.setBirthDay(user.getBirthDay());
                    dbUser.setFullName(user.getFullName());
                    return userRepository.save(dbUser);
                });

    }

    void saveHistory(UserEntity userEntity) {

        UserHistoryEntity history = new UserHistoryEntity();
        history.setUser(userEntity);
        history.setEmail(userEntity.getEmail());
        history.setPassword(userEntity.getPassword());
        history.setBirthDay(userEntity.getBirthDay());
        history.setFullName(userEntity.getFullName());
        history.setCreatedDate(LocalDateTime.now());
        userHistoryRepository.save(history);

    }

    public void deleteByUserId(Integer userId) {
        userRepository.deleteById(userId);
    }

    public List<UserHistoryEntity> showHistory(Integer userId) {
        return userHistoryRepository.findByUserIdOrderByIdDesc(userId);
    }

}
