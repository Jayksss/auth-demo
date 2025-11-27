package com.example.authdemo.service;

import com.example.authdemo.domain.User;
import com.example.authdemo.dto.RegisterRequest;
import com.example.authdemo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByLoginId(request.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User user = new User();
        user.setLoginId(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPwdHash(passwordEncoder.encode(request.getPassword()));
        user.setUserNm(request.getUsername()); // 임시로 아이디를 이름으로 저장

        userRepository.save(user);
    }
}
