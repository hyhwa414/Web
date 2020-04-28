package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface NoticeDao {
	//public List<NoticeDto> list() throws SQLException;
	public void write(NoticeDto noticeDto) throws SQLException;
	public NoticeDto show(int no) throws SQLException;
	public void modifyInfo(NoticeDto noticeDto) throws SQLException;
	public void delete(int no) throws SQLException;
	public NoticeDto getInfo(int no) throws SQLException;
	public List<NoticeDto> list(int currentPage, int sizePerPage) throws SQLException;
	public int getTotalCount() throws SQLException;
}
