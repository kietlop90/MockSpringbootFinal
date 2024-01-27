package com.mock.api.repository;

import com.mock.api.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {

    @Query("SELECT u from UserModel u where u.username = :userName")
    Optional<UserModel> findByUsername(String userName);
}
