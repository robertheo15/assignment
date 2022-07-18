package com.example.demo.authentication.service;

import com.example.demo.authentication.model.entity.User;
import common.base.service.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends BaseService<User>, UserDetailsService {

    User findByEmail(String email);
}
