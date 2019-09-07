package com.betapi.services;

import com.betapi.model.BetOwner;
import org.springframework.data.repository.CrudRepository;

public interface BetOwnerRepository extends CrudRepository<BetOwner,Long> {
}
