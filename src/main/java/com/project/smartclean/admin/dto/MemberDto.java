package com.project.smartclean.admin.dto;

import com.project.smartclean.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {
    private String userId;
    private String name;
    private String phone;
    private Date birth;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean verifiedYn;
    private LocalDateTime verifiedAt;
    private String verifiedKey;
    String userStatus;
    private boolean adminYn;
    private boolean centerYn;
    private String roles;
    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;
    private String address1;
    private String address2;
    private String address3;
    private String postcode;
    private long seq;

    public static MemberDto of(Member member) {

        return MemberDto.builder()
                .userId(member.getUserId())
                .name(member.getName())
                .phone(member.getPhone())
                .birth(member.getBirth())
                .password(member.getPassword())
                .address1(member.getAddress1())
                .address2(member.getAddress2())
                .address3(member.getAddress3())
                .postcode(member.getPostcode())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .verifiedYn(member.isVerifiedYn())
                .verifiedAt(member.getVerifiedAt())
                .verifiedKey(member.getVerifiedKey())
                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                .adminYn(member.isAdminYn())
                .centerYn(member.isCenterYn())
                .userStatus(member.getUserStatus())
                //.seq(member.getSeq())
                .build();
    }
}