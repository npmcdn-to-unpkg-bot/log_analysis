package com.log.web.controller;

import com.log.domain.Employe;
import com.log.exception.LogException;
import com.log.repository.EmployeRepository;
import com.log.security.AuthoritiesConstants;
import com.log.service.UserService;
import com.log.utils.BusinessAndDomainConverter;
import com.log.web.dto.UserDto;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(basePath = "/", value = "user", description = "Operations for  user", produces = "application/json")
@RestController
@RequestMapping("/ws/user")
public class UserController
{

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserService userService ;

    @Inject
    private BusinessAndDomainConverter converter ;

    @Inject
    private EmployeRepository employeRepository ;


    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> createEmploye(@RequestBody UserDto user) throws URISyntaxException
    {
        logger.debug("Call rest to create new  user  : {}", user);
        Employe result = userService.create(user);

        return ResponseEntity.created(new URI("/create"))
                .body(result);

    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> updateEmploye(@RequestBody UserDto user) throws URISyntaxException,LogException
    {
        logger.debug("Call rest to create new  user  : {}", user);
        Employe result = userService.update(user);

        return ResponseEntity.created(new URI("/update"))
                .body(result);

    }

    @RequestMapping(value = "/delete",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteUser(@RequestParam long id)throws  LogException{
        logger.debug("REST request to delete User: {}", id);
        userService.delete(id) ;
        return ResponseEntity.ok().build();
    }

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
    public ResponseEntity<UserDto> getAccount()  throws  Exception{
        return Optional.ofNullable(userService.getUserWithAuthorities())
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<UserDto>> getAllUsers(Pageable pageable)
            throws URISyntaxException {
        Page<Employe> page = employeRepository.findAll(pageable);
        List<UserDto> managedUserDTOs = page.getContent().stream()
                .map(us -> converter.fromUserToBusiness(us))
                .collect(Collectors.toList());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/user/list");
        return new ResponseEntity<>(managedUserDTOs, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserDto> getUser(@RequestParam long id) {
        logger.debug("REST request to get User : {}", id);
        return employeRepository.findOneById(id)
                .map(us -> new ResponseEntity<>(converter.fromUserToBusiness(us), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
