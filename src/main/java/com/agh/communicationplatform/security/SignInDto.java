package com.agh.communicationplatform.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class SignInDto {

    private final String email;
    private final String password;

    public SignInDto(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SignInDto that = (SignInDto) obj;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public String toString() {
        return "SignInDto{" +
                "email=" + email +
                "password=" + password +
                "}";
    }
}

