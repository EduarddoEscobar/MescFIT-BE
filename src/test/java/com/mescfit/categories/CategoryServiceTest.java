package com.mescfit.categories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CategoryService(categoryRepository);
    }

    @Test
    void shouldGetAllCategories() {
        // when
        underTest.getAllCategories();
        // then
        verify(categoryRepository).findAll();
    }

    @Test
    void canSeeIfCategoryWithNameDoesNotExists() {
        // given
        given(categoryRepository.categoryWithNameExists("Chest"))
                .willReturn(true);
        // when
        Boolean result = underTest.categoryWithNameDoesNotExists("Chest");

        // then
        assertThat(result).isFalse();
    }

    @Test
    void canAddCategory() {
        // given
        Category category = new Category("Chest");
        given(categoryRepository.categoryWithNameExists("Chest"))
                .willReturn(false);
        // when
        underTest.addCategory(category);

        // then
        ArgumentCaptor<Category> categoryArgumentCaptor =
                ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        Category result = categoryArgumentCaptor.getValue();
        assertThat(result).isEqualTo(category);
    }

    @Test
    void willThrowWhenCategoryAlreadyExists() {
        // given
        Category category = new Category("Chest");
        given(categoryRepository.categoryWithNameExists("Chest"))
                .willReturn(true);
        // when
        // then
        assertThatThrownBy(() -> underTest.addCategory(category))
                .hasMessageContaining("Category with the name Chest already exists");
    }

    @Test
    void canRemoveCategory() {
        // given
        Category category = new Category("Chest");
        given(categoryRepository.findByCategoryName("Chest"))
                .willReturn(Optional.of(category));
        // when
        Category result = underTest.removeCategory("Chest");

        // then
        verify(categoryRepository).delete(category);
        assertThat(result).isEqualTo(category);
    }

    @Test
    void willThrowWhenCategoryDoesNotExist() {
        // given
        given(categoryRepository.findByCategoryName("Chest"))
                .willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> underTest.removeCategory("Chest"))
                .hasMessageContaining("Category with name Chest was not found");
    }
}