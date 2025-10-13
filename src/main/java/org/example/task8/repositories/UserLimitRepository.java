package org.example.task8.repositories;

import org.example.task8.entityes.UserLimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLimitRepository extends JpaRepository<UserLimitEntity, Integer> {

    @Query("""
        UPDATE UserLimitEntity ul
        SET ul.dailyLimit = ul.defaultLimit
                """)
    @Modifying
    void setDefaultLimit();
}
