package org.example.task6.repositories;

import java.util.List;
import java.util.Optional;

import org.example.task6.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        select u from User u
        LEFT JOIN FETCH u.products
        where u.id = :id
        """)
    Optional<User> findById(@Param("id")Long id);

    @Query("""
        select u from User u
        LEFT JOIN FETCH u.products
        """)
    List<User> findAll();
}
