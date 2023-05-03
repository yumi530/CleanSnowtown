package com.project.smartclean.board.entity;

import com.project.smartclean.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
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
   private String writeName;
    //private String userId;

    private String filename;
    private String filepath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    @OrderBy("commentId asc")
    private List<Comment> comments;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private Member member;

//    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    @OrderBy("commentId asc")
//    private List<Comment> comments;

}
