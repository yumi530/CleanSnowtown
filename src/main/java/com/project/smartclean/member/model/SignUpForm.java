package com.project.smartclean.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpForm {
    @NotBlank(message = "아이디를 입력해주세요")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String userId;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Min(8)
    @Max(16)
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요")
    @Min(8)
    @Max(16)
    private String checkPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "생년월일을 입력해주세요")
    private Date birth;

    @NotBlank
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대전화번호를 입력해주세요")
    private String phone;

    private LocalDateTime createdAt;
    private boolean verifiedYn;
    @NotBlank(message = "주소를 입력해주세요")
    private String address1;
    @NotBlank(message = "주소를 입력해주세요")
    private String address2;
    @NotBlank(message = "주소를 입력해주세요")
    private String address3;
    @NotBlank(message = "우편번호를 입력해주세요")
    private String postcode;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String newPassword;
}
