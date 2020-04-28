package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.util.DBUtil;

public class MemberDaoImpl implements MemberDao{

	@Override
	public MemberDto getPwd(String id, String name, String phone) throws SQLException {
		MemberDto memberDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, name, password \n");
			sql.append("from user \n");
			sql.append("where id = ? and name = ? and phone = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
	            memberDto.setId(id);
	            memberDto.setName(name);
	            memberDto.setPassword(rs.getString("password"));
	            memberDto.setPhone(phone);
	            System.out.println(memberDto.getId() + "," + memberDto.getPassword()+ ","+memberDto.getName());
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return memberDto;
	}

	@Override
	public void join(MemberDto memberDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into user(id, password, name, addr, phone) values (?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberDto.getId()); 
			pstmt.setString(2, memberDto.getPassword()); 
			pstmt.setString(3, memberDto.getName()); 
			pstmt.setString(4, memberDto.getAddr()); 
			pstmt.setString(5, memberDto.getPhone());
			int cnt = pstmt.executeUpdate(); 
			System.out.println("변경된 row : " + cnt);
		} catch (SQLException e) {
			System.out.println("SQL 에러");
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
	}

	@Override
	public MemberDto login(String userid, String userpwd) throws SQLException {
		MemberDto memberDto = null; // 로그인 된 사람의 정보를 담기 위해 만든 것
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, name, addr, phone \n");
			sql.append("from user \n");
			sql.append("where id = ? and password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userid);
			pstmt.setString(2, userpwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setId(rs.getString("id"));
				memberDto.setName(rs.getString("name"));
				memberDto.setAddr(rs.getString("addr"));
				memberDto.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			memberDto = null;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return memberDto;
	}

	@Override
	public void modifyInfo(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("update user \n");
			insertMember.append("set password = ?, name = ?, addr = ?, phone = ?\n");
			insertMember.append("where id = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1,memberDto.getPassword());
			System.out.println("정보수정>>>>>>>"+memberDto.getPassword());
			pstmt.setString(2,memberDto.getName());
			System.out.println("정보수정>>>>>>>"+memberDto.getName());
			pstmt.setString(3,memberDto.getAddr());
			System.out.println("정보수정>>>>>>>"+memberDto.getAddr());
			pstmt.setString(4,memberDto.getPhone());
			System.out.println("정보수정>>>>>>>"+memberDto.getPhone());
			pstmt.setString(5,memberDto.getId());
			System.out.println("정보수정>>>>>>>"+memberDto.getId());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
	}

	@Override
	public MemberDto getInfo(String userid) throws SQLException {
MemberDto memberDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, password, name, addr, phone\n");
			sql.append("from user \n");
			sql.append("where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setId(rs.getString("id"));
				memberDto.setPassword(rs.getString("password"));
				memberDto.setName(rs.getString("name"));
				memberDto.setAddr(rs.getString("addr"));
				memberDto.setPhone(rs.getString("phone"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return memberDto;
	}

	@Override
	public void delete(String userid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("delete from user \n");
			insertMember.append("where id = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
			System.out.println("삭제완료!");
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}
	
}
