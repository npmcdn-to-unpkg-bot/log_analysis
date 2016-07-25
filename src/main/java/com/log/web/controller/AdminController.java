package com.log.web.controller;


import com.log.domain.Alert;
import com.log.domain.Employe;
import com.log.exception.LogException;
import com.log.service.AlertService;
import com.log.service.LogService;
import com.log.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Api(basePath = "/", value = "Admin", description = "Operations for  Admin", produces = "application/json")
@RestController
@RequestMapping("/ws/admin")
public class AdminController
{

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Inject
    private LogService logService ;

    @Inject
    private UserService userService;

    @Inject
    private AlertService alertService ;


    @RequestMapping(value = "/employe/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEmploye(@RequestBody Employe user) throws URISyntaxException
    {
        logger.debug("Call rest to create new  user  : {}", user);
        Employe result = userService.create(user);

        return ResponseEntity.created(new URI("/create"))
                .body(result);

    }

    @RequestMapping(value = "/employe/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmploye(@RequestBody Employe user) throws URISyntaxException, LogException
    {
        logger.debug("Call rest to create new  user  : {}", user);
        Employe result = userService.update(user);

        return ResponseEntity.created(new URI("/employe//update"))
                .body(result);

    }

    @RequestMapping(value = "/employe/delete",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEmploye(@RequestParam("id") long id) throws URISyntaxException, LogException
    {
        logger.debug("Call rest to delete alert  : {}", id);
        Employe result = userService.delete(id);

        return ResponseEntity.created(new URI("/alert/delete"))
                .body(result);

    }

    @RequestMapping(value = "/alert/delete",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAlert(@RequestParam("id") long id) throws URISyntaxException, LogException
    {
        logger.debug("Call rest to delete  alert  : {}", id);
        Alert result = alertService.delete(id);

        return ResponseEntity.created(new URI("/delete"))
                .body(result);

    }

    @RequestMapping(value = "/alert/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAlertList() throws URISyntaxException, LogException
    {
        logger.debug("Call rest to get the list of   alert ");
        List<Alert> result = alertService.getAlertList();

        return ResponseEntity.created(new URI("/list"))
                .body(result);

    }




    public static final String ROOT = "D://tmp/logs";

    @RequestMapping(method = RequestMethod.POST, value = "/read")
    public String handleFileUpload(@RequestPart("file") MultipartFile file) throws  LogException{

        logService.readLogFile(file);
       return "success" ;
    }

}
