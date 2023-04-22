package com.ll.exam.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) throws SignupEmailDupException, SignupUsernameDupException {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));


        try {userRepository.save(user);}
        catch (DataIntegrityViolationException e){
            if (userRepository.existsByUsername(username)){
                throw new SignupUsernameDupException("이미 사용중인 아이디 입니다.");
            }else throw new SignupEmailDupException("이미 사용중인 이메일 입니다.");
        }

        return user;
    }
}
