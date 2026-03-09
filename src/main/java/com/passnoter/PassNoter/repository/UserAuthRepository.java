package com.passnoter.PassNoter.repository;

import com.passnoter.PassNoter.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth,Long> {
    Optional<UserAuth> findByEmail(String email);

    Optional<UserAuth> findByEmailAndPassword(String email,String password);
}
