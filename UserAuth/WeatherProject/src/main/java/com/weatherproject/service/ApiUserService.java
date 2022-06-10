package com.weatherproject.service;

import com.weatherproject.domain.ApiUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ApiUserService {

    ApiUser saveUser(ApiUser apiUser);
    ApiUser getUser(Long id);

    ApiUser getUser(String username);
    List<ApiUser> getUsers();
    ApiUser updateUser(ApiUser apiUser);
    Boolean deleteUser(Long id);
}
