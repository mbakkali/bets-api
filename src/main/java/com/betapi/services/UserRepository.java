package com.betapi.services;

import com.betapi.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Owner,Long> {
}
