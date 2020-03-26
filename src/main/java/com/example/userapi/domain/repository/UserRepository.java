package com.example.userapi.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.userapi.domain.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query("UPDATE User u SET username = :_username, password = :_password, display_name = :_display_name, email = :_email "
            + "WHERE u.username = :_old_username")
    @Transactional
    void updateUser(@Param("_username") String newUserName, @Param("_password")String newPassword,
                        @Param("_display_name") String newDisplayName, @Param("_email")String newEmail, 
                        @Param("_old_username") String oldUsername);

    List<User> findByUsername(String username);
}
