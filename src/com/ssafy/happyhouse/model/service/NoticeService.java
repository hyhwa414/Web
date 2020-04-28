package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface NoticeService {
	//public List<NoticeDto> list() throws SQLException;
	public void write(NoticeDto noticeDto) throws Exception;
	public NoticeDto show(int no) throws SQLException;
	public void modifyInfo(NoticeDto noticeDto) throws SQLException;
	public void delete(int no) throws SQLException;
	public NoticeDto getInfo(int no) throws SQLException;
	public PageNavigation makePageNavigation(int pg, int sizePerPage) throws Exception;
	public List<NoticeDto> list(int currentPage, int sizePerPage) throws Exception;
}
