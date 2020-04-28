package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.happyhouse.model.service.MemberServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService;
	//private GuestBookService guestBookService;

	public void init() {
		memberService = new MemberServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			process(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String root = request.getContextPath();
		String path = "/index.jsp";
		
		String act = request.getParameter("act");
		
		if("mvjoin".equals(act)) {
			path = "/user/join.jsp";
			redirect(response, path, root);
		} else if("mvlogin".equals(act)) {
			path = "/user/login.jsp";
			redirect(response, path, root);
		}else if("login".equals(act)) {
			login(request,response);
		}else if("logout".equals(act)) {
			logout(request,response);
		}else if("mvwrite".equals(act)) {
			path = "/guestbook/write.jsp";
			redirect(response, path, root);
		}else if("join".equals(act)) {
			join(request,response);
		}else if("mvmodify".equals(act)) {
			moveModify(request, response);
		}else if("modify".equals(act)) {
			modifyInfo(request, response); 
		}else if("delete".equals(act)) {
			delete(request,response);
		}else if("findpwd".equals(act)) {
			findpwd(request,response);
			System.out.println("findpwd");
		}else if("info".equals(act)) {
			path = "/user/info.jsp";
			redirect(response, path, root);
		}else if("index".equals(act)) {
			System.out.println("인덱스");
			redirect(response, path, root);
		}
	}


	private void findpwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String root = request.getContextPath();
		String path = "/index.jsp";
		
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String phone = tel1+"-"+tel2+"-"+tel3; 
		
		MemberDto memberDto;
		
		try {
			memberDto = memberService.getPwd(userid, username, phone);
			System.out.println("find>>>>>>>>>"+memberDto.getId());
			System.out.println("find>>>>>>>>>"+memberDto.getName());
			System.out.println("find>>>>>>>>>"+memberDto.getPassword());
			path = "/user/findsuccess.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("findinfo", memberDto);
			redirect(response, path, root);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "비밀번호 찾기 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			redirect(response, path, root);
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String root = request.getContextPath();
		String path = "/index.jsp";
		String id = request.getParameter("userid");
		System.out.println(id);
		
		try {
			memberService.delete(id);
			path = "/user/deletesuccess.jsp";
			HttpSession session = request.getSession();
			session.removeAttribute("userinfo"); // 세션 안에는 여러개 넣을 수 있다. 장바구니에 이것이 적절
			System.out.println(root + path);
			response.sendRedirect(request.getContextPath() + path);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원탈퇴 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			redirect(response, path, root);
		}
	}

	private void modifyInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String root = request.getContextPath();
		String path = "/index.jsp";
		MemberDto memberDto = new MemberDto();
		memberDto.setId(request.getParameter("userid"));
		System.out.println("modifyInfo : "+request.getParameter("userid"));
		memberDto.setPassword(request.getParameter("userpwd"));
		memberDto.setName(request.getParameter("username"));
		memberDto.setAddr(request.getParameter("address"));
		memberDto.setPhone(request.getParameter("tel1")+"-"+request.getParameter("tel2")+"-"+request.getParameter("tel3"));
		
		try {
			memberService.modifyInfo(memberDto);
			System.out.println("인포>>>>>>>>>"+memberDto.getId());
			System.out.println("인포>>>>>>>>>"+memberDto.getName());
			System.out.println("인포>>>>>>>>>"+memberDto.getAddr());
			System.out.println("인포>>>>>>>>>"+memberDto.getPhone());
			path = "/user/modifysuccess.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", memberDto);
			response.sendRedirect(request.getContextPath() + path);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원정보 수정 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			redirect(response, path, root);
		}
	}

	private void moveModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("userid");
		String path = "/index.jsp";
		System.out.println("무브>>>>>>>"+id);
		MemberDto memberDto;
		try {
			memberDto = memberService.getInfo(id);
			request.setAttribute("info",memberDto);
			path = "/user/modify.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 정보 수정 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
		}
		
		forward(request, response, path);
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		String root = request.getContextPath();
		
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		String username = request.getParameter("username");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String address = request.getParameter("address");
		
		try {
			MemberDto memberdto = new MemberDto();
			memberdto.setId(userid);
			memberdto.setPassword(userpwd);
			memberdto.setName(username);
			memberdto.setPhone(tel1+"-"+tel2+"-"+tel3);
			memberdto.setAddr(address);
			path = "/user/joinsuccess.jsp";
			memberService.join(memberdto);
			redirect(response, path, root);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원가입 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			forward(request, response, path);
		}
		
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = "/index.jsp";
		String root = request.getContextPath();
		HttpSession session = request.getSession();
		session.removeAttribute("userinfo"); // 세션 안에는 여러개 넣을 수 있다. 장바구니에 이것이 적절
		//session.invalidate(); // 로그아웃에서는 이게 맞고
		System.out.println(root + path);
		redirect(response, path, root);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		try {
			MemberDto memberDto = memberService.login(userid, userpwd); // null인지 null이 아닌지에 따라서 로그인의 여부가 결정
			if(memberDto != null) { // 로그인 성공
				///////////////////// session //////////////////////
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				//request.setAttribute("userinfo", memberDto);
				////////////////////////////////////////////////////
//				path = "/index.jsp";
				System.out.println("??");
				// 로그인이 성공됬을 때 쿠키 생성하는데 checkbox에 check가 되었을 때만 생성
				///////////////////// Cookie //////////////////////
				String idsv = request.getParameter("idsave");
				if("saveok".equals(idsv)) { // 아이디 저장 체크
					Cookie cookie = new Cookie("ssafy_id", userid);
					System.out.println(userid);
					cookie.setPath(request.getContextPath()); // 어느 경로에서 사용할지 설정
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
					System.out.println("쿠키 만들기 성공!");
				}else { // 체크 해제
					Cookie[] cookies = request.getCookies();
					if(cookies != null){
						for(Cookie cookie : cookies){
							if(cookie.getName().equals("ssafy_id")){
								cookie.setPath(request.getContextPath());
								cookie.setMaxAge(0); // 쿠기는 지우는게 없기 때문에 setMaxAge(0) 사용
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				
				forward(request, response, path);
			}else { // 실패
				request.setAttribute("msg", "아이디 또는 비밀번호를 확인해 주세요.");
				System.out.println("로그인실패");
				path = "/user/login.jsp";
				forward(request, response, path);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			forward(request, response, path);
		}
		
	}

	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void redirect(HttpServletResponse response, String path, String root) throws IOException {
		response.sendRedirect(root + path);
	}
}