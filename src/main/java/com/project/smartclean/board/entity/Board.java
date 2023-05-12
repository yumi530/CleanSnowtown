package com.project.smartclean.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "member")
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

    //    @ManyToOne
//    @JoinColumn(name = "user_id")
    private String userId;

//    @OneToMany(mappedBy = "board" ,cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    @OrderBy("commentId asc")
//    private List<Comment> commentList = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private Member member;

//    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    @OrderBy("commentId asc")
//    private List<Comment> comments;

}
