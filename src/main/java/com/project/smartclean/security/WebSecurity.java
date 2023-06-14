package com.project.smartclean.security;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("webSecurity")
@RequiredArgsConstructor
public class WebSecurity {
    private final BoardRepository boardRepository;

    public boolean checkAuthority(Authentication authentication, Long boardNo) {
        Optional<Board> boards = boardRepository.findById(boardNo);
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                || boards.get().getMember().getUserId().equals(authentication.getName())) {
            return true;
        } else {
            return false;
        }
    }
}
