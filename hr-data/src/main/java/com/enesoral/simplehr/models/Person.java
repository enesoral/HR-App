package com.enesoral.simplehr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String address;

    @Email(message = "Email should valid")
    private String email;

    @NotNull
    @Size(min = 10, max = 11, message="Phone must be 10 or 11 characters")
    private String phone;
}