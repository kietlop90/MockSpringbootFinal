package com.duongam.demo.repositories;

import com.duongam.demo.dto.response.forlist.LResponseUser;
import com.duongam.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Page<LResponseUser> findAllBy(Pageable pageable);

    //Search User by Keyword
    @Query("SELECT u FROM User u WHERE CAST(u.id AS string) LIKE %:keyword% OR u.name LIKE %:keyword% OR u.email LIKE %:keyword%")
    Page<LResponseUser> findAllByOneKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT u FROM User u WHERE CAST(u.id AS string) LIKE %:keyword% OR u.name LIKE %:keyword% OR u.email LIKE %:keyword% " +
            "OR CAST(u.id AS string) LIKE %:keyword2% OR u.name LIKE %:keyword2% OR u.email LIKE %:keyword2% ")
    Page<LResponseUser> findAllByTwoKeyword(@Param("keyword") String keyword, @Param("keyword2") String keyword2, Pageable pageable);

    @Query("SELECT u FROM User u WHERE CAST(u.id AS string) LIKE %:keyword% OR u.name LIKE %:keyword% OR u.email LIKE %:keyword% " +
            "OR CAST(u.id AS string) LIKE %:keyword2% OR u.name LIKE %:keyword2% OR u.email LIKE %:keyword2%  " +
            "OR CAST(u.id AS string) LIKE %:keyword3% OR u.name LIKE %:keyword3% OR u.email LIKE %:keyword3% ")
    Page<LResponseUser> findAllByThreeKeyword(@Param("keyword") String keyword, @Param("keyword2") String keyword2, @Param("keyword3") String keyword3, Pageable pageable);

    @Query("SELECT u FROM User u WHERE CAST(u.id AS string) LIKE %:keyword% OR u.name LIKE %:keyword% OR u.email LIKE %:keyword% " +
            "OR CAST(u.id AS string) LIKE %:keyword2% OR u.name LIKE %:keyword2% OR u.email LIKE %:keyword2% " +
            "OR CAST(u.id AS string) LIKE %:keyword3% OR u.name LIKE %:keyword3% OR u.email LIKE %:keyword3% " +
            "OR CAST(u.id AS string) LIKE %:keyword4% OR u.name LIKE %:keyword4% OR u.email LIKE %:keyword4%")
    Page<LResponseUser> findAllByForKeyword(@Param("keyword") String keyword, @Param("keyword2") String keyword2, @Param("keyword3") String keyword3,
                                   @Param("keyword4") String keyword4, Pageable pageable);

     User findByUsername(String userName);
}
