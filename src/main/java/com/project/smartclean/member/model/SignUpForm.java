package com.project.smartclean.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpForm {
    private String userId;
    private String name;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;

    private LocalDateTime createdAt;
    private boolean verifiedYn;
    private String address1;
    private String address2;
    private String address3;
    private String postcode;

    private String newPassword;
}
