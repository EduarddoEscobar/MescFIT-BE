package com.mescfit.categories;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;
    @Column(name = "category_name", nullable = false, unique = true, length = 32)
    private String categoryName;

    @Column(name = "max_exercises", nullable = false)
    private Integer maxExercises = 3;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
