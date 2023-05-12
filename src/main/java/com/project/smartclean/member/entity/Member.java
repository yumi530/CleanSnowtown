package com.project.smartclean.member.entity;

import com.project.smartclean.notice.entity.Notice;
import com.project.smartclean.order.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "member")
public class Member implements MemberCode  {
    @Id
    private String userId;
    private String name;
    private String password;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private boolean adminYn;
    private boolean centerYn;
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


//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Notice> notices = new ArrayList<>();

    public Member(String username) {
        this.userId = username;
    }


}
