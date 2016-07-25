package com.log.service;

import com.log.config.Constants;
import com.log.domain.*;
import com.log.exception.LogException;
import com.log.repository.AlertRepository;
import com.log.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AlertService
{

    private Logger logger = LoggerFactory.getLogger(AlertService.class);

    @Inject
    private AlertRepository alertRepository;

    @Inject
    private CategoryRepository categoryRepository;

    public Alert createAlert(String line) throws LogException
    {
        return readFile(line);
    }

    private Alert readFile(String line) throws LogException
    {
        String logEntryPattern = "(.*)\\s([\\d-]+\\s[\\d:]+)\\s([\\w]+)\\s+:\\s(.+)";

        Pattern p = Pattern.compile(logEntryPattern);
        Matcher matcher = p.matcher(line);
        if (!matcher.matches())
        {
            throw new LogException("The lines of file didn't match the pattern expected");
        }
        String machine = matcher.group(1);
        LocalDateTime fullDate = LocalDateTime.parse(matcher.group(2), Constants.LocalDateTimeFormatter);
        LocalDate date = fullDate.toLocalDate();
        String time = fullDate.toLocalTime().format(Constants.LocalTimeFormatter);
        LogLevel criticite = LogLevel.fromString(matcher.group(3));
        String message = matcher.group(4);
        Category category = categoryRepository.findByCriticite(criticite)
                .map(cat -> {
                    return cat;
                })
                .orElseGet(null);
        return createAlert(machine, time, date, message, 0, category);


    }

    private Alert createAlert(String machine, String time, LocalDate date, String message, int level, Category category)
    {

        Alert alert = new Alert();
        alert.setMachine(machine);
        alert.setDate(date);
        alert.setHour(time);
        alert.setMessage(message);
        alert.setLevel(level);
        alert.setCategory(category);
        return alert;
    }

    public List<Alert> getAlertList()
    {
        return alertRepository.findAll();
    }

    public Alert delete(long id) throws LogException
    {
        return alertRepository.findOneById(id)
                .map(emp -> {
                    alertRepository.delete(emp);
                    return emp;
                })
                .orElseThrow(() -> new LogException("the alert with id " + id + " not found"));


    }
}
