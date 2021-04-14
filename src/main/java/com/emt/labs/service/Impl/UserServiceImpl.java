package com.emt.labs.service.Impl;

import com.emt.labs.model.Role;
import com.emt.labs.model.User;
import com.emt.labs.model.dto.JwtResponseDto;
import com.emt.labs.model.dto.LoginDto;
import com.emt.labs.model.dto.RegisterDto;
import com.emt.labs.model.exceptions.UserAlreadyExistsException;
import com.emt.labs.repository.RoleRepository;
import com.emt.labs.repository.UserRepository;
import com.emt.labs.security.jwt.JwtUtils;
import com.emt.labs.security.services.UserDetailsImpl;
import com.emt.labs.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User findById(String username) {
        return this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public JwtResponseDto signInUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        User user = this.findById(loginDto.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        jwtResponseDto.setAccessToken(jwt);
        jwtResponseDto.setUsername(userDetails.getUsername());
        jwtResponseDto.setRoles(roles);

        return jwtResponseDto;
    }

    @Override
    public void signUpUser(RegisterDto registerDto) {
        if (this.userRepository.findById(registerDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(registerDto.getUsername());
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role role = this.roleRepository.findAll().stream().
                filter(r -> r.getRole().name().equals(registerDto.getRole())).findFirst().get();
        user.setRole(role);

        this.userRepository.save(user);
    }
}
