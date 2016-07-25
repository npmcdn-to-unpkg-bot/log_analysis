package com.log.web.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Api(basePath = "/", value = "Logs", description = "Operations for  logs", produces = "application/json")
@RestController
@RequestMapping("/ws/log")
public class LogsController
{

    private static final Logger log = LoggerFactory.getLogger(LogsController.class);

    public static final String ROOT = "D://tmp/logs";

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String handleFileUpload(@RequestPart("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
            } catch (IOException |RuntimeException e) {
            }
        }

        return "success";
    }
}
