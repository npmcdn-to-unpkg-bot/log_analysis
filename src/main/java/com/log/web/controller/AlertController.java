package com.log.web.controller;

import com.log.domain.Alert;
import com.log.domain.Employe;
import com.log.domain.LogLevel;
import com.log.exception.LogException;
import com.log.repository.AlertRepository;
import com.log.security.AuthoritiesConstants;
import com.log.service.AlertService;
import com.log.utils.BusinessAndDomainConverter;
import com.log.web.dto.AlertDto;
import com.log.web.dto.UserDto;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(basePath = "/", value = "Alert", description = "Operations for  alert", produces = "application/json")
@RestController
@RequestMapping("/ws/alert")
public class AlertController
{
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);


    @Inject
    private AlertService alertService ;

    @Inject
    private AlertRepository alertRepository ;

    @Inject
    private BusinessAndDomainConverter converter ;



    @RequestMapping(value = "/delete",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> deleteAlert(@RequestParam("id") long id) throws URISyntaxException, LogException
    {
        logger.debug("Call rest to delete  alert  : {}", id);
        Alert result = alertService.delete(id);

        return ResponseEntity.created(new URI("/delete"))
                .body(result);

    }

    @RequestMapping(value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> getAlertList(Pageable pageable) throws URISyntaxException, LogException
    {
        logger.debug("Call rest to get the list of   alert ");
        Page<Alert> page = alertRepository.findAll(pageable);
        List<AlertDto> alertDtos = page.getContent().stream()
                .map(al -> converter.fromAlertToBusiness(al))
                .collect(Collectors.toList());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/alert/list");
        return new ResponseEntity<>(alertDtos, headers, HttpStatus.OK);

    }

    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> getList(@RequestParam long id) throws URISyntaxException, LogException
    {
        logger.debug("Call rest to get the   alert  : {}",id);
        AlertDto result = alertService.getAlert(id);

        return ResponseEntity.created(new URI("alert/get"))
                .body(result);

    }

    @RequestMapping(value = "/list/date",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> getAlertList(@RequestParam("start")String start,@RequestParam("end")String end) throws URISyntaxException, LogException
    {
        logger.debug("Call rest to get the list of   alert between  {} and {}",start,end);
        List<Alert> result = alertService.getAlertListBetweenDates(start,end);

        return ResponseEntity.created(new URI("/alert/list/date"))
                .body(result);

    }


    @RequestMapping(value = "/statistic",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> getAlertListForStatistic() throws URISyntaxException, LogException
    {
        logger.debug("Call rest to get the list of   alert ");
        Map<LogLevel,Long> alertDtos = alertService.getAlertForStatistic() ;
        return new ResponseEntity<>(alertDtos,  HttpStatus.OK);

    }

}
