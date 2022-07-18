package com.example.demo.authentication.service.impl;

import com.example.demo.authentication.model.entity.Role;
import com.example.demo.authentication.model.entity.User;
import com.example.demo.authentication.payload.ErrorDetails;
import com.example.demo.authentication.repository.AuthRepository;
import com.example.demo.authentication.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AuthRepository authRepository;

    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
        return authRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    @Override
    public User create(User entity) {
        return authRepository.save(entity);
    }

    @Override
    public User update(User oldEntity, User newEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Page<User> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = authRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
