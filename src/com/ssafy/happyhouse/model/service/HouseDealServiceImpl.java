package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dao.HouseDealDao;
import com.ssafy.happyhouse.model.dao.HouseDealDaoImpl;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.util.PageNavigation;

public class HouseDealServiceImpl implements HouseDealService{
	private HouseDealDao dao;

	public HouseDealServiceImpl() {
		 dao =new HouseDealDaoImpl();
	}
	
	public PageNavigation makePageNavigation(String key, String word, int currentPage, int sizePerPage) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10; // 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = dao.getTotalCount(key, word); // 총 게시글 수
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1; // 총 페이지 수
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize; // startRange ture : 이전X false : 이전O
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1)/naviSize * naviSize < currentPage; // endRange true : 다음X false : 다음O
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
	
	public List<HouseDeal> searchAll(int currentPage, int sizePerPage) throws Exception {
		try {
			return dao.searchAll(currentPage, sizePerPage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<HouseDeal> searchAptName(String aptName, int currentPage, int sizePerPage) throws Exception {
		try {
			return dao.searchAptName(aptName, currentPage, sizePerPage);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<HouseDeal> searchDong(String dong, int currentPage, int sizePerPage) throws Exception {
		try {
			return dao.searchDong(dong, currentPage, sizePerPage);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public HouseDeal show(int no) throws SQLException {
		return dao.show(no);
	}
}
