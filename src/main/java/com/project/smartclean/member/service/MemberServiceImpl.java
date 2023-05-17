package com.project.smartclean.member.service;

import com.project.smartclean.admin.dto.MemberDto;
import com.project.smartclean.admin.model.MemberParam;
import com.project.smartclean.board.entity.QBoard;
import com.project.smartclean.common.utils.PasswordUtils;
import com.project.smartclean.components.MailComponents;
import com.project.smartclean.exception.MemberNotVerifiedException;
import com.project.smartclean.exception.MemberStopUserException;
import com.project.smartclean.member.entity.Member;
import com.project.smartclean.member.entity.MemberCode;
import com.project.smartclean.member.entity.QMember;
import com.project.smartclean.member.entity.Search;
import com.project.smartclean.member.model.ResetPasswordInput;
import com.project.smartclean.member.model.ServiceResult;
import com.project.smartclean.member.model.SignUpForm;
import com.project.smartclean.member.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    @Override
    public boolean register(SignUpForm parameter) {
//      Member member = memberRepository.findById(parameter.getUserId())
//               .orElseThrow(() -> new RuntimeException("이미 사용중인 ID 입니다."));
//       Member.builder()
//               .userId(parameter.getUserId())
//               .name(parameter.getName())
//               .birth(parameter.getBirth())
//               .password(parameter.getPassword())
//               .phone(parameter.getPhone())
//               .createdAt(LocalDateTime.now())
//               .build();
//       memberRepository.save(member);

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        if (optionalMember.isPresent()) {
            throw new RuntimeException("이미 사용중인 ID 입니다.");
        }

        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .name(parameter.getName())
                .birth(parameter.getBirth())
                .address1(parameter.getAddress1())
                .address2(parameter.getAddress2())
                .address3(parameter.getAddress3())
                .postcode(parameter.getPostcode())
                .password(encPassword)
                .phone(parameter.getPhone())
                .createdAt(LocalDateTime.now())
                .verifiedYn(false)
                .verifiedKey(uuid)
                .userStatus(Member.MEMBER_STATUS_REQ)
                .build();
        memberRepository.save(member);


        String email = parameter.getUserId();
        String subject = "[Clean! SnowTown] 회원가입 인증";
        String text = "[Clean! SnowTown] 다음 링크를 클릭하셔서 가입을 완료 하세요."
                + "href='http://localhost:8080/member/verify?id=" + uuid;
        mailComponents.sendMail(email, subject, text);

        return true;
    }

    @Override
    public boolean verify(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByVerifiedKey(uuid);
        Member member = optionalMember.get();

        if (member.isVerifiedYn()) {
            return false;
        }
        member.setVerifiedYn(true);
        member.setVerifiedAt(LocalDateTime.now());
        member.setUserStatus(Member.MEMBER_STATUS_ING);
        memberRepository.save(member);

        return true;
    }

    @Override
    public List<Member> list(MemberParam parameter) {
        return memberRepository.findAll();
    }

    @Override
    public MemberDto detail(String userId) {
        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (!optionalMember.isPresent()) {
            return null;
        }
        Member member = optionalMember.get();

        return MemberDto.of(member);
    }

    @Override
    public boolean updateStatus(String userId, String userStatus) {
        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }
        Member member = optionalMember.get();

        member.setUserStatus(userStatus);
        memberRepository.save(member);

        return true;
    }

    @Override
    public boolean updatePassword(String userId, String password) {
        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }
        Member member = optionalMember.get();
        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        member.setPassword(encPassword);
        memberRepository.save(member);

        return true;
    }

    @Override
    public boolean sendResetPassword(ResetPasswordInput parameter) {
        Optional<Member> optionalMember = memberRepository.findByUserIdAndName(parameter.getUserId(), parameter.getName());
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }
        Member member = optionalMember.get();

        String uuid = UUID.randomUUID().toString();

        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusDays(1));
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "[Clean! SnowTown] 비밀번호 초기화 메일입니다.";
        String text = "[Clean! SnowTown]" +
                "아래 링크를 클릭하셔서 비밀번호를 초기화 해주세요" +
                "href = 'http://localhost:8080/member/reset/password?id=" + uuid;
        mailComponents.sendMail(email, subject, text);

        return false;
    }

    @Override
    public boolean checkResetPassword(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
        if (!optionalMember.isPresent()) {
            return false;
        }
        Member member = optionalMember.get();

        if (member.getResetPasswordLimitDt() == null) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }
        if (member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }
        return true;
    }

    @Override
    public boolean resetPassword(String uuid, String password) {
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
        if (!optionalMember.isPresent()) {
            throw new MemberNotVerifiedException("회원 정보가 존재하지 않습니다.");
        }
        Member member = optionalMember.get();

        if (member.getResetPasswordLimitDt() == null) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }
        if (member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        member.setPassword(encPassword);
        member.setResetPasswordKey("");
        member.setResetPasswordLimitDt(null);
        memberRepository.save(member);

        return true;
    }

    @Override
    public ServiceResult withdraw(String userId, String password) {

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (!optionalMember.isPresent()) {
            return new ServiceResult(false, "회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if (!PasswordUtils.equals(password, member.getPassword())) {
            return new ServiceResult(false, "비밀번호가 일치하지 않습니다.");
        }

        member.setName("삭제회원");
        member.setPhone("");
        member.setPassword("");
        member.setCreatedAt(null);
        member.setModifiedAt(null);
        member.setVerifiedYn(false);
        member.setVerifiedAt(null);
        member.setVerifiedKey("");
        member.setResetPasswordKey("");
        member.setResetPasswordLimitDt(null);
        member.setUserStatus(Member.MEMBER_STATUS_WITHDRAW);
        member.setAddress1("");
        member.setAddress2("");
        member.setAddress3("");
        memberRepository.save(member);

        return new ServiceResult();
    }

    @Override
    public ServiceResult updateMemberPassword(SignUpForm parameter) {
        String userId = parameter.getUserId();

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (!optionalMember.isPresent()) {
            return new ServiceResult(false, "회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if (!PasswordUtils.equals(parameter.getPassword(), member.getPassword())) {
            return new ServiceResult(false, "비밀번호가 일치하지 않습니다.");
        }

        String encPassword = PasswordUtils.encPassword(parameter.getNewPassword());
        member.setPassword(encPassword);
        memberRepository.save(member);

        return new ServiceResult(true);
    }

    @Override
    public Page<Member> list(Search search, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QMember qMember = QMember.member;
        if (search.getSearchCondition().equals("userId")) {
            builder.and(qMember.userId.like("%" + search.getSearchKeyword() + "%"));
        } else if (search.getSearchCondition().equals("name")) {
            builder.and(qMember.name.like("%" + search.getSearchKeyword() + "%"));
        }else if (search.getSearchCondition().equals("phone")) {
            builder.and(qMember.phone.like("%" + search.getSearchKeyword() + "%"));
        }

        return memberRepository.findAll(builder, pageable);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findById(username);
        if (!optionalMember.isPresent())
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        Member member = optionalMember.get();

        if (Member.MEMBER_STATUS_REQ.equals(member.getUserStatus())) {
            throw new MemberNotVerifiedException("이메일 활성화 이후에 로그인을 해주세요.");
        }
        if (Member.MEMBER_STATUS_STOP.equals(member.getUserStatus())) {
            throw new MemberStopUserException("정지된 회원 입니다.");
        }
        if (Member.MEMBER_STATUS_WITHDRAW.equals(member.getUserStatus())) {
            throw new MemberStopUserException("탈퇴된 회원 입니다.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (member.isAdminYn()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }



        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);

    }

}
