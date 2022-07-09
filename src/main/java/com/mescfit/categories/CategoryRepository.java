package com.mescfit.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String name);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Category c " +
            "WHERE c.categoryName = ?1")
    Boolean categoryWithNameExists(String name);
}
