package com.codingtest.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    @NotNull
    @Size(min = 1, max = 50)
    private String userName;

    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @NotNull
    @Max(value = 122)
    @Min(value = 0L, message = "age must be an integer and cannot be negative")
    private Integer age;
}
