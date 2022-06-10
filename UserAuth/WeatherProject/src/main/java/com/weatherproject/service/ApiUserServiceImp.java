package com.weatherproject.service;

import com.weatherproject.domain.ApiUser;
import com.weatherproject.repository.ApiUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ApiUserServiceImp implements ApiUserService, UserDetailsService {

    private final ApiUserRepository apiUserRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ApiUser saveUser(ApiUser apiUser) {
        if (apiUserRepo.findByUsername(apiUser.getUsername()) != null)
            return null;
        log.info("Saving new user {}", apiUser.getUsername());
        apiUser.setPassword(passwordEncoder.encode(apiUser.getPassword()));
        return apiUserRepo.save(apiUser);
    }


    @Override
    public ApiUser getUser(Long id) {
        if (apiUserRepo.findById(id).isPresent())
            return apiUserRepo.findById(id).get();
        return null;
    }

    @Override
    public ApiUser getUser(String username) {
        return apiUserRepo.findByUsername(username);
    }

    @Override
    public List<ApiUser> getUsers() {
        log.info("Fetching all users");
        return apiUserRepo.findAll();
    }

    @Override
    public ApiUser updateUser(ApiUser apiUser) {
        if (apiUserRepo.findById(apiUser.getId()).isPresent()){
            apiUser.setPassword(passwordEncoder.encode(apiUser.getPassword()));
            return apiUserRepo.saveAndFlush(apiUser);
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (!apiUserRepo.findById(id).isPresent())
            return false;
        apiUserRepo.delete(apiUserRepo.findById(id).get());
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        ApiUser apiUser = apiUserRepo.findByUsername(username);
        if (apiUser == null){
            log.error("User not found in db");
            throw new UsernameNotFoundException("User not found in db");
        }
        else log.info("User found in db : {}", apiUser.getUsername());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(apiUser.getRole()));
        return new User(apiUser.getUsername(), apiUser.getPassword(),authorities);
    }



}
