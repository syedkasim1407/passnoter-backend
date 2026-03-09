package com.passnoter.PassNoter.repository;

import com.passnoter.PassNoter.model.PasswordVault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordVaultRepository extends JpaRepository<PasswordVault,Long> {
    public List<PasswordVault> findByUserAuthId(Long userid);
}
