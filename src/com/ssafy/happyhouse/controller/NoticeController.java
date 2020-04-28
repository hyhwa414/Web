package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;
import com.ssafy.happyhouse.model.service.NoticeServiceImpl;
import com.ssafy.happyhouse.util.*;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/notice.do")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoticeService noticeService;

	@Override
	public void init() throws ServletException {
		super.init();
		noticeService = new NoticeServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String root = request.getContextPath();
		String path = "/index.jsp";

		String act = request.getParameter("act");

		if ("notice".equals(act)) {
			notice(request, response);
		} else if ("mvwrite".equals(act)) {
			System.out.println("글쓰는 곳 옴");
			path = "/notice/write.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("write".equals(act)) {
			System.out.println("글쓰기 시작");
			write(request, response);
		} else if ("show".equals(act)) {
			show(request, response);
		} else if ("mvmodify".equals(act)) {
			moveModify(request, response);
		} else if ("modify".equals(act)) {
			modifyInfo(request, response);
		} else if ("delete".equals(act)) {
			delete(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String root = request.getContextPath();
		String path = "/index.jsp";
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);

		try {
			noticeService.delete(no);
			path = "/notice/deletesuccess.jsp";
			HttpSession session = request.getSession();
			session.removeAttribute("list");
			System.out.println(root + path);
			response.sendRedirect(request.getContextPath() + path);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "공지사항 삭제 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			response.sendRedirect(request.getContextPath() + path);
		}

	}

	private void modifyInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String root = request.getContextPath();
		String path = "/index.jsp";
		HttpSession session = request.getSession();
		NoticeDto noticeDto = new NoticeDto();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		noticeDto.setId(memberDto.getId());
		System.out.println("이동>>>" + noticeDto.getId());
		noticeDto.setTitle(request.getParameter("title"));
		noticeDto.setContent(request.getParameter("content"));
		noticeDto.setNo(Integer.parseInt(request.getParameter("no")));
		noticeDto.setRegtime(request.getParameter("regtime"));

		try {
			noticeService.modifyInfo(noticeDto);
			noticeDto = noticeService.getInfo(noticeDto.getNo());
			session.setAttribute("list", noticeDto);
			path = "/notice/show.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원정보 수정 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			response.sendRedirect(request.getContextPath() + path);
		}
	}

	private void moveModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String path = "/index.jsp";
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		try {
			NoticeDto noticeDto = new NoticeDto();
			noticeDto.setId(memberDto.getId());
			noticeDto = noticeService.getInfo(no);
			session.setAttribute("notice", noticeDto);
			session.setAttribute("userinfo", memberDto);
			path = "/notice/modify.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 정보 수정 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			response.sendRedirect(request.getContextPath() + path);
		}
	}

	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String path = "/index.jsp";
		NoticeDto noticeDto;
		try {
			noticeDto = noticeService.show(no);
			System.out.println("find>>>>>>>>>" + noticeDto.getId());
			path = "/notice/show.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("list", noticeDto);
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "비밀번호 찾기 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			response.sendRedirect(request.getContextPath() + path);
		}
	}

	private void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		NoticeDto noticeDto = new NoticeDto();

		if (memberDto != null) {
			noticeDto.setId(request.getParameter("id"));
			System.out.println("작성자 : " + noticeDto.getId());
			noticeDto.setTitle(request.getParameter("title"));
			noticeDto.setContent(request.getParameter("content"));
			try {
				noticeService.write(noticeDto);
				path = "/notice/writesuccess.jsp";
				session = request.getSession();
				session.setAttribute("list", noticeDto);
				request.getRequestDispatcher(path).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글작성중 문제가 발생했습니다.");
				path = "/error/error.jsp";
				response.sendRedirect(request.getContextPath() + path);
			}
		} else {
			request.setAttribute("msg", "로그인 후 사용 가능한 페이지입니다.");
			path = "/error/error.jsp";
			response.sendRedirect(request.getContextPath() + path);
		}
	}

	private void notice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		int currentPage = Integer.parseInt(request.getParameter("pg")); //현재페이지번호
		System.out.println(">>>>>>>>"+currentPage);
		String spp = request.getParameter("spp"); // 한페이지당 글 갯수
		System.out.println(">>>>>>>>>>>>>>"+spp);
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);//sizePerPage
		try {
			List<NoticeDto> list = noticeService.list(currentPage, sizePerPage);
			PageNavigation pageNavigation = noticeService.makePageNavigation(currentPage, sizePerPage);
			request.setAttribute("list", list);
			request.setAttribute("navigation", pageNavigation);
			path = "/notice/list.jsp";
			System.out.println("공지 이동");
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글목록을 얻어오는 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
		}
	}

}
