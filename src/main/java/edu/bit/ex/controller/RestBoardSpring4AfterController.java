package edu.bit.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.extern.log4j.Log4j;

//레스트 컨트롤러 어노테이션 추가

@Log4j
@RestController // (모든 메서드의 리턴타입을 기존과 다르게 처리한다는 것을 명시
// json 형식으로 데이터를 응답하는 방법
// 기존의 컨트롤러는 쓰지 않는다
public class RestBoardSpring4AfterController {
   
   @Autowired
   private BoardService boardService;
   
   // 자바객체 리스트를 json으로 형태 바꾸어줌
   @GetMapping("/rest/after") //예전엔 스트링 붙여서 햇는데 이제는 객체 자체를 리턴시킴(리스트 가져와서) 제이슨으로 변환
   public List<BoardVO> restBefore( Model model) {
      log.info("restAfter()..");
      
      List<BoardVO> list = boardService.getList();
      
      model.addAttribute("list",list);
      // 스트링 형태의 뷰를 리턴하는게 아닌 객체를 리턴한다
      return list;
   }
   
}