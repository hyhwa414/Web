package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.util.PageNavigation;


public interface HouseDealService {
	public PageNavigation makePageNavigation(String key, String word, int currentPage, int sizePerPage) throws Exception;
	
	public List<HouseDeal> searchAll(int currentPage, int sizePerPage) throws Exception;
	
	public List<HouseDeal> searchAptName(String aptName, int currentPage, int sizePerPage) throws Exception;
	
	public List<HouseDeal> searchDong(String dong, int currentPage, int sizePerPage) throws Exception;
	
	public HouseDeal show(int no) throws SQLException;
}
