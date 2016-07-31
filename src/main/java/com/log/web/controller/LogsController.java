package com.log.web.controller;

import com.log.exception.LogException;
import com.log.security.AuthoritiesConstants;
import com.log.service.LogService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;

@Api(basePath = "/", value = "Logs", description = "Operations for  logs", produces = "application/json")
@RestController
@RequestMapping("/ws/logs")
public class LogsController
{
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);


    @Inject
    private LogService logService ;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    @Secured(AuthoritiesConstants.ADMIN)
    public Object handleFileUpload(@RequestPart("file") MultipartFile file) throws  LogException{

        logger.info("read log file : {}",file.getOriginalFilename());
        return logService.readLogFile(file);
    }

}
