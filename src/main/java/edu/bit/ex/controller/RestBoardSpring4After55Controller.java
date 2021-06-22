package edu.bit.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.extern.log4j.Log4j;

//레스트 컨트롤러 어노테이션 추가

@Log4j
@RestController
public class RestBoardSpring4After55Controller {
   
   @Autowired
   private BoardService boardService;
   
   // 자바객체 리스트를 json으로 형태 바꾸어줌
   @GetMapping("/rest/{bid}") //변수화 시켜서 받으려면 패스 배리어블
   public BoardVO restNum(@PathVariable("bid") int bid) {
      log.info("/rest/restNum");
      
      return boardService.get(bid); // 객체 받아오는거
      
      
      
   }
   
}