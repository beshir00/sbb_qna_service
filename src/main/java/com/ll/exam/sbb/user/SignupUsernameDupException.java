package com.ll.exam.sbb.user;

public class SignupUsernameDupException extends RuntimeException {
    public SignupUsernameDupException(String message) {
        super(message);
    }
}
