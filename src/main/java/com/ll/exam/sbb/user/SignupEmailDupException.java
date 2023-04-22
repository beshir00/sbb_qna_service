package com.ll.exam.sbb.user;

public class SignupEmailDupException extends RuntimeException {
    public SignupEmailDupException(String message) {
        super(message);
    }
}
