package com.mescfit.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Transactional
    @Query("SELECT a from AppUser a " +
            "WHERE a.email = ?1")
    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.locked = TRUE WHERE a.email = ?1")
    int lockAppUser(String email);

    @Transactional
    @Query("SELECT a.userId from AppUser a " +
            "WHERE a.email = ?1")
    Optional<Long> findUserIdByEmail(String email);
}
