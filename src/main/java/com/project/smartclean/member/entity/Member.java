package com.project.smartclean.member.entity;

import com.project.smartclean.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Member implements MemberCode, UserDetails {
    @Id
    private String userId;
    private String name;
    private String password;
    private String checkPassword;
    private String phone;
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


//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Notice> notices = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return isAdminYn()
                ? List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))
                :  List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
