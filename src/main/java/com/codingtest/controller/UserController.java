package com.codingtest.controller;

import com.codingtest.dto.UserDto;
import com.codingtest.entities.User;
import com.codingtest.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;


@RestController
@RequestMapping("/codingtest")
@EnableSwagger2
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(path = "/user/add", method = RequestMethod.POST)
    public UserDto saveUser(@Valid @RequestBody UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);
        user = service.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.codingtest"))
                .paths(PathSelectors.any())
                .build();
    }
}
