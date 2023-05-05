package com.project.smartclean.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "member")
public class Member implements MemberCode {
    @Id
    private String userId;
    private String name;
    private String password;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private boolean adminYn;
    private String address1;
    private String address2;
    private String address3;
    private String postcode;
    private String roles;
    private String userStatus;
    private LocalDateTime verifiedAt;
    private boolean verifiedYn;
    private String verifiedKey;
    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int seq;

//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();

}
