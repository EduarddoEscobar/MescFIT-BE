package com.mescfit.categories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByCategoryName() {
        // given
        Category category = new Category("workout");
        underTest.save(category);
        // when
        Optional<Category> result = underTest.findByCategoryName("workout");
        Category actual = null;
        if(result.isPresent()){
            actual = result.get();
        }
        // then
        assertThat(actual).isEqualTo(category);
    }


    @Test
    void itShouldReturnTrueWhenCategoryWithNameExists() {
        // given
        Category category = new Category("workout");
        underTest.save(category);
        // when
        Boolean result = underTest.categoryWithNameExists("workout");
        // then
        assertThat(result).isTrue();
    }

    @Test
    void itShouldReturnFalseWhenCategoryWithNameDoesNotExists() {
        // given
        // when
        Boolean result = underTest.categoryWithNameExists("workout");
        // then
        assertThat(result).isFalse();
    }
}