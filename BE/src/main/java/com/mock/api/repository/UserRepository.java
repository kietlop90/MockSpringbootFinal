package com.mock.api.repository;

import com.mock.api.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // findByUsernameIgnoreCase();
    @Query("SELECT u FROM User u WHERE lower(u.username) = lower(:userName) ")
    Optional<User> findByUsername(String userName);

    @Query("SELECT u FROM User u WHERE " +
            "(u.username LIKE concat('%', :search, '%')" +
            " or u.name LIKE concat('%', :search, '%'))  ")
    Page<User> searchByName(String search, Pageable pageable);
}
