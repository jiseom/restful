package edu.bit.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.extern.log4j.Log4j;

//하나의 URI가 하나의 고유한 리소스를 대표하도록 설계된 개념

//http://localhost/spring02/list?bno=1 ==> url+파라미터
//http://localhost/spring02/list/1 ==> url
//RestController은 스프링 4.0부터 지원
//@Controller, @RestController 차이점은 메서드가 종료되면 화면전환의 유무

@Log4j
@RestController // (모든 메서드의 리턴타입을 기존과 다르게 처리한다는 것을 명시
@RequestMapping("/restful/*")
public class RestBoardController {

	@Autowired
	private BoardService boardService;

	// 1. list(처음 진입 화면이므로 화면이 깜박여도 상관없으므로 @Controller방식으로 접근 - veiw(화면)를 리턴
	@GetMapping("/board")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("rest/rest_list"); // restful/rest_list.jsp
		mav.addObject("list", boardService.getList());

		return mav;
	}
	
	// 레스트 컨트롤러 있으면 문법을 완전히 무시하고 스트링으로 넘어감
		//@GetMapping("/board")
		//public String list(Model model) {
			
			//mav.addObject("list", boardService.getList());

			//return mav;
		//}
	
	// 조회 URI 설계를 따로함 겟방식
		@GetMapping("/board/{bid}")
		public ModelAndView rest_content_view(BoardVO boardVO, ModelAndView mav) {
			mav.setViewName("rest/rest_content_view"); // restful/rest_list.jsp
			mav.addObject("content_view", boardService.get(boardVO.getBid()));

			return mav;
		}
		
		// restful 관련된 스프링 API
//		@RestController
//		@ResponseBody
//		@RequestBody
//		@PathVariable
//		ResponseEntity<String>
		
		
		
		// 수정: 대표적인 put /board/10 
		@PutMapping("/board/{bid}")
		public ResponseEntity<String> restupdate(@RequestBody BoardVO boardVO, ModelAndView mav) {
			
			log.info("restupdat()...");
			log.info("boardVO"+ boardVO);
			
			ResponseEntity<String> entity = null;
			
			try {
		         
		         boardService.modify(boardVO);
		         entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		         
		      } catch (Exception e) {
		         e.printStackTrace();
		         entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		      }   


			return entity;
		}
		
		@DeleteMapping("/board/{bid}")
		   public ResponseEntity<String> restDelete(@PathVariable("bid") int bid) {
		      
		      log.info("restDelete() ..");
		      log.info("bid..:" + bid);
		      
		      ResponseEntity<String> entity = null;
		      try {
		         
		         int rn = boardService.remove(bid);
		         log.info("delete result:" + rn);
		         // 삭제가 성공하면 성공 상태메시지 저장
		         entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		         
		      } catch (Exception e) {
		         e.printStackTrace();
		         // 삭제가 실패하면 실패 상태메시지 저장
		         entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		      }   
		      // 삭제 처리 HTTP 상태 메시지 리턴
		      return entity;
		   }

}