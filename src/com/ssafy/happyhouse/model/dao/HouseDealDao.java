package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;


public interface HouseDealDao {
	public abstract List<HouseDeal> searchAll(int currentPage, int sizePerPage) throws ClassNotFoundException, SQLException;
	
	public List<HouseDeal> searchAptName(String aptName, int currentPage, int sizePerPage) throws SQLException, ClassNotFoundException;

	public List<HouseDeal> searchDong(String dong, int currentPage, int sizePerPage) throws SQLException, ClassNotFoundException;
	
	public HouseDeal show(int no) throws SQLException;
	
	public int getTotalCount(String key, String word) throws SQLException;
}
