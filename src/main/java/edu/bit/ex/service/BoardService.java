package edu.bit.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.bit.ex.vo.BoardVO;

public interface BoardService {
	List<BoardVO> getList();
	
	int remove(int bid);

	BoardVO get(int bid);
	
    void modify(BoardVO boardVO);
	
}
