package com.example.testTask.repo;

import com.example.testTask.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where " +
            "((UPPER(c.firstName)=UPPER(:name) and :name is not null) or (:name is null)) and" +
            "((UPPER(c.surname)=UPPER(:surname) and :surname is not null) or (:surname is null)) and " +
            "((UPPER(c.secondName)=UPPER(:secondName) and :secondName is not null) or (:secondName is null)) and " +
            "((c.phone=:phone and :phone is not null) or (:phone is null)) and " +
            "((c.email=:email and :email is not null) or (:email is null))")
    List<Client> findAllClientsByParam(String name, String surname, String secondName, String phone, String email);

}
