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
        given(categoryRepository.categoryWithNameExists("chest"))
                .willReturn(true);
        // when
        Boolean result = underTest.categoryWithNameDoesNotExists("chest");

        // then
        assertThat(result).isFalse();
    }

    @Test
    void canAddCategory() {
        // given
        Category category = new Category("chest");
        given(categoryRepository.categoryWithNameExists("chest"))
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
        Category category = new Category("chest");
        given(categoryRepository.categoryWithNameExists("chest"))
                .willReturn(true);
        // when
        // then
        assertThatThrownBy(() -> underTest.addCategory(category))
                .hasMessageContaining("Category with the name chest already exists");
    }

    @Test
    void canRemoveCategory() {
        // given
        Category category = new Category("chest");
        given(categoryRepository.findByCategoryName("chest"))
                .willReturn(Optional.of(category));
        // when
        Category result = underTest.removeCategory("chest");

        // then
        verify(categoryRepository).delete(category);
        assertThat(result).isEqualTo(category);
    }

    @Test
    void willThrowWhenCategoryDoesNotExist() {
        // given
        given(categoryRepository.findByCategoryName("chest"))
                .willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> underTest.removeCategory("chest"))
                .hasMessageContaining("Category with name Chest was not found");
    }
}