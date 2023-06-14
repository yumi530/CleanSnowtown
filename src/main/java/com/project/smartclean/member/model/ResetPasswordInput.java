package com.project.smartclean.member.model;

import lombok.Data;

@Data
public class ResetPasswordInput {
    private String userId;
    private String name;

    private String password;
    private String id;
}
