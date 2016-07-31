package com.log.utils;


import com.log.domain.Alert;
import com.log.domain.Authority;
import com.log.domain.Employe;
import com.log.repository.AuthorityRepository;
import com.log.web.dto.AlertDto;
import com.log.web.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.stream.Collectors;


@Component
public class BusinessAndDomainConverter
{

    @Inject
    private AuthorityRepository authorityRepository ;

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    @Inject
    private ModelMapper modelMapper;

    public UserDto fromUserToBusiness(Employe user)
    {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setAuthorities(user.getAuthorityList().stream().map(Authority::getName)
                .collect(Collectors.toSet()));
        return userDto;
    }

    public AlertDto fromAlertToBusiness(Alert alert)
    {
        AlertDto alertDto = modelMapper.map(alert, AlertDto.class);
        alertDto.setCriticite(alert.getCategory().getCriticite());
        alertDto.setLogFileName(alert.getLogFile().getName());
        return alertDto;
    }

    public Employe fromBusinessToUser(UserDto userDto)
    {
        Employe employe = modelMapper.map(userDto, Employe.class);
        employe.setAuthorityList(userDto.getAuthorities().stream().map(aut -> authorityRepository.findOneByName(aut).get()
        ).collect(Collectors.toSet()));
        return  employe ;

    }

}
