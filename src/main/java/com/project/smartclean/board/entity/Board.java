package com.project.smartclean.board.entity;

import com.project.smartclean.member.entity.Member;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString(exclude = "member")
public class Board {
    @Id
    @GeneratedValue
    private Long boardNo;

    private String title;
    private String contents;
    private int cnt;
    private LocalDateTime writeDate;

    private LocalDateTime updateDate;
    //  @GeneratedValue(strategy = IDENTITY)

    private String filename;
    private String filepath;

    @OneToMany(mappedBy = "board" ,cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("commentId asc")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

//    public String getWriteName() {
//        return this.member.getName();
//    }

}
