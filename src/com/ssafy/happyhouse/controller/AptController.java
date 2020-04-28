package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.service.HouseDealService;
import com.ssafy.happyhouse.model.service.HouseDealServiceImpl;
import com.ssafy.happyhouse.util.PageNavigation;


@WebServlet("/apt.do")
public class AptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HouseDealService houseDealService;
	
	public void init() {
		houseDealService = new HouseDealServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		
		String act = request.getParameter("act");
		String path = "/apt/search.jsp";
		if("searchAll".equals(act)) {
			searchAll(request, response);
		} else if("searchAptName".equals(act)) {
			searchAptName(request, response);
		} else if("searchDong".equals(act)) {
			searchDong(request, response);
		} else if ("show".equals(act)) {
			show(request, response);
		} 
	}

	private void searchAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String path = "/apt/search.jsp";
		int currentPage = Integer.parseInt(request.getParameter("pg")); //현재페이지번호
		System.out.println(">>>>>>>>"+currentPage);
		String spp = request.getParameter("spp"); // 한페이지당 글 갯수
		System.out.println(">>>>>>>>>>>>>>"+spp);
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);//sizePerPage
		
		try {			
			List<HouseDeal> list = houseDealService.searchAll(currentPage, sizePerPage);
			PageNavigation pageNavigation = houseDealService.makePageNavigation("", "", currentPage, sizePerPage);
			request.setAttribute("deals", list);
			request.setAttribute("navigation", pageNavigation);
			path = "/apt/searchList.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void searchAptName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String path = "/apt/search.jsp";
		String aptName = request.getParameter("word");
		int currentPage = Integer.parseInt(request.getParameter("pg")); //현재페이지번호
		System.out.println(">>>>>>>>"+currentPage);
		String spp = request.getParameter("spp"); // 한페이지당 글 갯수
		System.out.println(">>>>>>>>>>>>>>"+spp);
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);//sizePerPage
		
		try {			
			List<HouseDeal> list = houseDealService.searchAptName(aptName, currentPage, sizePerPage);
			PageNavigation pageNavigation = houseDealService.makePageNavigation("aptName", aptName, currentPage, sizePerPage);
			request.setAttribute("deals", list);
			request.setAttribute("navigation", pageNavigation);
			path = "/apt/searchList.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void searchDong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String path = "/apt/search.jsp";
		String dong = request.getParameter("word");
		int currentPage = Integer.parseInt(request.getParameter("pg")); //현재페이지번호
		System.out.println(">>>>>>>>"+currentPage);
		String spp = request.getParameter("spp"); // 한페이지당 글 갯수
		System.out.println(">>>>>>>>>>>>>>"+spp);
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);//sizePerPage
		
		try {			
			List<HouseDeal> list = houseDealService.searchDong(dong, currentPage, sizePerPage);
			PageNavigation pageNavigation = houseDealService.makePageNavigation("dong", dong, currentPage, sizePerPage);
			request.setAttribute("deals", list);
			request.setAttribute("navigation", pageNavigation);
			path = "/apt/searchList.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String path = "/index.jsp";
		
		try {
			HouseDeal houseDeal = houseDealService.show(no);
			path = "apt/showApt.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("deal", houseDeal);
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "상세정보 조회 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			response.sendRedirect(request.getContextPath() + path);
		}
	}
}
