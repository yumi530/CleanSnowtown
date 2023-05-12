package com.project.smartclean.board.service;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.entity.QBoard;
import com.project.smartclean.board.entity.Search;
import com.project.smartclean.board.repository.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public void insertBoard(Board board, MultipartFile file) throws IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        /*식별자 . 랜덤으로 이름 만들어줌*/
        UUID uuid = UUID.randomUUID();

        /*랜덤식별자_원래파일이름 = 저장될 파일이름 지정*/
        String fileName = uuid + "_" + file.getOriginalFilename();

        /*빈 껍데기 생성*/
        /*File을 생성할건데, 이름은 "name" 으로할거고, projectPath 라는 경로에 담긴다는 뜻*/
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        Board insertBoard = Board.builder()
                .boardNo(board.getBoardNo())
                .title(board.getTitle())
                .contents(board.getContents())
                .writeName(board.getWriteName())
//                .writeDate(LocalDateTime.now())
                .filename(fileName)
                .filepath("/files/" + fileName)
                .build();
        boardRepository.save(insertBoard);
    }

    @Override
    public Board readBoard(Long boardNo) {

        Board read = boardRepository.findById(boardNo).get();
        read.setCnt(read.getCnt());
        Board result = boardRepository.save(read);
        return result;
    }

    @Override
    public Board findById(Long boardNo) {
        Optional<Board> optionalBoard = boardRepository.findById(boardNo);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            return board;
        } else {
            return null;
        }
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
    public Board updateBoard(Board board) {
        Board findBoard = boardRepository.findById(board.getBoardNo()).get();
        findBoard.setBoardNo(board.getBoardNo());
        findBoard.setTitle(board.getTitle());
        findBoard.setWriteName(board.getWriteName());
        findBoard.setContents(board.getContents());
        findBoard.setCnt(board.getCnt());
        findBoard.setFilename(board.getFilename());
        findBoard.setFilepath(board.getFilepath());
//        findBoard.setUpdateDate(LocalDateTime.now());
        boardRepository.save(findBoard);
        return findBoard;
    }

    @Override
    public void deleteBoard(Board board) {
        boardRepository.deleteById(board.getBoardNo());
    }

    @Override
    public void updateView(Long boardNo) {
        boardRepository.updateViews(boardNo);
    }

    @Override
    public Page<Board> getBoardList(Search search, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QBoard qBoard = QBoard.board;
        if (search.getSearchCondition().equals("title")) {
            builder.and(qBoard.title.like("%" + search.getSearchKeyword() + "%"));
        } else if (search.getSearchCondition().equals("writeName")) {
            builder.and(qBoard.writeName.like("%" + search.getSearchKeyword() + "%"));
        } else if (search.getSearchCondition().equals("contents")) {
            builder.and(qBoard.contents.like("%" + search.getSearchKeyword() + "%"));
        }
        return boardRepository.findAll(builder, pageable);
    }
}
