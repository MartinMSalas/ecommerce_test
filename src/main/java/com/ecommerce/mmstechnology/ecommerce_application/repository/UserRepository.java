package com.ecommerce.mmstechnology.ecommerce_application.repository;

import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE user_table u SET u.firstName = :firstName,  u.lastName = :lastName WHERE u.userId = :userId")
    int updateUserById(@Param("userId") Long userId,
                       @Param("firstName") String firstName,
                       @Param("lastName") String lastName);






}
