package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dao.MemberDaoImpl;
import com.ssafy.happyhouse.model.dto.MemberDto;

public class MemberServiceImpl implements MemberService{

	private MemberDao memberDao;
	
	public MemberServiceImpl() {
		memberDao = new MemberDaoImpl();
	}
	
	@Override
	public MemberDto getPwd(String id, String name, String phone) throws SQLException {
		// TODO Auto-generated method stub
		return memberDao.getPwd(id, name, phone);
	}

	@Override
	public void join(MemberDto memberDto) throws Exception {
		if (memberDto.getId() == null || memberDto.getPassword() == null || memberDto.getName() == null || memberDto.getAddr() == null || memberDto.getPhone() == null) {
			throw new Exception();
		}
		System.out.println("join서비스실행");
		memberDao.join(memberDto);
	}

	@Override
	public MemberDto login(String userid, String userpwd) throws Exception {
		if(userid == null || userpwd == null)
			throw new Exception(); // DB가 일 할 필요가 없다.
		return memberDao.login(userid, userpwd);
	}

	@Override
	public void modifyInfo(MemberDto memberDto) throws SQLException {
		memberDao.modifyInfo(memberDto);
		
	}

	@Override
	public MemberDto getInfo(String userid) throws SQLException {
		return memberDao.getInfo(userid);
	}

	@Override
	public void delete(String userid) throws SQLException {
		memberDao.delete(userid);
	}

}
