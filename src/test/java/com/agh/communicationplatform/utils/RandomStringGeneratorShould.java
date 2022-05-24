package com.agh.communicationplatform.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomStringGeneratorShould {

    @Test
    void returnRandomString() {
        //given
        final var length = 20;

        //when
        final var result = RandomStringGenerator.generate(length);

        //then
        assertThat(result.length()).isEqualTo(length);
        assertThat(isAlphaNumeric(result)).isTrue();
    }

    private boolean isAlphaNumeric(String s){
        String pattern = "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }
}
