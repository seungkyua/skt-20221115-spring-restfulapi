package com.example.myrestfulservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryV5 extends JpaRepository<UserV5, Integer> {
}
