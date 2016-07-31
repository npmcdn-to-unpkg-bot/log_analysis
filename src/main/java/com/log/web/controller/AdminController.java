package com.log.web.controller;


import com.log.service.LogService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@Api(basePath = "/", value = "Admin", description = "Operations for  Admin", produces = "application/json")
@RestController
@RequestMapping("/ws/admin")
public class AdminController
{

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Inject
    private LogService logService ;

}
