package com.project.smartclean.board.service;

import com.project.smartclean.board.entity.Board;
import com.project.smartclean.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    @Override
    public List<Board> boardList(Board board) {
        return boardRepository.findAll();
    }

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
                .writeDate(LocalDateTime.now())
                 .filename(fileName)
                 .filepath("/files/"+fileName)
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
    public void updateBoard(Board board) {
        Board findBoard = boardRepository.findById(board.getBoardNo()).get();

        findBoard.setTitle(board.getTitle());
        findBoard.setContents(board.getContents());
        findBoard.setUpdateDate(LocalDateTime.now());
        boardRepository.save(findBoard);
    }

    @Override
    public void deleteBoard(Board board) {
        boardRepository.deleteById(board.getBoardNo());
    }

    @Override
    public void updateView(Long boardNo) {
        boardRepository.updateViews(boardNo);
    }
}
