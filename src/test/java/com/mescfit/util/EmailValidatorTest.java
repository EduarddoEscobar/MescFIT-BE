package com.mescfit.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EmailValidatorTest {

    EmailValidator underTest = new EmailValidator();

    @Test
    void itShouldReturnFalseWithInvalidEmail() {
        // given
        String email = "foo";

        // when
        boolean result = underTest.isValidEmail(email);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void itShouldReturnFalseWithout() {
        // given
        String email = "foo";

        // when
        boolean result = underTest.isValidEmail(email);

        // then
        assertThat(result).isFalse();
    }
}
