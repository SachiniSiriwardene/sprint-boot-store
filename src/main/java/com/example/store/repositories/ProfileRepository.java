package com.example.store.repositories;

import com.example.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    // Additional query methods can be defined here if needed
}
