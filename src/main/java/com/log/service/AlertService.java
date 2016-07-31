package com.log.service;

import com.log.config.Constants;
import com.log.domain.*;
import com.log.exception.LogException;
import com.log.repository.AlertRepository;
import com.log.repository.CategoryRepository;
import com.log.utils.BusinessAndDomainConverter;
import com.log.web.dto.AlertDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AlertService
{

    private Logger logger = LoggerFactory.getLogger(AlertService.class);

    @Inject
    private AlertRepository alertRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private BusinessAndDomainConverter converter;

    public Alert createAlert(String line, LogFile logFile) throws LogException
    {
        return readFile(line, logFile);
    }

    private Alert readFile(String line, LogFile logFile) throws LogException
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
        int level;
        Category category = categoryRepository.findByCriticite(criticite)
                .map(cat -> {
                    return cat;
                })
                .orElseGet(null);
        if (category == null)
        {
            level = 6;
        }
        else
        {
            switch (category.getCriticite())
            {
                case DEBUG:
                    level = 6;
                    break;
                case TRACE:
                    level = 5;
                    break;
                case INFO:
                    level = 5;
                    break;
                case WARN:
                    level = 3;
                    break;
                case ERROR:
                    level = 2;
                    break;
                case SEVERE:
                    level = 1;
                    break;
                default:
                    level = 6;
                    break;
            }
        }
        return createAlert(machine, time, date, message, level, category, logFile);


    }

    private Alert createAlert(String machine, String time, LocalDate date, String message, int level, Category category, LogFile logFile)
    {

        Alert alert = new Alert();
        alert.setMachine(machine);
        alert.setDate(date);
        alert.setHour(time);
        alert.setMessage(message);
        alert.setLevel(level);
        alert.setCategory(category);
        alert.setLogFile(logFile);
        return alertRepository.save(alert);
    }

    public List<AlertDto> getAlertList()
    {
        return alertRepository.findAll().stream()
                .map(al -> converter.fromAlertToBusiness(al))
                .collect(Collectors.toList());
    }


    public AlertDto getAlert(long id) throws LogException
    {
        return alertRepository.findOneById(id)
                .map(al -> converter.fromAlertToBusiness(al))
                .orElseThrow(() -> new LogException("Alert with " + id + "  not found"));

    }

    public List<Alert> getAlertListBetweenDates(String start, String end)
    {
        return alertRepository.findByDateBetween(start, end);
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
