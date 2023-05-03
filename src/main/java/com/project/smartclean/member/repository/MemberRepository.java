package com.project.smartclean.member.repository;

import com.project.smartclean.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
    Optional<Member> findByVerifiedKey(String verifiedKey);
    Optional<Member> findByUserIdAndName(String userId, String name);
    Optional<Member> findByResetPasswordKey(String resetPasswordKey);

    void deleteById(String userId);
}
