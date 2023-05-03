package com.project.smartclean.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.smartclean.order.entity.UserOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Member implements MemberCode {
    @Id
//    @GeneratedValue(strategy = IDENTITY)
    private String userId;

    private String name;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime verifiedAt;
    private String verifiedKey;
    private boolean verifiedYn;
    private boolean adminYn;
    private String roles;
    private String userStatus;
    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;
    private String address1;
    private String address2;
    private String address3;
    private String postcode;

    private int seq;

    @OneToMany(mappedBy = "member")
    private List<UserOrder> userOrders = new ArrayList<>();




}
