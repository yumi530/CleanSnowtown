package com.project.smartclean.board.service;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.entity.QBoard;
import com.project.smartclean.board.entity.Search;
import com.project.smartclean.board.repository.BoardRepository;
import com.project.smartclean.member.entity.Member;
import com.project.smartclean.member.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;



@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public Board insertBoard(Board board, User user, MultipartFile file) throws IOException {
        String fileName = "";
        if (file != null) {
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
            UUID uuid = UUID.randomUUID();

            fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);
        }

        Member writeMember = memberRepository.findById(user.getUsername()).get();

        Board insertBoard = Board.builder()
                .boardNo(board.getBoardNo())
                .title(board.getTitle())
                .contents(board.getContents())
                .writeDate(LocalDateTime.now())
//                .writeName(writeMember)
//                .writeName(writeMember.getName())
                .cnt(0)
                .filename(fileName)
                .filepath("/files/" + fileName)
                .member(writeMember)
                .build();

        return boardRepository.save(insertBoard);



//        BoardDto insertBoard = BoardDto.builder()
//                .boardNo(boardDto.getBoardNo())
//                .title(boardDto.getTitle())
//                .contents(boardDto.getContents())
//                .writeDate(LocalDateTime.now())
//                .writeName(member)
//                .cnt(0)
//                .filename(fileName)
//                .filepath("/files/" + fileName)
//                .build();
//
//        return boardRepository.save(insertBoard.toEntity());

    }

    @Override
    public Board readBoard(Long boardNo) {
        Board board = boardRepository.findById(boardNo).get();
        board.setCnt(board.getCnt() + 1);
        boardRepository.save(board);
        return board;
    }

//    @Override
//    public Board readBoard(Board board, User user, Member member) {
//        if (board.getWriteName() == user.getUsername() || member.isAdminYn()) {
//            Board read = boardRepository.findById(board.getBoardNo()).get();
//            read.setCnt(read.getCnt());
//            Board result = boardRepository.save(read);
//            return result;
//        } return null;
//    }

    @Override
    public Board updateBoard(Board board, User user, MultipartFile file) throws IOException {
        Board findBoard = boardRepository.findById(board.getBoardNo()).get();

        String fileName = "";


        if (file != null) {
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID();


            fileName = uuid + "_" + file.getOriginalFilename();


            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);
        }

        findBoard.setBoardNo(board.getBoardNo());
        findBoard.setTitle(board.getTitle());
        findBoard.setContents(board.getContents());
        findBoard.setCnt(board.getCnt());
        findBoard.setFilename(fileName);
        findBoard.setFilepath("/files/" + fileName);
//        findBoard.setWriteName(user.getUsername()); // si
        findBoard.setUpdateDate(LocalDateTime.now());
        boardRepository.save(findBoard);
        return findBoard;
    }

    @Override
    public void deleteBoard(Board board) {
        boardRepository.deleteById(board.getBoardNo());
    }

    @Override
    public Page<Board> getBoardList(Search search, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        QBoard qBoard = QBoard.board;
        if (search.getSearchCondition().equals("title")) {
            builder.and(qBoard.title.like("%" + search.getSearchKeyword() + "%"));
        }
//        else if (search.getSearchCondition().equals("writeName")) {
//            builder.and(qBoard.writeName.like("%" + search.getSearchKeyword() + "%"));
//        }
        else if (search.getSearchCondition().equals("contents")) {
            builder.and(qBoard.contents.like("%" + search.getSearchKeyword() + "%"));
        }

        return boardRepository.findAll(builder, pageable);
    }

    @Override
    public Page<Board> getBoardListWithMember(Search search, Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}
