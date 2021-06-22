<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax(제이쿼리 함수임)</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">

function getList(){
	
	/*    $.ajax({
    url : '서비스 주소'
    , data : '서비스 처리에 필요한 인자값'
    , type : 'HTTP방식' (POST/GET 등)
    , dataType : 'return 받을 데이터 타입' (json, text 등)
    , success : function('결과값'){
    // 서비스 성공 시 처리 할 내용
    }, error : function('결과값'){
    // 서비스 실패 시 처리 할 내용
    }
 }); */

	
	var url = "${pageContext.request.contextPath}/rest/after.json";//이거 치고 들어오면 컨트롤러 리스트로 리턴해라
	$.ajax({
		type: 'GET',
		url: url,
		cache: false, // 이걸 안쓰거나 true 하면 수정해도 값반영이 잘 안됌
		dataType: 'json', // 데이터타입을 제이슨 꼭해야함
		success: function(result){
			var htmls = "";
			
			$("#list_table").html("");
			
			$("<tr>", {
				  html : "<td>" + "번호" + "</td>"+  // 컬럼명들
                  "<td>" + "이름" + "</td>"+
                  "<td>" + "제목" + "</td>"+
                  "<td>" + "날짜" + "</td>"+            
                  "<td>" + "히트" + "</td>"+
                  "<td>" + "삭제" + "</td>"
				
			}).appendTo("#list_table") // 이것을 테이블에 붙임
			
			if(result.length < 1){
				htmls.push("등록된 댓글이 없습니다.");
			}else{
				// 객체 자체기때문에 따옴표 붙이면 안된다 붙이면 선택자 됨
				 $(result).each(function(){
                     
                     htmls += '<tr>';
                     htmls += '<td>'+ this.bid + '</td>';
                     htmls += '<td>'+ this.bname + '</td>';
                     htmls += '<td>'
                   for(var i=0; i < this.bindent; i++) { //for 문은 시작하는 숫자와 종료되는 숫자를 적고 증가되는 값을 적어요. i++ 은 1씩 증가 i+2 는 2씩 증가^^
                      htmls += '-'   
                  }
                     htmls += '<a href="${pageContext.request.contextPath}/content_view?bId=' + this.bid + '">' + this.btitle + '</a></td>';
                        htmls += '<td>'+ this.bdate + '</td>'; 
                     htmls += '<td>'+ this.bhit + '</td>';
                     htmls += '<td>'
                         for(var i=0; i < this.bindent; i++) { //for 문은 시작하는 숫자와 종료되는 숫자를 적고 증가되는 값을 적어요. i++ 은 1씩 증가 i+2 는 2씩 증가^^
                            htmls += '-'   
                        }
                     htmls += '<a class="a-delete" href="${pageContext.request.contextPath}/ajax/delete?bid=' + this.bid + '">'+'삭제'+'</a></td>';
                     htmls += '</tr>';
                     
                 });   //each end
				 htmls+='<tr>';
                 htmls+='<td colspan="5"> <a href="${pageContext.request.contextPath}/write_view">글작성</a> </td>';                         
                 htmls+='</tr>';

			}
			$("#list_table").append(htmls);

		}
		
		
	}); //Ajax end
	
}// end getList

</script>
   <script>
      $(document).ready(function(){
         getList();
      });
   </script>
   
    <script>
      $(document).ready(function(){
         $(document).on("click",".a-delete",function(event){
            //prevendDefault()는 href로 연결해 주지 않고 단순히 click에 대한 처리를 하도록 해준다.
            event.preventDefault();
            console.log("ajax 호출전"); 
            //해당 tr제거
            var trObj =  $(this).parent().parent();
            
            $.ajax({
                type : "GET",
                url : $(this).attr("href"),
                success: function (result) {       
                    console.log(result); 
                  if(result == "SUCCESS"){
                       //getList();
                     $(trObj).remove();  
                               
                  }                       
                },
                error: function (e) {
                    console.log(e);
                }
            })
             
         });   
      
      });
   </script>
   
   
</head>
<body>
   <table id= "list_table" width="500" cellpadding="0" cellspacing="0" border="1">
      
      
         
   </table>
</body>
</html>