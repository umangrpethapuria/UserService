package com.codingtest.dto;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertTrue;


public class UserDtoTest {

    Validator validator;

    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenRequiredFieldIsMissing_ThenInvalidate() {
        UserDto userDto = new UserDto("", "John", "Smith", 30);
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertTrue(violations.size() == 1);

        userDto = new UserDto("jsmith", "", "Smith", 30);
        violations = validator.validate(userDto);
        assertTrue(violations.size() == 1);

        userDto = new UserDto("jsmith", "John", "", 30);
        violations = validator.validate(userDto);
        assertTrue(violations.size() == 1);

        userDto = new UserDto("jsmith", "John", "Smith", null);
        violations = validator.validate(userDto);
        assertTrue(violations.size() == 1);
    }


    @Test
    public void whenUserNameExceedsLength_ThenInvalidate() {
        UserDto userDto = new UserDto("jsmithsadaasdsadasdasdsadsadsadsadsadsadsadsadsadsd", "John", "Smith", 30);
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertTrue("User name cannot be more than 50 characters", violations.size() == 1);
    }

    @Test
    public void whenFirstNameExceedsLength_ThenInvalidate() {
        UserDto userDto = new UserDto("jsmith", "jsmithsadaasdsadasdasdsadsadsadsadsadsadsadsadsadsd", "Smith", 30);
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertTrue("First name cannot be more than 50 characters", violations.size() == 1);
    }

    @Test
    public void whenLastNameExceedsLength_ThenInvalidate() {
        UserDto userDto = new UserDto("jsmith", "John", "jsmithsadaasdsadasdasdsadsadsadsadsadsadsadsadsadsd", 30);
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertTrue("Last name cannot be more than 50 characters", violations.size() == 1);
    }


    @Test
    public void whenAgeNegative_ThenInvalidate() {
        UserDto userDto = new UserDto("jsmith", "John", "Smith", -1);
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        assertTrue("Age cannot be negative", violations.size() == 1);
    }

    @Test
    public void whenAgeGreaterThanThreeDigits_ThenInvalidate() {
        UserDto userDto = new UserDto("jsmith", "John", "Smith", 1000);
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        assertTrue("Age invalid", violations.size() == 1);
    }
}