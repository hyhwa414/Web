package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dao.NoticeDao;
import com.ssafy.happyhouse.model.dao.NoticeDaoImpl;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.util.PageNavigation;

public class NoticeServiceImpl implements NoticeService{

	private NoticeDao noticeDao;
	
	public NoticeServiceImpl() {
		noticeDao = new NoticeDaoImpl();
	}
	
	@Override
	public void write(NoticeDto noticeDto) throws Exception {
		if(noticeDto.getTitle() == null || noticeDto.getContent() == null) {
			throw new Exception();
		}
		noticeDao.write(noticeDto);
	}

	@Override
	public NoticeDto show(int no) throws SQLException {
		return noticeDao.show(no);
	}

	@Override
	public void modifyInfo(NoticeDto noticeDto) throws SQLException {
		noticeDao.modifyInfo(noticeDto);
		
	}

	@Override
	public void delete(int no) throws SQLException {
		noticeDao.delete(no);
	}

	@Override
	public NoticeDto getInfo(int no) throws SQLException {
		return noticeDao.getInfo(no);
	}


	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10; // 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = noticeDao.getTotalCount(); // 총 게시글 수
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

	@Override
	public List<NoticeDto> list(int currentPage, int sizePerPage) throws Exception{
		return noticeDao.list(currentPage, sizePerPage);
	}
}
