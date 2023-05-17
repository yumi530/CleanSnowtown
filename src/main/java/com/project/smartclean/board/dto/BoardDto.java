package com.project.smartclean.board.dto;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardDto {

    private Long boardNo;

    private String title;
    private String contents;
    private int cnt;
    private LocalDateTime writeDate;

    private LocalDateTime updateDate;

    private Member writeName;

//    public void writerName(String writeName) {
//        this.writeName = member.getUserId();
//    }

    public String filename;
    private String filepath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "write_name")
    private Member member;


//
//    public static BoardDto of(Board board) {
//
//        board.setBoardNo(board.getBoardNo());
//        board.setWriteName(board.getMember().getName());
//        board.setWriteDate(LocalDateTime.now());
//        board.setTitle(board.getTitle());
//        board.setContents(board.getContents());
////        board.setCnt(0);
////        board.setFilename(board.getFilename());
////        board.setFilepath("/files/" + filename);
//        return BoardDto.of(board);
//    }
public Board toEntity() {
    return Board.builder()
            .boardNo(boardNo)
                .title(title)
                .contents(contents)
                .writeDate(LocalDateTime.now())
//                .writeName(member.getName())
//            .member(writeName)
                .cnt(0)
                .filename(filename)
                .filepath(filepath)
                .build();
}



}
