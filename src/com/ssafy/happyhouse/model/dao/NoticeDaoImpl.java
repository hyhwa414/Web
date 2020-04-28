package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.util.DBUtil;

public class NoticeDaoImpl implements NoticeDao {

//	@Override
//	public List<NoticeDto> list() throws SQLException {
//		List<NoticeDto> list = new ArrayList<NoticeDto>();
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = DBUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select no, id, title, content, regtime \n");
//			sql.append("from notice \n");
//			sql.append("order by no desc \n");
//			pstmt = conn.prepareStatement(sql.toString());
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				NoticeDto noticeDto = new NoticeDto();
//				noticeDto.setNo(rs.getInt("no"));
//				noticeDto.setId(rs.getString("id"));
//				noticeDto.setTitle(rs.getString("title"));
//				noticeDto.setContent(rs.getString("content"));
//				noticeDto.setRegtime(rs.getString("regtime"));
//				
//				list.add(noticeDto);
//			}
//		} finally {
//			DBUtil.close(rs);
//			DBUtil.close(pstmt);
//			DBUtil.close(conn);
//		}
//		
//		return list;
//	}

	@Override
	public void write(NoticeDto noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("insert into notice (id, title, content, regtime) \n");
			insertMember.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, noticeDto.getId());
			System.out.println("sql>>>>>>>" + noticeDto.getId());
			pstmt.setString(2, noticeDto.getTitle());
			System.out.println("sql>>>>>>>" + noticeDto.getTitle());
			pstmt.setString(3, noticeDto.getContent());
			System.out.println("sql>>>>>>>" + noticeDto.getContent());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public NoticeDto show(int no) throws SQLException {
		NoticeDto noticeDto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no, id, title, content, regtime \n");
			sql.append("from notice \n");
			sql.append("where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				noticeDto = new NoticeDto();
				noticeDto.setNo(rs.getInt("no"));
				noticeDto.setId(rs.getString("id"));
				noticeDto.setTitle(rs.getString("title"));
				noticeDto.setContent(rs.getString("content"));
				noticeDto.setRegtime(rs.getString("regtime"));
				System.out.println("show 완료!");
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return noticeDto;
	}

	@Override
	public void modifyInfo(NoticeDto noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("update notice \n");
			insertMember.append("set title = ?, content = ?, regtime = now() \n");
			insertMember.append("where no = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, noticeDto.getTitle());
			pstmt.setString(2, noticeDto.getContent());
			pstmt.setInt(3, noticeDto.getNo());
			System.out.println("정보수정>>>>>>>" + noticeDto.getTitle());
			System.out.println("정보수정>>>>>>>" + noticeDto.getContent());
			System.out.println("정보수정>>>>>>>" + noticeDto.getRegtime());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

	}

	@Override
	public void delete(int no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("delete from notice \n");
			insertMember.append("where no = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			System.out.println("삭제완료!");
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

	}

	@Override
	public NoticeDto getInfo(int no) throws SQLException {
		NoticeDto noticeDto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no, id, title, content, regtime \n");
			sql.append("from notice \n");
			sql.append("where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				noticeDto = new NoticeDto();
				noticeDto.setNo(rs.getInt("no"));
				System.out.println("getinfo>>>>" + noticeDto.getNo());
				noticeDto.setId(rs.getString("id"));
				System.out.println("getinfo>>>>" + noticeDto.getId());
				noticeDto.setTitle(rs.getString("title"));
				System.out.println("getinfo>>>>" + noticeDto.getTitle());
				noticeDto.setContent(rs.getString("content"));
				System.out.println("getinfo>>>>" + noticeDto.getContent());
				noticeDto.setRegtime(rs.getString("regtime"));
				System.out.println("getinfo>>>>" + noticeDto.getRegtime());
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return noticeDto;
	}

	@Override
	public List<NoticeDto> list(int currentPage, int sizePerPage) throws SQLException {
		List<NoticeDto> list = new ArrayList<NoticeDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no, id, title, content, regtime \n");
			sql.append("from notice \n");
			sql.append("order by no desc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setInt(++idx, (currentPage - 1) * sizePerPage);
			pstmt.setInt(++idx, sizePerPage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeDto noticeDto = new NoticeDto();
				noticeDto.setNo(rs.getInt("no"));
				noticeDto.setId(rs.getString("id"));
				noticeDto.setTitle(rs.getString("title"));
				noticeDto.setContent(rs.getString("content"));
				noticeDto.setRegtime(rs.getString("regtime"));

				list.add(noticeDto);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return list;
	}

	@Override
	public int getTotalCount() throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(no) \n");
			sql.append("from notice \n");
			sql.append("order by no desc \n");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql.toString());
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
