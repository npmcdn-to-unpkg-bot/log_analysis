package com.log.service;


import com.log.domain.Authority;
import com.log.domain.Employe;
import com.log.exception.LogException;
import com.log.repository.AuthorityRepository;
import com.log.repository.EmployeRepository;
import com.log.security.SecurityUtils;
import com.log.utils.BusinessAndDomainConverter;
import com.log.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserService
{

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Inject
    private EmployeRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private AuthorityRepository authorityRepository;

    @Inject
    private BusinessAndDomainConverter converter ;

    @Inject
    private RestTemplate restTemplate;

    public Employe create(UserDto userDto)
    {

        userRepository.findOneByUsername(userDto.getUsername())
                .ifPresent(us -> new Exception("the user name" + us.getUsername() + " is already in user"));
        Employe employe = converter.fromBusinessToUser(userDto);

        employe.setPassword(passwordEncoder.encode(employe.getPassword()));
        Optional<Authority> authority = authorityRepository.findOneByName("ROLE_USER");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority.get());
        employe.setAuthorityList(authorities);
        userRepository.save(employe);
        return employe;

    }

    public Employe update(UserDto userDto) throws LogException
    {


        return userRepository.findOneById(userDto.getId())
                .map(emp -> {
                    Employe newEmploye = converter.fromBusinessToUser(userDto);
                    Optional.ofNullable(newEmploye.getPassword())
                            .ifPresent(pass -> newEmploye.setPassword(passwordEncoder.encode(newEmploye.getPassword()))) ;
                    Optional<Authority> authority = authorityRepository.findOneByName("ROLE_USER");
                    Set<Authority> authorities = new HashSet<>();
                    authorities.add(authority.get());
                    newEmploye.setId(emp.getId());
                    newEmploye.setAuthorityList(authorities);
                    userRepository.delete(emp);
                    userRepository.save(newEmploye) ;
                    return  newEmploye ;
                })
                .orElseThrow(() -> new LogException("the user with id " + userDto.getId() + " not found"));


    }

    public Employe delete(long id) throws LogException
    {
        return userRepository.findOneById(id)
                .map(emp -> {
                    userRepository.delete(emp);
                    return  emp ;
                })
                .orElseThrow(() -> new LogException("the user with id " + id + " not found"));


    }

    public List<Employe> getList() throws LogException
    {
        return userRepository.findAll();


    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities() throws Exception
    {
        return userRepository.findOneByUsername(SecurityUtils.getCurrentUserLogin()).filter(Employe::isEnabled)
                .map(us -> {
                    us.getAuthorities().size();
                    return converter.fromUserToBusiness(us);
                }).orElseThrow(() -> new Exception("user not found "));

    }

    public Object authenticate(String userName, String password, HttpServletRequest request)
    {

        String baseUrl = request.getScheme() + // "http"
                "://" +                                // "://"
                request.getServerName() +              // "myhost"
                ":" +                                  // ":"
                request.getServerPort() +              // "80"
                request.getContextPath();              // "/myContextPath" or "" if deployed in root context

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("client_secret", "client_secret");
        map.add("client_id", "client_id");
        map.add("grant_type", "password");
        map.add("scope", "read");
        map.add("username", userName);
        map.add("password", password);

        Object result = restTemplate.postForObject(baseUrl + "/oauth/token", map, Object.class);
        return result;
    }
}
