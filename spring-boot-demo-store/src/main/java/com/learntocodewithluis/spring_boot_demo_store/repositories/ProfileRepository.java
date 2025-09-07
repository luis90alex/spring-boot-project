package com.learntocodewithluis.spring_boot_demo_store.repositories;

import com.learntocodewithluis.spring_boot_demo_store.dtos.UserSummary;
import com.learntocodewithluis.spring_boot_demo_store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

//    @EntityGraph(attributePaths = "user")
//    List<Profile> findByLoyaltyPointsGreaterThanOrderByUserEmail(Integer loyaltyPoints);

    @Query("select p from Profile p where p.loyaltyPoints > :loyaltyPoints order by p.user.email")
    @EntityGraph(attributePaths = "user")
    List<Profile> findProfilesOrderByEmail(@Param("loyaltyPoints") Integer loyaltyPoints);

}