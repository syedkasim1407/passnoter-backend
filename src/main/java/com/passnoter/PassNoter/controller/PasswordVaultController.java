package com.passnoter.PassNoter.controller;

import com.passnoter.PassNoter.exception.PasswordVaultNotFoundException;
import com.passnoter.PassNoter.exception.UserNotFoundException;
import com.passnoter.PassNoter.model.PasswordVault;
import com.passnoter.PassNoter.model.UserAuth;
import com.passnoter.PassNoter.repository.PasswordVaultRepository;
import com.passnoter.PassNoter.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/vault")
public class PasswordVaultController {
    @Autowired
    UserAuthRepository userAuthRepository;
    @Autowired
    PasswordVaultRepository passwordVaultRepository;

    @PostMapping("/posting/{userId}")
    public PasswordVault create(@PathVariable Long userId, @RequestBody PasswordVault passwordVault)
    {
        UserAuth user=userAuthRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));
        passwordVault.setUserAuth(user);
        return passwordVaultRepository.save(passwordVault);
    }
    @GetMapping("/{userId}")
    public List<PasswordVault> getById(@PathVariable Long userId)
    {
        userAuthRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return passwordVaultRepository.findByUserAuthId(userId);
    }
    @PutMapping("/{userId}/{vaultId}")
    public PasswordVault updateVault(@PathVariable Long userId,@PathVariable Long vaultId,
                                     @RequestBody PasswordVault updatedVault )
    {
        UserAuth user=userAuthRepository.findById(userId).
                orElseThrow(()-> new UserNotFoundException(userId));
        PasswordVault vault=passwordVaultRepository.findById(vaultId).
                orElseThrow(()-> new UserNotFoundException(userId));
        if(!vault.getUserAuth().getId().equals(user.getId()))
        {
            throw new RuntimeException("Unauthorised update");
        }
        vault.setWebsiteName(updatedVault.getWebsiteName());
        vault.setUsername(updatedVault.getUsername());
        vault.setEmail(updatedVault.getEmail());
        vault.setPassword(updatedVault.getPassword());
        return passwordVaultRepository.save(vault);
    }

    @DeleteMapping("/{userId}/{vaultId}")
    public Map<String,String> delete(@PathVariable Long userId, @PathVariable Long vaultId)
    {
        UserAuth user=userAuthRepository.findById(userId).
                orElseThrow(()-> new UserNotFoundException(userId));

        PasswordVault vault=passwordVaultRepository.findById(vaultId).
                orElseThrow(()-> new PasswordVaultNotFoundException(vaultId));

        if(!vault.getUserAuth().getId().equals(user.getId()))
        {
            throw new RuntimeException("Unauthorised delete");
        }
        passwordVaultRepository.delete(vault);
        Map<String ,String> msg=new HashMap<>();
        msg.put("Message","The data is deleted successfully");
        return msg;
    }
}
