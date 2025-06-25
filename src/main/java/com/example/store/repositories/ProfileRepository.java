package com.example.store.repositories;

import com.example.store.dtos.UserSummary;
import com.example.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    @Query("select p.id as id, p.user.email as email from Profile p where p.loyaltyPoints > :loyaltyPoints order by p.user.email")
    @EntityGraph(attributePaths = "user")
    List<UserSummary> findUserProfiles(@Param("loyaltyPoints") int loyaltyPoints);
}
