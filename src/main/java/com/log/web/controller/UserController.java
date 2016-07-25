package com.log.web.controller;

import com.log.domain.Employe;
import com.log.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Api(basePath = "/", value = "user", description = "Operations for  user", produces = "application/json")
@RestController
@RequestMapping("/ws/user")
public class UserController
{

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserService userService ;

    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authentictae(@RequestParam("username") String username,
                                          @RequestParam("password") String password,HttpServletRequest request) throws URISyntaxException
    {
        logger.debug("Call rest to authenticate with user name : {}", username);

        Object result = userService.authenticate(username, password, request) ;
        return ResponseEntity.created(new URI("/authenticate"))
                .body(result);

    }


    @RequestMapping(value = "/account",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Employe> getAccount()  throws  Exception{
        return Optional.ofNullable(userService.getUserWithAuthorities())
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
