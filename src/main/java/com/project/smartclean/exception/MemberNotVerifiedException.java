package com.project.smartclean.exception;

public class MemberNotVerifiedException extends RuntimeException {
    public MemberNotVerifiedException(String error) {
        super(error);
    }
}
