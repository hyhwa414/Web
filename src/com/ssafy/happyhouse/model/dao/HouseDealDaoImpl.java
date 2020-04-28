package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.util.DBUtil;

public class HouseDealDaoImpl implements HouseDealDao{
	
	
	public List<HouseDeal> searchAll(int currentPage, int sizePerPage) throws SQLException, ClassNotFoundException {
		List<HouseDeal> list = new ArrayList<HouseDeal>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no,dong,AptName,code,dealAmount,buildYear,dealYear,dealMonth,dealDay,area,floor,jibun \n");
			sql.append("from housedeal \n");
			sql.append("order by no asc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setInt(++idx, (currentPage - 1) * sizePerPage);
			pstmt.setInt(++idx, sizePerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {				
				HouseDeal houseDeal = new HouseDeal();
				houseDeal.setNo(rs.getInt("no"));
				houseDeal.setDong(rs.getString("dong"));
				houseDeal.setAptName(rs.getString("aptName"));
				houseDeal.setCode(rs.getInt("code"));
				houseDeal.setDealAmount(rs.getString("dealAmount"));
				houseDeal.setBuildYear(rs.getInt("buildYear"));
				houseDeal.setDealYear(rs.getInt("dealYear"));
				houseDeal.setDealMonth(rs.getInt("dealMonth"));
				houseDeal.setDealDay(rs.getInt("dealDay"));
				houseDeal.setArea(rs.getDouble("area"));
				houseDeal.setFloor(rs.getInt("floor"));
				houseDeal.setJibun(rs.getString("jibun"));
				
				list.add(houseDeal);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}
	
	public List<HouseDeal> searchAptName(String aptName, int currentPage, int sizePerPage) throws SQLException, ClassNotFoundException {
		List<HouseDeal> list = new ArrayList<HouseDeal>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no,dong,AptName,code,dealAmount,buildYear,dealYear,dealMonth,dealDay,area,floor,jibun \n");
			sql.append("from housedeal \n");
			sql.append("where aptName like ? ");
			sql.append("order by no asc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, "%" + aptName + "%");
			pstmt.setInt(++idx, (currentPage - 1) * sizePerPage);
			pstmt.setInt(++idx, sizePerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				if(rs.getString("aptName").contains(aptName)) {					
					HouseDeal houseDeal = new HouseDeal();
					houseDeal.setNo(rs.getInt("no"));
					houseDeal.setDong(rs.getString("dong"));
					houseDeal.setAptName(rs.getString("aptName"));
					houseDeal.setCode(rs.getInt("code"));
					houseDeal.setDealAmount(rs.getString("dealAmount"));
					houseDeal.setBuildYear(rs.getInt("buildYear"));
					houseDeal.setDealYear(rs.getInt("dealYear"));
					houseDeal.setDealMonth(rs.getInt("dealMonth"));
					houseDeal.setDealDay(rs.getInt("dealDay"));
					houseDeal.setArea(rs.getDouble("area"));
					houseDeal.setFloor(rs.getInt("floor"));
					houseDeal.setJibun(rs.getString("jibun"));
					
					list.add(houseDeal);
				}
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}
	
	public List<HouseDeal> searchDong(String dong, int currentPage, int sizePerPage) throws SQLException, ClassNotFoundException {
		List<HouseDeal> list = new ArrayList<HouseDeal>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no,dong,AptName,code,dealAmount,buildYear,dealYear,dealMonth,dealDay,area,floor,jibun \n");
			sql.append("from housedeal \n");
			sql.append("where dong like ? ");
			sql.append("order by no asc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, "%" + dong + "%");
			pstmt.setInt(++idx, (currentPage - 1) * sizePerPage);
			pstmt.setInt(++idx, sizePerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				if(rs.getString("dong").contains(dong)) {					
					HouseDeal houseDeal = new HouseDeal();
					houseDeal.setNo(rs.getInt("no"));
					houseDeal.setDong(rs.getString("dong"));
					houseDeal.setAptName(rs.getString("aptName"));
					houseDeal.setCode(rs.getInt("code"));
					houseDeal.setDealAmount(rs.getString("dealAmount"));
					houseDeal.setBuildYear(rs.getInt("buildYear"));
					houseDeal.setDealYear(rs.getInt("dealYear"));
					houseDeal.setDealMonth(rs.getInt("dealMonth"));
					houseDeal.setDealDay(rs.getInt("dealDay"));
					houseDeal.setArea(rs.getDouble("area"));
					houseDeal.setFloor(rs.getInt("floor"));
					houseDeal.setJibun(rs.getString("jibun"));
					
					list.add(houseDeal);
				}
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}
	
	public HouseDeal show(int no) throws SQLException {
		HouseDeal houseDeal = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no,dong,AptName,code,dealAmount,buildYear,dealYear,dealMonth,dealDay,area,floor,jibun \n");
			sql.append("from housedeal \n");
			sql.append("where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				houseDeal = new HouseDeal();
				houseDeal.setNo(rs.getInt("no"));
				houseDeal.setDong(rs.getString("dong"));
				houseDeal.setAptName(rs.getString("aptName"));
				houseDeal.setCode(rs.getInt("code"));
				houseDeal.setDealAmount(rs.getString("dealAmount"));
				houseDeal.setBuildYear(rs.getInt("buildYear"));
				houseDeal.setDealYear(rs.getInt("dealYear"));
				houseDeal.setDealMonth(rs.getInt("dealMonth"));
				houseDeal.setDealDay(rs.getInt("dealDay"));
				houseDeal.setArea(rs.getDouble("area"));
				houseDeal.setFloor(rs.getInt("floor"));
				houseDeal.setJibun(rs.getString("jibun"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return houseDeal;
	}
	
	public int getTotalCount(String key, String word) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(no) \n");
			sql.append("from housedeal \n");
			if(!key.equals(""))
				sql.append("where " + key + " like ? ");
			sql.append("order by no desc \n");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql.toString());
			if(!key.equals(""))
				pstmt.setString(1, "%" + word + "%");
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			System.out.println(cnt);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return cnt;
	}
}
