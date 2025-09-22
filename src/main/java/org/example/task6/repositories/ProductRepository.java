package org.example.task6.repositories;

import java.util.List;

import org.example.task6.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        select p
        from Product p
        where p.user.id = :userId
        """)
    List<Product> findByUserId(@Param("userId") Long userId);
}
