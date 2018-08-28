package com.codingtest.controller;

import com.codingtest.dto.UserDto;
import com.codingtest.entities.User;
import com.codingtest.error.RestExceptionHandler;
import com.codingtest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void testSaveUser() throws Exception {
        UserDto userDto = new UserDto("jsmith", "John", "Smith", 30);
        ObjectMapper mapper = new ObjectMapper();
        String userDtoJson = mapper.writeValueAsString(userDto);

        when(userService.save((User) anyObject())).thenReturn(new User());
        when(modelMapper.map(Matchers.any(UserDto.class), eq(User.class))).thenReturn(new User());

        this.mockMvc.perform(post("/codingtest/user/add").contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson)).andExpect(status().isOk());
    }

    @Test
    public void testSaveUserWithInvalidInput() throws Exception {

        UserDto userDto = new UserDto("", "John", "Smith", 30);
        ObjectMapper mapper = new ObjectMapper();
        String userDtoJson = mapper.writeValueAsString(userDto);

        when(userService.save((User) anyObject())).thenThrow(MethodArgumentNotValidException.class);


        this.mockMvc.perform(post("/codingtest/user/add").contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson)).andExpect(status().isBadRequest());
    }
}