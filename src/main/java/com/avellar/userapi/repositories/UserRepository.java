package com.avellar.userapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avellar.userapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
