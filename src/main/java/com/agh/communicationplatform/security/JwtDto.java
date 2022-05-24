package com.agh.communicationplatform.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class JwtDto {

    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String role;
    private final String jwt;

    public JwtDto(@JsonProperty("userId") Long userId, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("email") String email, @JsonProperty("role") String role, @JsonProperty("jwt") String jwt) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.jwt = jwt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, role, jwt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        JwtDto that = (JwtDto) obj;
        return Objects.equals(userId, that.userId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(jwt, that.jwt);
    }

    @Override
    public String toString() {
        return "JwtDto{" +
                "userId" + userId +
                "firstName" + firstName +
                "lastName" + lastName +
                "email=" + email +
                "role=" + role +
                "jwt=" + jwt +
                "}";
    }
}

