package com.project.smartclean.admin.model;

import lombok.Data;

@Data
public class MemberInput {
    String userId;
    String userStatus;
    String password;
}