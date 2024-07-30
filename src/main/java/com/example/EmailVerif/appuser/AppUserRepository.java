package com.example.EmailVerif.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);


    @Transactional
    @Modifying
    @Query(value="Update app_user  set enabled=TRUE where email=?1;",nativeQuery = true)
    int enableAppUser(String email);


}
