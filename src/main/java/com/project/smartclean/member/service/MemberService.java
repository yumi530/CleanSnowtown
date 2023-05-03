package com.project.smartclean.member.service;

import com.project.smartclean.admin.dto.MemberDto;
import com.project.smartclean.admin.model.MemberParam;
import com.project.smartclean.member.entity.Member;
import com.project.smartclean.member.model.ResetPasswordInput;
import com.project.smartclean.member.model.ServiceResult;
import com.project.smartclean.member.model.SignUpForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {
    boolean register(SignUpForm parameter);
    boolean verify(String uuid);

    List<Member> list(MemberParam parameter);

    MemberDto detail(String userId);

    boolean updateStatus(String userId, String userStatus);

    boolean updatePassword(String userId, String password);

    boolean sendResetPassword(ResetPasswordInput parameter);

    boolean checkResetPassword(String uuid);

    boolean resetPassword(String id, String password);


//    void withdraw(SignUpForm parameter);


    ServiceResult withdraw(String userId, String password);

    ServiceResult updateMemberPassword(SignUpForm parameter);
}
