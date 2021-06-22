package edu.bit.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.extern.log4j.Log4j;

//레스트 컨트롤러 어노테이션 추가

@Log4j
@Controller
@RequestMapping("/ajax/*")
public class AjaxBoardController {
	
	@Autowired
	private BoardService boardService;
   
   @GetMapping("/list")
   public String ajaxList( Model model) {
      log.info("ajaxList()..");
      
            
      return "ajax/ajaxList";
   }
   
   @ResponseBody // 스트링값이 고대로 ajax result 값이 됨
   @GetMapping("/delete")
   public String ajaxDelete(BoardVO boardVO) { // 넘기는거 필요 없음
      log.info("ajaxDelete()..");
      log.info(boardVO);
      
      boardService.remove(boardVO.getBid());
      
            
      return "SUCCESS";
   }
 
   

}