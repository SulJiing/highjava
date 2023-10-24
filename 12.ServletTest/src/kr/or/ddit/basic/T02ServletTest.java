package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02ServletTest extends HttpServlet {
/*
 	서블릿 동작 방식에 대하여...
 	
 	1. 사용자(클라이언트)가 URL을 클릭하면 Http Request를 서블릿 컨테이너로 전송(요청)한다.
 	2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
 		(로딩이 안된 경우에는 로딩처리를 함. 로딩시 init()메서드가 호출됨.)
 	3. 서블릿 컨테이너는 요청을 처리할 개별 쓰레드 객체를 생성하여 해당 서블릿 객체의 service()메서드를 호출한다.
 		(HttpServletRequest 및 HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다.)
 	4. service() 메서드는 메서드 타입을 체크하여 적절한 메서드를 호출한다.(doGet, doPost, doPut 등)
 	5. 요청처리가 완료되면 HttpServletRequest 및 HttpServletResponse 객체는 소멸된다.
 	6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy() 메서드가 호출된다.
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청 객체의 메서드
		System.out.println("getCharacterEncoding() : "+req.getCharacterEncoding());
		System.out.println("getContentLength() : "+req.getContentLength());
		System.out.println("getQueryString() : "+req.getQueryString());
		System.out.println("getProtocol() : "+req.getProtocol());
		System.out.println("getRequestURL() : "+req.getRequestURL());
		System.out.println("getRemoteAddr() : "+req.getRemoteAddr());
		System.out.println("getRemotePort() : "+req.getRemotePort());
		
		// POST방식으로 넘어오는 Body데이터를 인코딩 처리함. GET방식인 경우 톰캣이 자동으로 인코딩 처리함.
		// 요청객체로부터 값을 가져오기 전에 먼저 설정해야 적용됨.
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name"); // 클라이언트가 서버쪽으로 던져주는 정보를 꺼내오는 것
		
		System.out.println("name => "+name);
		
		// 요청 객체에 정보 저장하기
		req.setAttribute("tel", "1111-1111");
		req.setAttribute("addr", "대전시 중구 오류동");
		
		// 요청 객체에 저장된 정보 꺼내기
		System.out.println("tel => " + req.getAttribute("tel")); // setAttribute로 Request객체 안에 저장해둔 값을 꺼내오는 것
		System.out.println("addr => " + req.getAttribute("addr"));
		
		// 응답 메세지 생성하기(응답 객체 이용)...
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
//		resp.setHeader("Content-Type", "text/plain");
		
		// 실제 응답 메세지를 생성하는 부분...(문자기발 출력을 위함)
		PrintWriter out = resp.getWriter();
		
		out.println("name => "+name);
		out.println("서블릿 경로 => "+req.getServletPath());
		out.println("컨테스트 경로 => "+req.getContextPath());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
