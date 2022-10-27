package com.example.ITBC.Logger.repository;

import com.example.ITBC.Logger.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByUsername(String username);

    Client findByUsernameOrEmail(String username, String email);

    Client findByToken(String token);

}
