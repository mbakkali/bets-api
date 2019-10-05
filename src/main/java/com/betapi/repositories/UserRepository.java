package com.betapi.repositories;

import com.betapi.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,String> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);


}
